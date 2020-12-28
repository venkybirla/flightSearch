# flightSearch
1. Import this project with maven.
2. Install and run redis on your system  on "6379" port.
3. Run "FlightSearchApplication.java" file to run the application.
4. Application will run on 8081 port.
5. You can add/remove more flight or bus routes in "transportinfo.txt" file in resource folder. After updating the file. Run these following cally:
	http://localhost:8081/flight/routes/save
6. You can search the flights beween source and destination using following url:-
	http://localhost:8081/flight/search/{from}/{to}/{date}
	Note date format - yyyymmdd.