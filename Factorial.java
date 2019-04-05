import java.io.*;
import java.util.*;
public class Factorial {

    public static void main(String[] args) {

        int num = 50;
        long factorial = 1;
        long mod = 1000000007;
        long startTime=System.nanoTime();
        long arr[]=new long[50];
        for(int i = 1; i <= num; ++i)
        {
            // factorial = factorial * i;
            factorial = (factorial*i)%mod;
            long endTime=System.nanoTime();
            arr[i-1]=endTime-startTime;

        }
        String temp="";
        for(int i=0;i<50;i++){
            System.out.print(arr[i]+" ");
            temp = temp + Long.toString(arr[i])+", ";
        }
        try{
        FileOutputStream fo = new FileOutputStream("times.txt");
        byte[] by = temp.getBytes();
        fo.write(by,0,by.length);
        System.out.printf("Factorial of %d = %d", num, factorial);
    }catch(Exception e){
        System.out.println(e);
    }
    }
}