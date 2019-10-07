// Programmer: Izhak Hamidi E01533340 
// Client program
// File name: ih_TCPClient.java

import java.io.*;
import java.net.*;
public class ih_TCPClient
{
    private static InetAddress host;

    public static void main(String[] args)
   {
    	//creating variables to store in arguments, and setting them to default values
    	//hostnum adn username are set to defalt values
    	String username="",hostnum="localhost";
    	int portnum=20450;
    	boolean ureceived=false,preceived=false,hreceived = false;
    	//checking if any argyment was passed
    	if (args.length != 0)
    	{
    		//for every argument, check for -u,-p,-h
    		for(int i=0;i<args.length;i++)
	    		if (args[i].equals("-u")&&!ureceived) {
	    			//-u will allow next arg to be username
	    			username=args[i+1];
	                System.out.println("username recieved");
	                ureceived=true;
	    		}
	            else if (args[i].equals("-p")&&!preceived ) {
	            	//-p will alow next arg to be portnum
	            	portnum=Integer.parseInt(args[i+1]);
	                System.out.println("portnum recieved");
	                preceived=true;
	            }
	            else if (args[i].equals("-h")&&!hreceived) {
	            	//-h will allow next arg to be hostnum
	    	        hostnum=args[i+1];
	            	System.out.println("hosttnum recieved");
	            	hreceived=true;
	            }
    	}
    	
       try {
    	   		// Get server IP-address
                host = InetAddress.getByName(hostnum);
       }
       catch(UnknownHostException e){
           System.out.println("Host ID not found!");
           System.exit(1);
       }
       run(portnum,ureceived,username);
    }

    private static void run(int port,boolean ureceived,String us)
    {
        Socket link = null;
        String user="";
        try{
        // Establish a connection to the server
                 link = new Socket(host,port); 

        // Set up input and output streams for the connection
                BufferedReader in = new BufferedReader(
                new InputStreamReader(link.getInputStream()));
                PrintWriter out = new PrintWriter(
                link.getOutputStream(),true); 
     
       //Set up stream for keyboard entry
                BufferedReader userEntry = new BufferedReader(new InputStreamReader(System.in));
                String message, response;
                
       //Prompting user for username
                if (!ureceived) {
                	System.out.println("Username?:");
                	user = userEntry.readLine();
                }
                else
                {
                	user=us;
                }
       // Get data from the user and send it to the server
                do{
                         System.out.print("Enter message: ");
                         message = userEntry.readLine();
                         out.println(user+": "+message); 
                }while (!message.equals("DONE"));

      // Receive the final report and close the connection
                response = in.readLine();
                System.out.println(response);
                //reading in the final report
                String currline;
                while ((currline = in.readLine()) != null) {
                	System.out.println(currline);

                }
                
      }
      catch(IOException e){
             e.printStackTrace();
      }

      finally{
           try{
                 System.out.println("\n!!!!! Closing connection... !!!!!");
                 link.close(); 
            }

           catch(IOException e){
                  System.out.println("Unable to disconnect!");
                  System.exit(1);
           }

     }

  }

}