import java.io.*;
import java.net.*;

public class Client{

	static long factorial(int l, int r)
	{
		int mod = 1000000007;
		long fact = 1;
		for(long i=l;i<=r;i++)
			fact = (fact*i)%mod;
		return fact; 
	}

	public static void makeClient(String ip,int port){
		try{
			Socket socket = new Socket(ip,port);
			DataInputStream input = new DataInputStream(System.in);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream inputFromServer = new DataInputStream(socket.getInputStream());

			int l,r=20;

			l = Integer.parseInt(inputFromServer.readUTF());
			r = Integer.parseInt(inputFromServer.readUTF());


        String line = " ";
        long startTime0 = System.nanoTime();
        factorial(l,r);
        long endTime0 = System.nanoTime();
        long duration0 = (endTime0 - startTime0); 
		line = Long.toString(duration0);
		out.writeUTF(line);
		System.out.println("\nTIME for Equal Distribution : " + line);

		l = Integer.parseInt(inputFromServer.readUTF());
		r = Integer.parseInt(inputFromServer.readUTF());

		line = " ";
        startTime0 = System.nanoTime();
        factorial(l,r);
        endTime0 = System.nanoTime();
        duration0 = (endTime0 - startTime0); 
		line = Long.toString(duration0);
		out.writeUTF(line);
		System.out.println("\nTIME for ML Distribution : " + line);


        	
		}catch(Exception e){
			System.out.println(e);
		}

	}
	public static void main(String args[]){
		makeClient("127.0.0.1",5005);
	}
}