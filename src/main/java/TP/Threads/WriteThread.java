package TP.Threads;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread extends Thread {

    public void run(String msg, String ip, int port){
        enviar(msg, ip, port);
    }

    public void enviar(String msg, String ip, int port){
        Scanner scan = new Scanner(System.in);
        String txt = "";
        while(!txt.equals("FIM")){
            System.out.println("Digite sua mensagem: ");
            txt = scan.nextLine();

            try {
                Socket connection = new Socket(ip, port);
                DataOutputStream saida = new DataOutputStream(connection.getOutputStream()); // pega informacoes do buffer de recebimento do servidor
                saida.writeBytes(msg + txt +'\n'); // envia
                saida.flush();
                saida.close();
                connection.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
