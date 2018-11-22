
/**
 * @author: Brad Fitzgerald, Student-ID: 100969645
 * @author: Samuel Cook, Student-ID: 101004349
 * @version 1.0
 * @date: November 19 2018
 * @title NET 4005 Assignment 3: SOAP 
 */
import javax.jws.WebService;
import javax.xml.ws.Endpoint;


@WebService

public class APIGateway {

	Server1 srv1;
	Server2 srv2;
	Server3 srv3;
	Server4 srv4;

	Endpoint endpointA, endpointB;

	int numOfReq;

	public APIGateway() {	
		System.out.println("Starting SOAP Publisher Service!");
		numOfReq=0;
		srv2 = new Server2(this);
		srv4 = new Server4(this);
		endpointA = Endpoint.publish("http://localhost:9000/ServiceA",srv1 = new Server1(this));
		endpointB = Endpoint.publish("http://localhost:9000/ServiceB",srv3 = new Server3(this));
	}

	public static void main(String[] args) {
		// Constructor
		APIGateway newGateway = new APIGateway();
		newGateway.runFunction();

	}

	public void runFunction() {

		
		while (true) {
			// System.out.println(srv1.getReturnValue());
			if (srv1.getReturnValue() != null) {
				//numOfReq++;
				srv1.setNull();
				endpointA.stop();
				endpointA = Endpoint.publish("http://localhost:9000/ServiceA",srv2 = new Server2(this));
			} else if (srv2.getReturnValue() != null) {
				//numOfReq++;
				srv2.setNull();
				endpointA.stop();
				endpointA = Endpoint.publish("http://localhost:9000/ServiceA",srv1 = new Server1(this));
			}

			if (srv3.getReturnValue() != null) {
				//numOfReq++;
				srv3.setNull();
				endpointB.stop();
				endpointB = Endpoint.publish("http://localhost:9000/ServiceB",srv4 = new Server4(this));
			} else if (srv4.getReturnValue() != null) {
				//numOfReq++;
				srv4.setNull();
				endpointB.stop();
				endpointB = Endpoint.publish("http://localhost:9000/ServiceB",srv3 = new Server3(this));
			}

			// Delay so state can change
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
	
	public int getRequestnum() {
		return numOfReq;
	}
	
	public void incReq() {
		numOfReq++;
	}

}
