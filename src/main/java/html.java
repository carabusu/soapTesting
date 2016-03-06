import htmlflow.HtmlView;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;


import j2html.tags.ContainerTag;
import junit.framework.Assert;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import htmlflow.HtmlView;
import htmlflow.ModelBinder;
import htmlflow.elements.ElementType;
import htmlflow.elements.HtmlTable;
import htmlflow.elements.HtmlTr;
import static j2html.TagCreator.*;

/**
 * Created by Mihai on 3/3/2016.
 */
public class html {

    public static void main(String [] args) throws IOException {

        HashMap<String, String> hmap = new HashMap<String, String>();
        hmap.put("ID", "2");
        hmap.put("12", "Chaitanya");


        createSOAPRequest mySOAPRequest=new createSOAPRequest(hmap);

        String soapRequest=mySOAPRequest.createSOAPRequest().runSOAPRequest().getSOAPResponse();


        Velocity.init();
        Template t = Velocity.getTemplate("./templates/demoTye.html");

        VelocityContext context = new VelocityContext();

        context.put("value1", "text1111");
        context.put("value2", "text452342");
        context.put("scenarioID", "1");
        context.put("scenarioName", "Search by rxm and partner name");

        context.put("executionStatusClass", "fail");
        context.put("executionStatus", "FAIL");
        context.put("doxSOAPRequest", soapRequest);

        ArrayList<String> objs =new ArrayList();
        objs.add("asda");
        objs.add("derg");
        objs.add("tyjyh");



        context.put("objs", objs);


        Writer writer = new StringWriter();
        t.merge(context, writer);


        System.out.println(writer);

        File fileOut = new File("./templates/outputHTML.html");
        FileWriter fileWriter = new FileWriter(fileOut);
        fileWriter.write(String.valueOf(writer));

        fileWriter.flush();
        fileWriter.close();


    }
}




/*



        ContainerTag testTag = new ContainerTag("a");
        testTag.setAttribute("href", "http://example.com");

        ContainerTag testTag1 = a("gigi").attr("href","www.yahoo.com");

        ContainerTag testTag2=table();

       String htmlFile= html().with(
                head().with(
                        title("Title"),
                        link().withRel("stylesheet").withHref("/css/main.css")
                ),
                body().with(

                                h1("Heading!"),
                        testTag,
                        testTag1

                )
        ).render();

        System.out.println("html:" + htmlFile);

        File fileOut = new File("Task.html");
        FileWriter fileWriter = new FileWriter(fileOut);
        fileWriter.write(htmlFile);

        fileWriter.flush();
        fileWriter.close();

 */