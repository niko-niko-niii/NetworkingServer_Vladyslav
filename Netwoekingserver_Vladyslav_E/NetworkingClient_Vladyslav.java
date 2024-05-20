import java.net.*;
import java.io.*;
import java.sql.SQLOutput;


public class NetworkingClient_Vladyslav {

    public static void main(String[] args) {

        Socket client = null;

        int portNumber = 8000               ;
        if (args.length >= 1) {
            portNumber = Integer.parseInt(args[0]);
        }
        for(int i=0; i<10; i++){
           try{
               String msg ="";

               //Create client socket
               client =new Socket(InetAddress.getLocalHost(),portNumber);
               System.out.println("client socket created"+client);

               // Create an output steram of the client socket
               OutputStream clientOut =client.getOutputStream();
               PrintWriter pw =new PrintWriter(clientOut, true);

               //create an input stream of the client socket
               InputStream clientIn = client.getInputStream();
               BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientIn));
               BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

               //creater BufferedReader for a standard input
               BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

               System.out.println("enetr your name. Type bye to exit");

               //read data from standeard input device and write it
               // to the output stream of  the client socket
               msg=stdIn.readLine().trim();
               pw.println(msg);

               //read data from the input stream of the client socket.
               System.out.println("message returned to the server="+br.readLine());

               pw.close();
               br.close();
               client.close();

               //stop the operation
               if (msg.equalsIgnoreCase("Bye")){
                   break;
               }
           }catch( IOException ie){
               System.out.println("I/O error"+ie);
           }
        }

    }
}


