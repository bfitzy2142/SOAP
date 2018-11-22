
/**
 * @author: Brad Fitzgerald, Student-ID: 100969645
 * @author: Samuel Cook, Student-ID: 101004349
 * @version 1.0
 * @date: November 19 2018
 * @title NET 4005 Assignment 3: SOAP 
 */
import javax.jws.WebService;
import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import com.sun.net.httpserver.HttpExchange;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebService(targetNamespace = "http://localhost", serviceName = "ServiceB", endpointInterface = "ServiceBInterface", portName = "Server4port")

/**
 * @Class Server4: Communicates with the APIGateway to return a service to the
 *        client
 */
public class Server4 implements ServiceBInterface {
	// message attribute to be returned to client
	String message;
	// Instance of the APIGateway class used to update request stats
	APIGateway publisher;
	// Date format
	SimpleDateFormat time = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss aa");

	public Server4(APIGateway publisher) {
		this.publisher = publisher;
	}

	@Resource
	WebServiceContext wsContext;

	/**
	 * @method getServiceB: Returns a String message to the client and displays the
	 *         request number and who made the request on the APIGateway program
	 * @return String message to the client
	 */
	public String getServiceB() {
		publisher.incReq();
		MessageContext con = wsContext.getMessageContext();
		HttpExchange ex = (HttpExchange) con.get("com.sun.xml.internal.ws.http.exchange");
		InetSocketAddress remAddr = ex.getRemoteAddress();
		String host = remAddr.getHostName();
		InetAddress remoteAddr = remAddr.getAddress();
		System.out.println("REQ:" + publisher.getRequestnum() + " " + time.format(new Date()) + "\nClient: '" + host
				+ "' connected with the address '" + remoteAddr + "' on Server4 for ServiceB!");
		message = "This is Server 4, giving you Service B!";

		return message;
	}

	/**
	 * @method getReturnValue: Getter method used by the APIGateway to achieve load
	 *         balancing
	 * @return String message
	 */
	public String getReturnValue() {
		return message;

	}
	/**
	 * @method setNull: Sets the value of message to null once a request is complete
	 * @return void
	 */
	public void setNull() {
		message = null;
	}
}