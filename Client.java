
/**
 * @author: Brad Fitzgerald, Student-ID: 100969645
 * @author: Samuel Cook, Student-ID: 101004349
 * @version 1.0
 * @date: November 19 2018
 * @title NET 4005 Assignment 3: SOAP 
 */
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class Client {
	public static void main(String[] args) throws Exception {

		try {
			// Turn Args into variables
			String serviceName = args[0];

			if ((serviceName.equals("ServiceA") || serviceName.equals("ServiceB")) && args.length == 1) {
				// Do the client stuff
				URL url = new URL("http://localhost:9000/" + serviceName + "?wsdl");
				QName name = new QName("http://localhost", serviceName);
				Service service = Service.create(url, name);
				if (serviceName.equals("ServiceA")) {
					ServiceAInterface svcA = service.getPort(ServiceAInterface.class);
					System.out.println(svcA.getServiceA());
				} else if (serviceName.equals("ServiceB")) {
					ServiceBInterface svcB = service.getPort(ServiceBInterface.class);
					System.out.println(svcB.getServiceB());
				}
			} else {
				System.out.println("Please enter a valid service");
				System.out.println("Usage:\n java Client ServiceA\n java Client ServiceB");
			}
		} catch (Exception ex) {
			String e = ex.getClass().toString();
			if (e.toString().equals("class java.lang.ArrayIndexOutOfBoundsException")) {
				System.out.println("Error!\nPlease provide an argument!");
				System.out.println("Usage:\n java Client ServiceA\n java Client ServiceB");
			} else {
				System.out.print(ex);
			}

		}
	}
}