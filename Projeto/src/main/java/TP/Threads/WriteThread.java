package TP.Threads;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread extends Thread {

    String ip;
    int port;

    public WriteThread(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public void run(String msg){
        enviar(msg);
    }

    public void enviar(String msg){

        try {
            Socket connection = new Socket(ip, port);

            DataOutputStream saida = new DataOutputStream(connection.getOutputStream()); // pega informacoes do buffer de recebimento do servidor
            saida.writeBytes(msg +'\n'); // envia
            saida.flush();
            saida.close();

            connection.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
