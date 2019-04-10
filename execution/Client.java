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


			Process p;
			String bashOut="";
         
         try{
         	String[] cmd = { "sh", "./script.sh"};
         	p = Runtime.getRuntime().exec(cmd); 
            p.waitFor();
            BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
           	String temp = "";
           	while((bashOut = reader.readLine()) != null) { 
            	System.out.println("TOP parameters form client : " + bashOut);
            	temp = temp + bashOut;
            	
            }
            out.writeUTF(temp);
            
        }catch(Exception e){
        	System.out.println("Error in bash");
        	System.out.println(e);
        }
        String line = " ";

        long startTime0 = System.nanoTime();
        factorial(0,50);
        long endTime0 = System.nanoTime();
        long duration0 = (endTime0 - startTime0); 
		line = Long.toString(duration0);
		out.writeUTF(line);
        	
		}catch(Exception e){
			System.out.println(e);
		}

	}
	public static void main(String args[]){
		makeClient("127.0.0.1",5005);
	}
}