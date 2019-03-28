import java.io.*;
import java.net.*;

public class Client{

	public static void makeClient(String ip,int port){
		try{
			Socket socket = new Socket(ip,port);
			DataInputStream input = new DataInputStream(System.in);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream inputFromServer = new DataInputStream(socket.getInputStream());


			String line = " ";
			while(!line.equals("Over")){
				String resp = inputFromServer.readUTF(); 
				String[] arrOfStr = resp.split(" "); 

				System.out.println("Server : " + resp);

				System.out.println(arrOfStr[0] + " " + arrOfStr[1]);

				int l = Integer.parseInt(arrOfStr[0]);
				int r = Integer.parseInt(arrOfStr[1]);

				System.out.println(l + " " + r);

				line = "result from client : " + socket;

				long startTime = System.nanoTime();
				//methodToTime();
				//int i =0;
				for(int i=0;i<1000000000;i++){
					;
					
				}
				long endTime = System.nanoTime();

				long duration = (endTime - startTime); 

				line = Long.toString(duration);
				System.out.println(line);

				out.writeUTF(line);
			}

		}catch(Exception e){
			System.out.println(e);
		}

	}
	public static void main(String args[]){
		makeClient("127.0.0.1",5001);
	}
}