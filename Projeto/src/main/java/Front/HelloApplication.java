package Front;

import TP.Threads.ReadThread;
import TP.Threads.WriteThread;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {
    private TextField tftexto = new TextField();
    public static GridPane mensagens = new GridPane();
    public static int countMessage = 0;
    public Chat chat;
    @Override
    public void start(Stage primaryStage) throws IOException {

        VBox vbox = new VBox();
        GridPane gp = new GridPane();
        gp.setPadding( new Insets(10) );
        gp.setHgap( 4 );
        gp.setVgap( 10 );
        VBox.setVgrow(gp, Priority.ALWAYS );

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow( Priority.ALWAYS );

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow( Priority.ALWAYS );

        Button config = new Button("Configurar conversa");
        config.setOnAction(this::OpenDialog);

        gp.add(config,0,0);

        Label lblEmail = new Label("Conversa:");
        gp.add( lblEmail,0, 1);


        mensagens.setPadding( new Insets(10) );
        mensagens.setHgap( 2 );
        mensagens.setVgap( 2 );
        mensagens.getColumnConstraints().addAll(  col1,new ColumnConstraints( 100 ), col2 );
        gp.add(mensagens,0,2);
//        TextArea taDescription = new TextArea();
//        gp.add( taDescription,  0, 2);


        Label lblContract = new Label("Digite:");
        gp.add( lblContract,    0, 3 );

        GridPane gp2 = new GridPane();

        gp2.getColumnConstraints().addAll(  col1, col2 );
        gp2.setPadding( new Insets(2) );
        gp2.setHgap( 2 );
        gp2.setVgap( 1 );

        gp2.add( tftexto,     0, 0 );


        Button saveButton = new Button("Save");
        saveButton.setOnAction( (ActionEvent event) -> {
            if(!tftexto.getText().isEmpty()){
                String msg = tfPortRemV + "-" + tfPortDestV + "-"+ tftexto.getText();
                chat.writeThread.enviar(msg);
                HelloApplication.mensagens.add(new Label(msg),0,HelloApplication.countMessage);
                HelloApplication.countMessage++;
            }

        });
        gp2.add( saveButton,     1, 0 );
        gp.add(gp2,0,4);


        vbox.getChildren().addAll( gp );

        Scene scene = new Scene(vbox);

        primaryStage.setTitle("Grid Pane App");
        primaryStage.setScene(scene);
        primaryStage.setWidth( 500 );
        primaryStage.setHeight( 736  );
        primaryStage.show();


    }
    private TextField tfipRemet = new TextField();
    private TextField tfPortRem = new TextField();
    private TextField tfipDest = new TextField();
    private TextField tfPortDest = new TextField();

    private String tfipRemetV = "";
    private String tfPortRemV= "";
    private String tfipDestV= "";
    private String tfPortDestV= "";
    public void OpenDialog(ActionEvent evt)  {

        tfPortRem.setText(tfPortRemV;
        tfipRemet.setText(tfipRemetV);
        tfipDest.setText(tfipDestV);
        tfPortDest.setText(tfPortDestV);


        Stage s = new Stage();
        VBox vbox = new VBox();
        GridPane gp = new GridPane();
        gp.setPadding( new Insets(10) );
        gp.setHgap( 4 );
        gp.setVgap( 10 );
        VBox.setVgrow(gp, Priority.ALWAYS );



        Label lblconfig = new Label("Configuração:");
        gp.add( lblconfig,    0, 0 );



        Label lblipRemet = new Label("IP Remetente:");
        gp.add( lblipRemet,    0, 1 );

        gp.add( tfipRemet,    0, 2 );

        Label lblPortRem = new Label("Porta remetente:");
        gp.add( lblPortRem,    1, 1 );
        gp.add( tfPortRem,    1, 2 );


        Label lblipDest = new Label("IP Destinatário:");
        gp.add( lblipDest,    0, 3 );

        gp.add( tfipDest,    0, 4 );

        Label lblPortDest = new Label("Porta Destinatário:");
        gp.add( lblPortDest,    1, 3 );

        gp.add( tfPortDest,    1, 4 );


        Button config = new Button("Salvar");
        //config.setOnAction();

        gp.add(config,0,5);

        config.setOnAction( ( ActionEvent event)->{
            if( !tfPortRem.getText().isEmpty()
                &&  !tfipRemet.getText().isEmpty()
                && !tfipDest.getText().isEmpty()
                && !tfPortDest.getText().isEmpty()){
                if(tfPortRem.getText() != tfPortRemV
                    || tfipRemet.getText() != tfipRemetV
                    || tfipDest.getText() != tfipDestV
                    || tfPortDest.getText() != tfPortDestV){
                    mensagens = new GridPane();
                    countMessage = 0;
                }
                tfPortRemV = tfPortRem.getText();
                tfipRemetV = tfipRemet.getText();
                tfipDestV = tfipDest.getText();
                tfPortDestV = tfPortDest.getText();
                int porta = Integer.parseInt(tfPortRemV);
                chat = new Chat(porta);
                chat.receber();
                s.close();
            }
        });
        vbox.getChildren().addAll( gp );

        Scene scene = new Scene(vbox);

        s.setTitle("Grid Pane App");
        s.setScene(scene);
        s.setWidth( 400 );
        s.setHeight( 400  );
        s.showAndWait();

    }
}


class Chat {
    ReadThread readThread;
    WriteThread writeThread;
    private static int  serverPort = 4567;
    private static String  serverIp = "127.0.0.1";

    public Chat( int portTo){

        readThread = new ReadThread(portTo);
        writeThread = new WriteThread(serverIp, serverPort);

    }

    public String parseMessage(Integer portTo, Integer portFrom, String txt){
        String msg = "";

        String[] values = txt.split("-");

        portFrom = Integer.parseInt(values[0]);
        portTo = Integer.parseInt(values[1]);
        msg = values[2];

        return msg;
    }

    public void enviar(String msg){
        writeThread.start();
        writeThread.run(msg);
    }

    public void receber(){
        readThread.start();
    }


    public static void main(String[] args) {
        String localIp = "127.0.0.1";

        int portFrom;
        int portTo;

        String msg;

        Scanner scan = new Scanner(System.in);

        System.out.println("Informe sua porta: ");
        portFrom = scan.nextInt();

        System.out.println("Informe a porta Destino: ");
        portTo = scan.nextInt();

        TP.Chat chat = new TP.Chat(localIp, serverPort, portFrom);

        msg = portFrom + "-" + portTo + "-";


        chat.enviar(msg);
    }
}