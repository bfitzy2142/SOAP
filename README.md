## NET4005 Assignment 3:

#### APIGateway.java
* This is the API Gateway which processes requests from the client, and hands them off to the server.
* This is the file which handles all load balancing. It accomplishes this with a round robin method. First giving `Server 1` and `Server 3` for Services `A` and `B` respectively. After a request has been processed the gateway will give the other server(s), `Server 2` and `Server 4`. It will alternate servers after each request.
* This process also does all of the statistic collection.
	
#### Server<1-4>.java
* Servers `1` and `2` give service `A`, while Servers `3` and `4` give service `B`. 
* The servers are responsible for printing the request number, the information for which is pulled from the API Gateway.
* The servers also print a time stamp for when the request has been competed.
	
#### Service<A,B>Interface.java
* This is the interface file, which ensures that the client is calling a method which the server actually has.
* This file also states which Service it is providing.

#### Client<1-2>.java
* This is the file which requests a service. It only talks to the API Gateway.
* The user provides which service it wants with: `java Client<1-2> [ServiceA, ServiceB]`
* The server responds with a message to the client, which the client prints to the terminal.
