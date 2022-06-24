package TP.Threads;

import Front.HelloApplication;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ReadThread extends Thread {
    int port;

    public ReadThread(int port){
        this.port = port;
    }

    public void run(){
        System.out.println("Escutando na porta " + port);

        try {
            ServerSocket connectionListener = new ServerSocket(port);
            String txt;

            while(true){
                Socket conexao = connectionListener.accept();

//                System.out.println("Cliente conectado do IP " + conexao.getInetAddress().getHostAddress() + ":" + port);
                Scanner scan = new Scanner(conexao.getInputStream());
                txt = scan.nextLine();

                String finalTxt = txt;
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        HelloApplication.mensagens.add(new Label(finalTxt),0,HelloApplication.countMessage);
                        HelloApplication.countMessage++;
                    }
                });

//                HelloApplication.mensagens.add(new Label(txt),0,HelloApplication.countMessage);
//                HelloApplication.countMessage++;
                System.out.println(txt);

                conexao.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
