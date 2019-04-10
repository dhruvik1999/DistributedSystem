import java.net.*;
import java.io.*;
import java.util.*;


public class Server{
    static ArrayList<Socket> socketCont = new ArrayList<Socket>();
    static ArrayList<Thread> threadCont = new ArrayList<Thread>();

    public static void main(String args[]){
        try{
            int time1 = 0;
            int time2 = 0;
            ServerSocket server = new ServerSocket(5005);
            DataInputStream input;
            DataOutputStream out;

            File file = new File("./output.txt");
            Scanner scan  = new Scanner(file);	
       
            int para[] = new int[4];
            int t =0 ;
            while(scan.hasNextLine()){
            	para[t] = (int)(Float.parseFloat(scan.nextLine())*100);
            	//System.out.println("Task for Client"para[t]);
            	t++;
            }
            for(int i=1;i<4;i++){

            	para[i] = para[i] + para[i-1] + 1;
            }



            String line = "";
        for(int i=0;i<1000;i++){
            Socket socket = server.accept();
            System.out.println("\n\nClient Connected :");

            socketCont.add(socket);

                        System.out.println("Client " + i + " ready to execute load");
                      
                        out = new DataOutputStream(socket.getOutputStream());
                        input = new DataInputStream(socket.getInputStream());

                        out.writeUTF(Integer.toString(i*25));
                        out.writeUTF(Integer.toString((i+1)*25));

                        int atime = Integer.parseInt(input.readUTF());
                        time1 += atime;

                        System.out.println("TIME for Equal Distribution : " + atime + "\n");


                        if(i==0){
                             out.writeUTF(Integer.toString(0));
                             out.writeUTF(Integer.toString(para[0]));
                        }else{
                            out.writeUTF(Integer.toString(para[i-1]));
                            out.writeUTF(Integer.toString(para[i]));
                        }

                        int btime = Integer.parseInt(input.readUTF());
                        time2 += btime;

                        System.out.println("TIME for ML Distribution : " + btime+ "\n");


                        System.out.println("------------------------------------------------------------\n");

                   if(i==3){

                        
                            System.out.println("Total time for equal distribution : " + time1);
                            System.out.println("Total time for ML distribution : " + time2);

                            float par = (float)(time1 - time2)/time1;

                            System.out.println("percentage of reduced time : " + par*100 + "%");

                            System.exit(0); 
                        }

    }

        
            
    }catch(Exception e){
        System.out.println(e);
        }
    }
}