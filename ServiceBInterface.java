
/**
 * @author: Brad Fitzgerald, Student-ID: 100969645
 * @author: Samuel Cook, Student-ID: 101004349
 * @version 1.0
 * @date: November 19 2018
 * @title NET 4005 Assignment 3: SOAP 
 */
import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService(targetNamespace = "http://localhost", serviceName = "ServiceA")

public interface ServiceBInterface
{
    @WebMethod
    public String getServiceB();
}