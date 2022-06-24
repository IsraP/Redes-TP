package TP;

import TP.Threads.ReadThread;
import TP.Threads.WriteThread;

import java.util.Scanner;

public class Chat {
    ReadThread readThread;
    WriteThread writeThread;

    public Chat(String ip, int port, int portTo){

        readThread = new ReadThread(portTo);
        writeThread = new WriteThread(ip, port);

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
        chat.enviar(msg);
    }
}
