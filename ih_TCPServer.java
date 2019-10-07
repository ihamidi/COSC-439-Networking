// Programmer: Izhak Hamidi E01533340

// Server program
// File name: "ih_TCPServer.java"

import java.io.*;
import java.util.*;
import java.net.*;

public class ih_TCPServer
{
     private static ServerSocket servSock;
     public static void main(String[] args)
    {
    	//HARDCODED PORTNUM
    	int portnum=20450;
    	//Checking forn the portnum argument
    	if (args.length != 0)
    	{
	     	if (args[0].equals("-p"))
	     	{
	     		portnum=Integer.parseInt(args[1]);
	     	}

    	}
        System.out.println("Opening port..."+portnum+"\n");
        try{

       // Create a server object 
              servSock = new ServerSocket(portnum); 
        }

        catch(IOException e){
             System.out.println("Unable to attach to port!");
             System.exit(1);
        }

       do 
       {  
            run();
       }while (true);

    }

    private static void run()
   {
        Socket link = null; 
        try{

 

        // Put the server into a waiting state
               link = servSock.accept(); 

        // Set up input and output streams for socket
              BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream())); 
              PrintWriter out = new PrintWriter(link.getOutputStream(),true);
        //Setting up printwriter to write to file
              File file = new File ("ih_chat.txt");
              PrintWriter tofile = new PrintWriter (file.getName());

    // print local host name
              String host = InetAddress.getLocalHost().getHostName();
              long starttime = System.currentTimeMillis();
              System.out.println("Client has estabished a connection to " + host);

    // Receive and process the incoming data 
             int numMessages = 0;
             String message = in.readLine();
             //reading in and printing all messages on server console
             while (!message.substring(message.indexOf(":")+2).equals("DONE"))
             {
                 System.out.println(message);
                 numMessages ++;
                 tofile.println(message);
                 tofile.flush();
                 message = in.readLine();

             }
             // getting edn time for session
             long elapsed=System.currentTimeMillis()-starttime;
             String time=convertToTime(elapsed);
             
            // Send a report back and close the connection
            
            Scanner frdr= new Scanner(file);
            String currline="";
            

            //printing out session time
            int i=0;
            while(frdr.hasNextLine()){
                currline += frdr.nextLine()+"\n";
            }
            //sending final report to client side
            out.println("Server received " + numMessages + " messages \n"+currline+"\n Session Time: "+time);
            //closing all readers and writers
            frdr.close();
            out.close();
            tofile.close();
            in.close();
            //deleting chat file at the end of session
            file.delete();
      }

      catch(IOException e){
          e.printStackTrace();
      }

      finally{
           try{
                System.out.println("!!!!! Closing connection... !!!!!\n" + "!!! Waiting for the next connection... !!!");
                link.close(); 
                
           }

           catch(IOException e){
                 System.out.println("Unable to disconnect!");
                System.exit(1);
           }
    }

  }
    /**
     * Method to convert milliseconds to a readable format
     * returns hh:mm:ss:milli
     */
    public static String convertToTime(long x)
    {
    	//using integer dis=vision and modulus to return readable value
    	String s="";
    	long hours = (x / 1000) / (60*60);
        long minutes = (x / 1000) / 60;
        long seconds = (x / 1000) % 60;
    	long milliseconds= (x%1000);
    	s= hours+":"+minutes+":"+seconds+":"+milliseconds;
    	return s;
    }

}