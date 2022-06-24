package TP;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Front extends Application {

    private TextField tftexto = new TextField();
    private GridPane mensagens = new GridPane();
    @Override
    public void start(Stage primaryStage) throws IOException {
        VBox vbox = new VBox();
        GridPane gp = new GridPane();
        gp.setPadding( new Insets(10) );
        gp.setHgap( 4 );
        gp.setVgap( 10 );
        VBox.setVgrow(gp, Priority.ALWAYS );
        DialogApp dialog = new DialogApp();

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow( Priority.ALWAYS );

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow( Priority.ALWAYS );

        Button config = new Button("Configurar conversa");
        config.setOnAction(dialog::Open);

        gp.add(config,0,0);

        Label lblEmail = new Label("Conversa:");
        gp.add( lblEmail,0, 1);


        mensagens.setPadding( new Insets(10) );
        mensagens.setHgap( 2 );
        mensagens.setVgap( 2 );
        mensagens.getColumnConstraints().addAll(  col1,new ColumnConstraints( 100 ), col2 );
        mensagens.add(new Label("192.168.0.1:4000"),0,0);
        mensagens.add(new Label("mensagem"),0,1);
        mensagens.add(new Label("192.168.0.1:4000"),2,2);
        mensagens.add(new Label("mensagem"),2,3);
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

            mensagens.add(new Label(tftexto.getText()),2,5);

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

}
class DialogApp extends Application {


    private TextField tfipRemet = new TextField();
    private TextField tfPortRem = new TextField();
    private TextField tfipDest = new TextField();
    private TextField tfPortDest = new TextField();

    @Override
    public void start(Stage Stage) throws Exception {

    }

    public void Open(ActionEvent evt)  {
        Stage s = new Stage();
        VBox vbox = new VBox();
        GridPane gp = new GridPane();
        gp.setPadding( new Insets(10) );
        gp.setHgap( 4 );
        gp.setVgap( 10 );
        VBox.setVgrow(gp, Priority.ALWAYS );
        DialogApp dialog = new DialogApp();


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
            s.close();
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