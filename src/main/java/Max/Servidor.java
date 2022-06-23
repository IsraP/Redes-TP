package Max;

import java.io.*;
import java.net.*;

class Servidor
{

   public static void main(String[] args) throws Exception
   {
      //Efetua as primitivas socket e bind
      int portaServidor = 6789;
      ServerSocket socket = new ServerSocket(portaServidor);

      while(true)
      {
         Socket conexao = socket.accept();

         System.out.println("Aguardando datagrama do cliente....");
         BufferedReader entrada =  new BufferedReader(new InputStreamReader(conexao.getInputStream()));

         String str = entrada.readLine();
         System.out.println("Received: " + str);

         str = str.toUpperCase() + '\n';

         DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
         saida.writeBytes(str);

         conexao.close();
      }
   }
}
