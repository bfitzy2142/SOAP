
/**
 * @author: Brad Fitzgerald, Student-ID: 100969645
 * @author: Samuel Cook, Student-ID: 101004349
 * @version 1.0
 * @date: November 19 2018
 * @title NET 4005 Assignment 3: SOAP 
 */
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * @class APIGateway: It's job is to receive service requests 
 * from the clients and to forward these requests to an appropriate back-end server.
 * The APIGateway is also responsible for load balancing requests between servers.
 * 
 */

@WebService

public class APIGateway {
	
	//Server objects that will be used to test if a client has requested a service 
	Server1 srv1;
	Server2 srv2;
	Server3 srv3;
	Server4 srv4;

	//Endpoint objects that will be used to keep track of open services
	Endpoint endpointA, endpointB;

	//Integer to track the number of requests made by clients
	int numOfReq;

	/*
	 * APIGateway constructor. Starts the service by opening service A on server1 and service B on server2.
	 * Attributes initialized
	 */
	public APIGateway() {	
		System.out.println("Starting SOAP Publisher Service!");
		numOfReq=0;
		srv2 = new Server2(this);
		srv4 = new Server4(this);
		endpointA = Endpoint.publish("http://localhost:9000/ServiceA",srv1 = new Server1(this));
		endpointB = Endpoint.publish("http://localhost:9000/ServiceB",srv3 = new Server3(this));
	}

	/**
	 * @method runFunction: Starts processing requests. The method runs with an infinite while loop checking if the return value
	 * of each server is equal to something. This is how load balancing is handled, and if one of the If statements evaluates to true, 
	 * the current Endpoint is stopped, and another is open with the adjacent server. I.e if server1 is open, it is closed and server 2
	 * serves the next request. 
	 * @return void
	 */
	
	public void runFunction() {
		while (true) {
			if (srv1.getReturnValue() != null) {
				srv1.setNull();
				endpointA.stop();
				endpointA = Endpoint.publish("http://localhost:9000/ServiceA",srv2 = new Server2(this));
			} else if (srv2.getReturnValue() != null) {
				srv2.setNull();
				endpointA.stop();
				endpointA = Endpoint.publish("http://localhost:9000/ServiceA",srv1 = new Server1(this));
			}

			if (srv3.getReturnValue() != null) {
				srv3.setNull();
				endpointB.stop();
				endpointB = Endpoint.publish("http://localhost:9000/ServiceB",srv4 = new Server4(this));
			} else if (srv4.getReturnValue() != null) {
				srv4.setNull();
				endpointB.stop();
				endpointB = Endpoint.publish("http://localhost:9000/ServiceB",srv3 = new Server3(this));
			}

			// Delay to prevent a run condition. An Endpoint need time to close before the next while loop iteration begins.
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
	
	/**
	 * @method getRequestnum: Getter used by server objects to print the current request number
	 * @return numOfReq attribute
	 */
	public int getRequestnum() {
		return numOfReq;
	}
	
	/**
	 * @method incReq: Setter method used by server objects to increment the request number before printing it
	 * @return void
	 */
	public void incReq() {
		numOfReq++;
	}
	
	public static void main(String[] args) {
		// Constructor
		APIGateway newGateway = new APIGateway();
		newGateway.runFunction();

	}

}
