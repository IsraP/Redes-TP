package Max;

import java.io.*;
import java.net.*;

class Cliente
{

   public static String lerString () throws Exception {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      return in.readLine();
   }

   public static void main(String[] args) throws Exception
   {
      int portaServidor = 5678;


      while(true) {
         Socket socket = new Socket("127.0.0.1", portaServidor);
         DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
         saida.writeBytes(lerString() + '\n');

//         BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//         System.out.println("FROM SERVER: " + entrada.readLine());
         socket.close();
      }

   }
}
