package TP.Server;
// C1: 5678
// C2: 6789
// C3: 7890
// C4: 8901

import java.util.*;

public class ServerThread extends Thread {
    public int portFrom;
    public int portTo;
    public String ip;
    public List<String> msgs;

    public ServerThread(String ip, int portTo, int portFrom, String msg){
        this.ip = ip;
        this.portTo = portTo;
        this.portFrom = portFrom;
        this.msgs = new LinkedList<String>();
        this.msgs.add(msg);
    }

    @Override
    public String toString() {
        return "ServerThread{" +
                "portFrom=" + portFrom +
                ", portTo=" + portTo +
                ", ip='" + ip + '\'' +
                ", msgs=" + msgs +
                '}';
    }
}
