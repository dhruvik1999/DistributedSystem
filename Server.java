import java.net.*;
import java.io.*;


public class Server{
	public static void main(String args[]){
		try{
			ServerSocket server = new ServerSocket(5000);
			DataInputStream input;
			DataOutputStream out;
		while(true){
		
			Socket socket = server.accept();

			out = new DataOutputStream(socket.getOutputStream());
			input = new DataInputStream(socket.getInputStream());


			System.out.println("New client connected");

			Thread thread = new ClientHandler(socket,input,out);

			thread.start();
		
	}
	}catch(Exception e){

		}


	}
}

class ClientHandler extends Thread{

	Socket socket;
	DataInputStream input;
	DataOutputStream out;

	public ClientHandler(Socket socket,DataInputStream input,DataOutputStream out){
		this.socket=socket;
		this.input=input;
		this.out=out;
	}

	public void run(){
		try{

			String line = "";
			while(!line.equals("Over")){
				line = input.readUTF();
				System.out.println("Client " + socket + " : " +line);
			}
			socket.close();
			input.close();
			out.close();

		}catch(Exception e){

			System.out.println(e);
		}

	}
}