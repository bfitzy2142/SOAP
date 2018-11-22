## NET4005 Assignment 3:

### Authors:
* Brad Fitzgerald - 100969645
* Sam Cook - 101004349

#### APIGateway.java
* This is the API Gateway which processes requests from the client, and hands them off to the server.
* This is the file which handles all load balancing. It accomplishes this with a round robin method. First giving `Server 1` and `Server 3` for Services `A` and `B` respectively. After a request has been processed the gateway will give the other server(s), `Server 2` and `Server 4`. It will alternate servers after each request.
* This process also does all of the statistic collection.

* Methods:
  * @class APIGateway: It's job is to receive service requests from the clients and to forward these requests to an appropriate back-end server. The APIGateway is also responsible for load balancing requests between servers.
  * APIGateway constructor. Starts the service by opening service A on server1 and service B on server2. Attributes initialized
  * @method runFunction: Starts processing requests. The method runs with an infinite while loop checking if the return value of each server is equal to something. This is how load balancing is handled, and if one of the If statements evaluates to true, the current Endpoint is stopped, and another is open with the adjacent server. I.e if server1 is open, it is closed and server 2 serves the next request. @return void
  * @method getRequestnum: Getter used by server objects to print the current request number. @return numOfReq attribute
  * @method incReq: Setter method used by server objects to increment the request number before printing it. @return void
	
#### Server<1-4>.java
* Servers `1` and `2` give service `A`, while Servers `3` and `4` give service `B`. 
* The servers are responsible for printing the request number, the information for which is pulled from the API Gateway.
* The servers also print a time stamp for when the request has been competed.
* Methods:
  * @Class Server<1-4>: Communicates with the APIGateway to return a service to the client
  * @method getService<A,B>: Returns a String message to the client and displays the request number and who made the request on the APIGateway program. @return String message to the client
  * @method getReturnValue: Getter method used by the APIGateway to achieve load balancing. @return String message
  * @method setNull: Sets the value of message to null once a request is complete. @return void
	
#### Service<A,B>Interface.java
* This is the interface file, which ensures that the client is calling a method which the server actually has.
* This file also states which Service it is providing.
* Methods:
  * @method getServiceA(): This is the interface for Service A ensuring both the client and the servers have the same methods. @return String

#### Client.java
* This is the file which requests a service. It only talks to the API Gateway.
* The user provides which service it wants with: `java Client [ServiceA, ServiceB]`
* The server responds with a message to the client, which the client prints to the terminal.
* Methods:
  * @method Main(String[]): Method that the client runs, takes in the service requested. Ensures that the input is sanitized. Hands the input to the API Gateway to send the request to a server for processing. @aram args. @throws Exception
