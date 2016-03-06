import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mihai on 3/2/2016.
 */
public class test {

    public static void main(String args[]) throws Exception {

        HashMap<String, String> hmap = new HashMap<String, String>();
        hmap.put("ID", "2");
        hmap.put("12", "Chaitanya");


        createSOAPRequest mySOAPRequest=new createSOAPRequest(hmap);

        mySOAPRequest.createSOAPRequest().runSOAPRequest().getSOAPResponse();



        // Create SOAP Connection
        /*
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        // Send SOAP Message to SOAP Server
        String url = "https://partnerweb.sveaekonomi.se/WebPayAdminService_test/AdminService.svc/backward";
        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);

        // print SOAP Response
        System.out.print("Response SOAP Message:");
        soapResponse.writeTo(System.out);
        */



        /*
        GetOrdersResponse response = parseResponse(soapResponse, "GetOrdersResponse");
        System.out.println(response.isOrderAccepted());
        System.out.println(response.getResultCode());
        System.out.println(response.getErrorMessage());

        System.out.println(response.getChangedDate());
        System.out.println(response.getClientId());
        System.out.println(response.getClientOrderId());
        System.out.println(response.getCreatedDate());
        System.out.println(response.getCreditReportStatusAccepted());
        System.out.println(response.getCreditReportStatusCreationDate());
        */
    }

}
