package TP.Server;

import java.io.DataOutputStream;
import java.util.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private int port;
    private ServerSocket connectionListener;
    private List<ServerThread> currentConnections;

    public ChatServer(int port){
        this.port = port;
        currentConnections = new LinkedList<>();
    }


    public ServerThread getOrCreateThread(String txt){
        int portTo;
        int portFrom;
        String ip = "127.0.0.1";
        String msg;
        System.out.println("TXT THREAD CREATE  = " + txt+"\n\n");
        String[] values = txt.split("-");

        portFrom = Integer.parseInt(values[0]);
        portTo = Integer.parseInt(values[1]);
        msg = values[2];


        for(ServerThread entry : currentConnections){
            if(portTo == entry.portTo && portFrom == entry.portFrom){
                return entry;
            }
        }

        ServerThread newThread = new ServerThread(ip, portTo, portFrom, msg);

        currentConnections.add(newThread);

        return newThread;
    }

    public void run(){
        String txt;

        try {
            connectionListener = new ServerSocket(port);
            System.out.println("Listening on port " + port);

            while(true){
                Socket conexao = connectionListener.accept();

                //System.out.println("Cliente conectado do IP " + conexao.getInetAddress().getHostAddress());
                Scanner scan = new Scanner(conexao.getInputStream());
                txt = scan.nextLine();

                ServerThread currentThread = getOrCreateThread(txt);


                //System.out.println(currentThread.ip + ":" + currentThread.portTo);
                Socket conexaoComCliente = new Socket(currentThread.ip, currentThread.portTo);
                //System.out.println("[" + currentThread.portFrom + "] diz: " + currentThread.msgs.get(currentThread.msgs.size() - 1) + '\n');
                DataOutputStream saida = new DataOutputStream(conexaoComCliente.getOutputStream());
                saida.writeBytes("[" + currentThread.portFrom + "] diz: " + currentThread.msgs.get(currentThread.msgs.size() - 1) + '\n');
                saida.flush();
                saida.close();

                conexaoComCliente.close();

                conexao.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // chat.enviar envia informacoes de thread
    // servidor verifica ou cria thread requisitada
    // servidor executa o envio da mensagem da thread
    // repita o processo


    public static void main(String[] args) {
        ChatServer cs = new ChatServer(4567);
        cs.run();
    }

}
