import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.util.Date;
import java.util.Scanner;
public class Server {
	static ArrayList<Service> list = new ArrayList<Service>();
    
	public static void main(String[] args) throws IOException 
	{		    	    	
        int portNumber = 4444;
        try (ServerSocket serverSocket =new ServerSocket(portNumber);) {
            while(true) {
            	System.out.println("[+] Server is up");
            	new EchoServerThread(serverSocket.accept()).start();
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
    }

}


	
		




