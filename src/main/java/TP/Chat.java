package TP;

import TP.Threads.ReadThread;
import TP.Threads.WriteThread;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Chat {
    ReadThread readThread;
    WriteThread writeThread;
    Socket connection;

    public Chat(String ip, int port, int portTo){
        try {
            connection = new Socket(ip, port);

            readThread = new ReadThread(portTo);
            writeThread = new WriteThread();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String parseMessage(Integer portTo, Integer portFrom, String txt){
        String msg = "";

        String[] values = txt.split("-");

        portFrom = Integer.parseInt(values[0]);
        portTo = Integer.parseInt(values[1]);
        msg = values[2];

        return msg;
    }

    public void enviar(String msg, String ip, int port){
        writeThread.start();
        writeThread.run(msg, ip, port);
    }

    public void receber(){
        readThread.start();
    }


    public static void main(String[] args) {
        String localIp = "127.0.0.1";
        int serverPort = 4567;
        int portFrom;
        int portTo;

        String msg;

        Scanner scan = new Scanner(System.in);

        System.out.println("Informe sua porta: ");
        portFrom = scan.nextInt();

        System.out.println("Informe a porta Destino: ");
        portTo = scan.nextInt();

        Chat chat = new Chat(localIp, serverPort, portFrom);

        msg = portFrom + "-" + portTo + "-";

        chat.receber();
        chat.enviar(msg, localIp, portTo);
    }
}
