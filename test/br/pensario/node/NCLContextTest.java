/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.node;

import br.pensario.NCLDoc;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.interfaces.NCLPort;
import java.net.URISyntaxException;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author joel
 */
public class NCLContextTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLContext cont = new NCLContext("ctx");
        NCLMedia m1 = new NCLMedia("video");
        NCLPort p1 = new NCLPort("pInicio");
        p1.setComponent(m1);

        cont.addNode(m1);
        cont.addPort(p1);

        String expResult = "<context id='ctx'>\n\t<port id='pInicio' component='video'/>\n\t<media id='video'/>\n</context>\n";
        String result = cont.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLContext instance = new NCLContext(reader, null);
            String expResult = "<context id='ctx'>\n\t<port id='pInicio' component='video'/>\n\t<media id='video'/>\n</context>\n";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(expResult)));

            String result = instance.parse(0);
            //System.out.println(result);
            assertEquals(expResult, result);
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><body>"+
                    "<context id='da' refer='db'/>"+
                    "<context id='db'>"+
                    "<media id='m1'/>"+
                    "</context></body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "m1";
            String result = ((NCLMedia) ((NCLContext) instance.getBody().getNodes().iterator().next()).getRefer().getNodes().iterator().next()).getId();
            //System.out.println(result);
            assertEquals(expResult, result);
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }
}