
import j2html.tags.ContainerTag;
import org.junit.Assert;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.*;
import org.xmlunit.*;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static j2html.TagCreator.*;


/**
 * Created by Mihai on 3/2/2016.
 */
public class testXML {
    @Test
    //public static void main(String args[]) throws Exception {
    public  void main() throws Exception {

      ContainerTag sss=  html().with(
              head().with(
                      title("Title"),
                      link().withRel("stylesheet").withHref("/css/main.css")
              ),
              body().with(
                                                    h1("Heading!")

                      )
              );


        File file = new File("F:\\github\\soapTesting\\src\\main\\resources\\report.html");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(sss.render());
        fileWriter.flush();
        fileWriter.close();





        //https://github.com/xmlunit/xmlunit/blob/master/xmlunit-core/src/test/java/org/xmlunit/builder/DiffBuilderTest.java

        String control = "<root><prefix1:element xmlns:prefix1=\"namespace\" prefix1:attribute=\"x\">Some text</prefix1:element></root>";
        String test = "<root><prefix2:element xmlns:prefix2=\"namespace\" prefix2:attribute=\"x\">Some text1</prefix2:element> <element gigi=\"bau\">test</element></root>";
        final List<Difference> diffs = new ArrayList<Difference>();

        final ComparisonListener comparisonListener = new ComparisonListener() {
            @Override
            public void comparisonPerformed(Comparison comparison, ComparisonResult outcome) {
                diffs.add(new Difference(comparison, outcome));
            }


        };

        try
        {
            Diff myDiff = DiffBuilder.compare(control).withTest(test)
                    .withDifferenceEvaluator(new IgnoreAttributeDifferenceEvaluator("prefix1"))
                    .withDifferenceEvaluator(new IgnoreAttributeDifferenceEvaluator("prefix2"))
                    .checkForIdentical()
                    .ignoreWhitespace()
                    .ignoreComments()
                    //.withComparisonListeners(comparisonListener)
                    .withDifferenceListeners(comparisonListener)
                    .build();

            // validate result

            Iterator<Difference> crunchifyIterator =myDiff.getDifferences().iterator();
            while (crunchifyIterator.hasNext()) {
                Difference unitDiff=crunchifyIterator.next();
                Comparison comp=unitDiff.getComparison();
                System.out.println("comparison type:" + comp.getType().name());


                Comparison.Detail controlDet=comp.getControlDetails();






                System.out.println("");
                //System.out.println(crunchifyIterator.next());
            }



            System.out.println("");
            Assert.assertFalse(myDiff.toString(), myDiff.hasDifferences());





            //assertTrue( "Similar", diff.similar() );
            //assertTrue( "Identical", diff.identical() );
        }
        catch ( Exception e )
        {
            fail( e.getMessage() );
        }

    }
    private final class IgnoreAttributeDifferenceEvaluator implements DifferenceEvaluator {

        private String attributeName;

        public IgnoreAttributeDifferenceEvaluator(String attributeName) {
            this.attributeName = attributeName;
        }

        @Override
        public ComparisonResult evaluate(Comparison comparison, ComparisonResult outcome) {
            final Node controlNode = comparison.getControlDetails().getTarget();
            if (controlNode instanceof Attr) {
                Attr attr = (Attr) controlNode;
                //if (attr.getName().equals(attributeName)) {
                if (attr.getName().contains(attributeName)) {
                    System.out.println("atr name:" +attr.getName()+"---"+attr.getPrefix());
                    return ComparisonResult.EQUAL;
                }
            }
            return outcome;
        }
    }

}
