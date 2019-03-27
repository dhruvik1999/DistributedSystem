import java.net.*;
import java.io.*;
import java.util.*;


public class Server{
	static ArrayList<Socket> socketCont = new ArrayList<Socket>();
	public static void main(String args[]){
		try{
			ServerSocket server = new ServerSocket(5001);
			DataInputStream input;
			DataOutputStream out;
			

			Thread t1 = new InputThread();
			t1.start();

		while(true){
		
			Socket socket = server.accept();
			socketCont.add(socket);

			out = new DataOutputStream(socket.getOutputStream());
			input = new DataInputStream(socket.getInputStream());


			System.out.println("New client connected");

			Thread thread = new ClientHandler(socket,input,out);

			thread.start();
		
	}
	}catch(Exception e){
		System.out.println(e);
	}


	}

	static class InputThread extends Thread{

	public void run(){
		String line;
		Scanner scan = new Scanner(System.in);
		while(true){
			line = scan.nextLine();
			System.out.println(line);
			System.out.println(socketCont);
		}
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

