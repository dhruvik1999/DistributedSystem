import java.io.*;
import java.net.*;

public class Client{

	public static void makeClient(String ip,int port){
		try{
			Socket socket = new Socket(ip,port);
			DataInputStream input = new DataInputStream(System.in);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());


			String line = " ";
			while(!line.equals("Over")){
				line = input.readLine();
				out.writeUTF(line);
			}

		}catch(Exception e){
			System.out.println(e);
		}


	}
	public static void main(String args[]){
		makeClient("127.0.0.1",5000);
	}
}