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
        
            String line = "";
        for(int i=0;i<1000;i++){
            Socket socket = server.accept();

            socketCont.add(socket);

            out = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());

            String line1 = input.readUTF();
            String line2 = input.readUTF();
            System.out.println("\n\n New client connected");
            System.out.println("TOP parameters form client : " + socket + "::\n" + line1 + "\n");
            System.out.println("time unit for client : " + socket + "::\n" + line2);
            System.out.println("------------------------------------------");

            line = line +  line1  + line2 + ",";
    
            if(i==3){

                int num = 50;
                long factorial = 1;
                long mod = 1000000007;
                long startTime=System.nanoTime();
                long arr[]=new long[50];
                for(int i0 = 1; i0 <= num; ++i0)
                    {
                    factorial = (factorial*i0)%mod;
                    long endTime=System.nanoTime();
                    arr[i0-1]=endTime-startTime;
                    }
                String temp="";
                for(int i0=0;i0<50;i0++){
                    System.out.print(arr[i0]+" ");
                    temp = temp + Long.toString(arr[i0])+", ";
                }
                line = line + temp;   
                /*
                    
                try{
                    BufferedWriter out1 = new BufferedWriter(new FileWriter("data/times.txt", true)); 
                    out1.write(temp+"\n"); 
                    out1.close();
                }catch(Exception e){
                    System.out.println(e);
                }
                */
           break;
        }
    }

    try{
                    BufferedWriter out1 = new BufferedWriter(new FileWriter("data/input.txt", true)); 
                    out1.write(line+"\n"); 
                    out1.close();
                }catch(Exception e){
                    System.out.println(e);
                }

                 System.exit(0);  
            


    }catch(Exception e){
        System.out.println(e);
        }
    }
}
