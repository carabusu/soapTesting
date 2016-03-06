import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Map;

/**
 * Created by Mihai on 3/2/2016.
 */
public class createSOAPRequest {
    private Map searchCriteria;
    private SOAPMessage soapMessage;
    private SOAPConnectionFactory soapConnectionFactory;
    private SOAPConnection soapConnection;
    private SOAPMessage soapResponse;
    final String prefix="soap12";
    private String soapMessageXML;


    public createSOAPRequest(Map searchCriteria){
        this.searchCriteria=searchCriteria;


        try {
            soapConnectionFactory = SOAPConnectionFactory.newInstance();
            soapConnection = soapConnectionFactory.createConnection();
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    public createSOAPRequest createSOAPRequest() {
        MessageFactory messageFactory = null;
        try {
            messageFactory = MessageFactory.newInstance();


            SOAPMessage soapMessage = messageFactory.createMessage();

            SOAPPart soapPart = soapMessage.getSOAPPart();


            // SOAP Envelope
            SOAPEnvelope envelope = soapPart.getEnvelope();
            envelope.setPrefix(prefix);
            envelope.removeAttribute("xmlns:SOAP-ENV");
            envelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
            envelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");


            //SOAP Header
            SOAPHeader header = envelope.getHeader();
            header.setPrefix(prefix);

            // SOAP Body
            SOAPBody start = envelope.getBody();
            start.setPrefix(prefix);

            SOAPElement soapBody = envelope.getBody();
            SOAPElement soapBodyGetCities = soapBody.addChildElement("GetCitiesByCountry", "","http://www.webserviceX.NET");

            SOAPElement soapBodyCountryName = soapBodyGetCities.addChildElement("CountryName");
            soapBodyCountryName.addTextNode("Romania");



            //String soapActionPrefix = "http://tempuri.org/IAdminService/";

            MimeHeaders headers = soapMessage.getMimeHeaders();
            headers.addHeader("SOAPAction",  "http://www.webservicex.net/GetCitiesByCountry");


            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer();
            // Set formatting
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            Source sc = soapMessage.getSOAPPart().getContent();

            //OutputStream streamOut = new FileOutputStream(new File(changeFileFolder, "soapRequest.xml"));
            System.out.println("SOAP Request: \n");

            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult( sw);
            tf.transform(sc, result);
            String xmlFileName= searchCriteria.get("ID") + "_SOAP_Request.xml";
            writeXMToFile(sw, xmlFileName);
            System.out.println(sw);
            System.out.println("=====================");
            soapMessageXML=sw.toString();


        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return this;
    }

    public createSOAPRequest runSOAPRequest() {
        //String url = "https://partnerweb.sveaekonomi.se/WebPayAdminService_test/AdminService.svc/backward";
        String url = "http://www.webservicex.com/globalweather.asmx";
        try {
            //soapResponse = soapConnection.call(soapMessage, url);
            soapConnection.close();
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getSOAPResponse(){
        /*
        try {
            //System.out.println("SOAP Response:\n");
            //soapResponse.writeTo(System.out);
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        return soapMessageXML;
    }

    private void writeXMToFile(StringWriter xmlContent, String filename){
        FileWriter fw = null;
        try {
            MyLogger myLogger=MyLogger.getInstance();
            fw = new FileWriter(myLogger.getFolderLog()[0]+"\\" + filename);
            fw.write(xmlContent.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
