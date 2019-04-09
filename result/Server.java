import java.net.*;
import java.io.*;
import java.util.*;


public class Server{
    static ArrayList<Socket> socketCont = new ArrayList<Socket>();
    static ArrayList<Thread> threadCont = new ArrayList<Thread>();
    public static void main(String args[]){
        try{
            ServerSocket server = new ServerSocket(5005);
            DataInputStream input;
            DataOutputStream out;

            File file = new File("./output.txt");
            Scanner scan  = new Scanner(file);
            int para[] = new int[4];
            int t =0 ;
            while(scan.hasNextLine()){
            	para[t] = (int)(Float.parseFloat(scan.nextLine())*100);
      
            	System.out.println(para[t]);
            	t++;
            }
        
            String line = "";
        for(int i=0;i<1000;i++){
            Socket socket = server.accept();
            System.out.println("Connected");

            socketCont.add(socket);


                System.out.println("&");

                   System.out.println(">>"); 

                      
                        out = new DataOutputStream(socket.getOutputStream());
                        input = new DataInputStream(socket.getInputStream());

                        out.writeUTF("1");
                        out.writeUTF("25");

                        System.out.println("before : " + input.readUTF());


                        System.out.println("<<");

                        out.writeUTF("1");
                        out.writeUTF("25");

                        System.out.println("after : " + input.readUTF());

                   if(i==3){
                        	 System.exit(0); 
                        }

    }

        
            
    }catch(Exception e){
        System.out.println(e);
        }
    }
}