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
			

			Thread t1 = new InputThread();
			//threadCont.add(t1);
			t1.start();

		//while(true){
		
		for(int i=0;i<1000;i++){
			Socket socket = server.accept();

			socketCont.add(socket);

			out = new DataOutputStream(socket.getOutputStream());
			input = new DataInputStream(socket.getInputStream());

			String line1 = input.readUTF();
			String line2 = input.readUTF();
			System.out.println("\n\nNew client connected");
			System.out.println("TOP parameters : " + socket + "::\n" + line1 + "\n");
			System.out.println("TIME UNIT : " + socket + "::\n" + line2);
			System.out.println("------------------------------------------");

			line1 = line1 + line2 + "\n";
	try{
		/*
        FileOutputStream fo = new FileOutputStream(Integer.toString(i));
        byte[] by = line1.getBytes();
        fo.write(by,0,by.length);
        */

        BufferedWriter out1 = new BufferedWriter(new FileWriter("data/" + Integer.toString(i) + ".txt", true)); 
            out1.write(line1); 
            out1.close();

    }catch(Exception e){
        System.out.println(e);
    }

			Thread thread = new ClientHandler(socket,input,out);

			//thread.start();

			if(i==3){
				//getTime(0.1,0.1,0.1);



        int num = 50;
        long factorial = 1;
        long mod = 1000000007;
        long startTime=System.nanoTime();
        long arr[]=new long[50];
        for(int i0 = 1; i0 <= num; ++i0)
        {
            // factorial = factorial * i;
            factorial = (factorial*i0)%mod;
            long endTime=System.nanoTime();
            arr[i0-1]=endTime-startTime;

        }
        String temp="";
        for(int i0=0;i0<50;i0++){
            System.out.print(arr[i0]+" ");
            temp = temp + Long.toString(arr[i0])+", ";
        }
        try{

        BufferedWriter out1 = new BufferedWriter(new FileWriter("data/times.txt", true)); 
            out1.write(temp+"\n"); 
            out1.close();

       // System.out.printf("Factorial of %d = %d", num, factorial);

            
    }catch(Exception e){
        System.out.println(e);
        //System.exit(0);
    }


				double p1, p2;
int mntime = 100000000;
double per1=0, per2=0, per3=0, per4=0;
for(p1=0;p1<=100;p1+=1)
{
	for(p2=p1;p2<=100;p2+=1)
	{
		double lo = p2, hi = 100, m1, m2;
		int time1=100000000, time2;
		m1 = lo+(hi-lo)/3;
		m2 = lo+2*(hi-lo)/3;
		if(m2-m1<=1)
		{
			time1 = getTime(p1, p2-p1, m1-p2);
			mntime = Math.min(mntime, time1);
		}
		while(m2-m1 > 1)
		{
			time1 = getTime(p1, p2-p1, m1-p2);
			time2 = getTime(p1, p2-p1, m2-p2);
			if(time1 > time2)
			{
				lo = m1;
			}
			else{
				hi = m2;
			}
			m1 = lo+(hi-lo)/3;
			m2 = lo+2*(hi-lo)/3;
		}
		if(time1 < mntime)
		{
			mntime = time1;
			per1 = p1;
			per2 = p2-p1;
			per3 = m1-p2;
			per4 =100-(per1 + per2 + per3);
		}
	}

}
System.out.println("min time : " + mntime);
System.out.println(per1 + "|" + per2 + "|" + per3 + "|" + (100-(per1 + per2 + per3)));

	BufferedWriter out1 = new BufferedWriter(new FileWriter("data/ans.txt", true)); 
            out1.write(per1 + "," + per2 + "," + per3 + "," + (100-(per1 + per2 + per3)) + "\n"); 
            out1.close();
            System.exit(0);
			}
		}
	//}
	}catch(Exception e){
		System.out.println(e);
	}finally{
		System.exit(0);
	}
}
	static int individualTime(int sys, int start, int end)
	{	String[] res;
		try{
		 InputThread.sendMsg(sys, Integer.toString(start) + " " + Integer.toString(end));
		 DataInputStream dataInput = new DataInputStream(socketCont.get(sys).getInputStream());
		 res =  dataInput.readUTF().split(" ");
		 return Integer.parseInt(res[0]);
		}catch(Exception e){
			System.out.println("Error in ind time.");
			System.out.println(e);
			return 1;
		}
		 
		
	}
	static int getTime(double p1, double p2, double p3)
	{
		int mx = 100000000;
		int number = 100;
		int left = number, start = 1;
		int time[] = new int[4];
		for(int i=0;i<4;i++)
			time[i] = 0;
		if(left > 0 && p1 != 0)
		{
			int end = (int)((p1/100.0)*number);
			time[0] = individualTime(0, start, start + end-1);
			left -= end;
			start += end;
		}
		if(left > 0 && p2 != 0)
		{
			int end = (int)((p2/100.0)*number);
			time[1] = individualTime(1, start, start + end-1);
			left -= end;
			start += end;
		}
		if(left > 0 && p3 != 0)
		{
			int end = (int)((p3/100.0)*number);
			time[2] = individualTime(2, start, start + end-1);
			left -= end;
			start += end;
		}
		if(left > 0)
		{
			time[3] = individualTime(3, start, number);
		
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
	String line;

	public ClientHandler(Socket socket,DataInputStream input,DataOutputStream out){
		this.socket=socket;
		this.input=input;
		this.out=out;
	}

	public void run(){
		try{

			line = "";
			while(!line.equals("Over")){
				//line = input.readUTF();
				//System.out.println("Client " + socket + " : " +line);
			}
			socket.close();
			input.close();
			out.close();

		}catch(Exception e){

			System.out.println(e);
		}

	}


}

