# Toll_calculator
This project emulates server to track and calculate payment for the use of the paid roads. 

Users are registered in the system, and when they pass through the checkpoints, a message is sent to the server with number of the checkpoint, client ID and flag, representing if the client has left the paid zone. When the client leaves the paid zone server calculates the payment for the use of the roads and sends an E-mail to client with corresponding data. Paid road parts a stored in the DB, with start/end checkpoint and pass payment info, as well as users, with their E-mails and a history of their trips(list). Project is connected to test MongoDB on Mlab.
Technologies used for this implementation:
-	Spring Boot
-	Spring Data
-	Maven
-	JUnit
-	MongoDB
-	Sockets
-	JavaEasyMail

