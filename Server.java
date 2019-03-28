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

		//while(true){
		
		for(int i=0;i<1000;i++){
			Socket socket = server.accept();
			socketCont.add(socket);

			out = new DataOutputStream(socket.getOutputStream());
			input = new DataInputStream(socket.getInputStream());


			System.out.println("New client connected");

			Thread thread = new ClientHandler(socket,input,out);

			thread.start();

			if(i==3){
				getTime(0.1,0.1,0.1);
			}
		}
	//}
	}catch(Exception e){
		System.out.println(e);
	}
}
	static int  individualTime(int sys, int start, int end)
	{
		 InputThread.sendMsg(sys, "Take this!!!");	
		 return 1;
	}
	static int getTime(double p1, double p2, double p3)
	{
		int mx = 100000000;
		int number = 100000000;
		int left = number, start = 1;
		int time[] = new int[4];
		for(int i=0;i<4;i++)
			time[i] = mx;
		if(left > 0 && p1 != 0)
		{
			int end = (int)(p1*number);
			end = number/4;
			time[0] = individualTime(0, start, start + end-1);
			left -= end;
			start += end;
		}
		if(left > 0 && p2 != 0)
		{
			int end = (int)(p1*number);
			end = number/4;
			time[1] = individualTime(1, start, start + end-1);
			left -= end;
			start += end;
		}
		if(left > 0 && p3 != 0)
		{
			int end = (int)(p1*number);
			end = number/4;
			time[2] = individualTime(2, start, start + end-1);
			left -= end;
			start += end;
		}
		if(left > 0)
		{
			int end = (int)(p1*number);
			time[3] = individualTime(3, start, number);
			left -= end;
			start += end;
		}
		int mxTime = 0;
		for(int i=0;i<4;i++)
			mxTime = Math.max(mxTime, time[i]);
		return mxTime;

	}






	static class InputThread extends Thread{

	public void run(){
		String line;
		Scanner scan = new Scanner(System.in);
		try{
		while(true){
			int sysnum = scan.nextInt();
			line = scan.nextLine();
			System.out.println(line);
			//System.out.println(socketCont);
			sendMsg(sysnum,line);	
		}
	}catch(Exception e){
		System.out.println("Input Formate Error");
	}
	}

	public static void  sendMsg(int socketNumber,String msg){
		try{
			Socket socket = socketCont.get(socketNumber);
			DataOutputStream msgOutput = new DataOutputStream(socket.getOutputStream());
			msgOutput.writeUTF(msg);
			System.out.println("message sent : " + socketNumber + " : " + msg);
		}catch(Exception e){
			System.out.println("Error in sendMsg class : InputThread");
			System.out.println(e);
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

