# DistributedHashMap
1. Running as App
	- cd <PATH/DistributedHashMap>
	- javac -d bin src\main\java\com\assignment\DistributedHashMap\*.java
	- java -cp bin com.assignment.DistributedHashMap.App
	- DistributedHashMap App
	```
		Open multiple terminals and use following commands
		Usage: java -cp  DistributedHashMap-0.0.jar com.assignment.DistributedHashMap.App
			Commands allowed :
				put <key> <value>
				get <value>
				clear
				display
				quit
	```
	
2. Running Rest Api
	- mvn clean install -DskipTests=true
	- java -jar target/DistributedHashMap-0.0.jar
	- http://localhost:8080/put?key=k2&value=v4456
	- http://localhost:8080/get?key=k2
	- http://localhost:8080/display
	
