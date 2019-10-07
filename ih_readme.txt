
#####################################
Izhak Hamidi E01533340
CSOC439 Computer Networking Principles
Project 1
#####################################
Project Description
This project is a simple Client Server program.
The client connects to the server using the portnumber and IP.
This is accompished using java's Socket class
It will display any messages sent from the client to the server on the server side adn log them into a chat file
Once the session is eded when the user enters "DONE". It will relay the messages back to the server along with how long the session lasted.



To Run this program:
Run the server java file on GCP or locally using java ih_TCPServer -p 20450	(-p and 20450 are an optional portnumber argument)
Once the server is started, you will need to run the client java file on command line
This file takes three optional arguments eachwith a prefix of -p,-h, or -u. (port, host, username)
If you are connecting to the server that is not local, you will need to use -h "hostname"
Four example of a valid command: java ih_TCPClient.java -p 20450
java ih_TCPClient.java -u iham -p 20450
java ih_TCPClient.java
java ih_TCPClient.java -h 127.0.0.1 -p 20450 -u iham
After one of these commands are entered, you will get a message in the server saying that "Client has connected to", which confirms that the connection is succesful
After you are done interacting with the server type in "DONE" and the client will disconnect from the server, the server will send back a report of messages as well as a session time.





Conclusion:
I enjoyed learning about java's classes that aid in networking.
This project tok around 6 hours to complete from start to finsih.
Problems that I have encountered:
-File.delete method will not delete the file unless all buffers/readersconnect to the file are closed.
-Remembering to read in the messages sent by the server on the client side using in.readline.
Improvements:
-an introduction to how the Socket library in java works before the program is assigned.
-Maybe use Java's JPanel class to implement a console box to allow for easier testing than command line (can also have user input information such as port and username right into the Componennt)