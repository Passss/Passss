# Special logger

The special logger is a springboot application that provides REST API endpoints for basic logging operations.
This application starts a H2 in-memory database.

# Code structure

The source code is structured into a controller, service, JPA repository and a JAVAX entity.

# Prerequesites

The following needs to be installed
Java SE 17+
Apache maven

#Operations

The following are the API operations supported

- GET "/"
The default path returns a string "Hello World!"

- GET "/get/{logId}"
This endpoint returns the logs given a logId.

- POST "/createorupdate"
This endpoint creates a LoggingEntity object in the database. The input is furnished as a POST request with a request JSON body
of LoggingEntity type. Here is an example.

{
    "logId": "5555",
    "name": "pasu",
    "message": "Special message from pasu"
}

- DELETE "/delete/{logId}"
This endpoint deletes an entry from LoggingEntity object in the database, given a logId.

- GET "/maxagelimit/{customMaxAgeLimit}"
This endpoint helps configure the customMaxAgeLimit in seconds. There is a asynchronous background spring scheduled job that
deletes all entries from LoggingEntity table with created date greater than "customMaxAgeLimit" seconds.

- GET "/getcurrentstate"
This endpoint returns the current state information of the special logger. This returns a JSON body of type LoggingState. Here
is an example

{
	"currentNumberOfLogs": 1,
	"maxAgeLimit": 74,
	"noOfStoredMessages": 1
}


#To run the application run the following from command line.

## mvn clean install

The above command creates the jar under target/ The name of the jar is demo-0.0.1-SNAPSHOT.jar

The application can be started by running the following
## java -jar demo-0.0.1-SNAPSHOT.jar

The same can be run from an IDE of your choice after importing the project.
