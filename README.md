The goal is to design an accurate positioning system using smart phone device devices. 
Using coordinates given by the GPS satellite, a position will be synthesized to present a real-time of the location of the device. 
Magnitude and latitude is sent via wifi and stored. The same system could be deployed on virtually any type of smart phone device. 

Technology and Tools:
We are going see how to make a very simple Android app to send location of mobile phone and will call a PHP script to perform basic storage operations.
Technology used:
	Android
	PHP
  MySQL

Tools used:
	Android studio
	MySQL server
	Net beans
	XAMP (PHP server)
	Samsung kies (Samsung USB drivers)


A request is made to a PHP script TestGPS.php to create a new location in our database through HTTP post.
TestGPS.php connects to your MySQL database to perform the insertion operation.
After getting JSON response from TestGPS.php , If success bit is 1 then a show message “sent and saved” is displayed on our app.

