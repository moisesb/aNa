/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.link;

import br.pensario.NCLDoc;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLParamInstance;
import br.pensario.connector.NCLConnectorParam;
import br.pensario.connector.NCLRole;
import br.pensario.connector.NCLSimpleCondition;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.NCLArea;
import br.pensario.node.NCLMedia;
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
public class NCLBindTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLBind b = new NCLBind();
        b.setRole(new NCLRole("start"));
        b.setComponent(new NCLMedia("video"));
        b.setInterface(new NCLArea("track"));
        b.setDescriptor(new NCLDescriptor("dvideo"));

        String expResult = "<bind role='start' component='video' interface='track' descriptor='dvideo'/>\n";
        String result = b.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws NCLInvalidIdentifierException {
        NCLBind b = new NCLBind();
        b.setRole(new NCLRole("set"));
        b.setComponent(new NCLMedia("video"));
        NCLParam p = new NCLParam(NCLParamInstance.BINDPARAM);
        p.setName(new NCLConnectorParam("var"));
        p.setValue("10");
        b.addBindParam(p);

        String expResult = "<bind role='set' component='video'>\n\t<bindParam name='var' value='10'/>\n</bind>\n";
        String result = b.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLBind instance = new NCLBind(reader, null);
            String expResult = "<bind role='start' component='video' interface='track' descriptor='dvideo'/>\n";

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
    public void test4() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLBind instance = new NCLBind(reader, null);
            String expResult = "<bind role='set' component='video'>\n\t<bindParam name='var' value='10'/>\n</bind>\n";

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
    public void test5() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><head>"+
                    "<connectorBase><causalConnector id='ca'>"+
                    "<simpleCondition role='pa1' min='1'/>"+
                    "</causalConnector></connectorBase>"+
                    "</head><body>"+
                    "<link id='l1' xconnector='ca'>"+
                    "<bind role='pa1'/>"+
                    "</link>"+
                    "</body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLLink link = (NCLLink) instance.getBody().getLinks().iterator().next();
            NCLBind bind = (NCLBind) link.getBinds().iterator().next();
            NCLSimpleCondition cond = (NCLSimpleCondition) link.getXconnector().getCondition();
            NCLRole expResult = cond.getRole();
            NCLRole result = bind.getRole();
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
    public void test6() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><head>"+
                    "<descriptorBase><descriptor id='dpa' player='teste'/></descriptorBase>"+
                    "</head><body>"+
                    "<link id='l1' xconnector='ca'>"+
                    "<bind descriptor='dpa'/>"+
                    "</link>"+
                    "</body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            String expResult = "teste";
            NCLLink link = (NCLLink) instance.getBody().getLinks().iterator().next();
            NCLBind bind = (NCLBind) link.getBinds().iterator().next();
            String result = bind.getDescriptor().getPlayer();
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
    public void test7() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><body>"+
                    "<link id='l1' xconnector='ca'>"+
                    "<bind component='m1'/>"+
                    "</link>"+
                    "<media id='m1' src='media.png'/>"+
                    "</body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLLink link = (NCLLink) instance.getBody().getLinks().iterator().next();
            NCLBind bind = (NCLBind) link.getBinds().iterator().next();

            String expResult = "media.png";
            String result = ((NCLMedia) bind.getComponent()).getSrc();
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
    public void test8() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><body>"+
                    "<link id='l1' xconnector='ca'>"+
                    "<bind component='m1' interface='a1'/>"+
                    "</link>"+
                    "<media id='m1'><area id='a1' label='teste'/></media>"+
                    "</body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLLink link = (NCLLink) instance.getBody().getLinks().iterator().next();
            NCLBind bind = (NCLBind) link.getBinds().iterator().next();

            String expResult = "teste";
            String result = ((NCLArea) bind.getInterface()).getLabel();
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
    public void test9() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLDoc instance = new NCLDoc();
            instance.setReader(reader);
            String xml = "<ncl><head>"+
                    "<connectorBase><causalConnector id='ca'>"+
                    "<connectorParam name='pa1' type='teste'/>"+
                    "</causalConnector></connectorBase>"+
                    "</head><body>"+
                    "<link id='l1' xconnector='ca'>"+
                    "<bind><bindParam name='pa1'/></bind>"+
                    "</link>"+
                    "</body></ncl>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            NCLLink link = (NCLLink) instance.getBody().getLinks().iterator().next();
            NCLBind bind = (NCLBind) link.getBinds().iterator().next();
            NCLParam param = (NCLParam) bind.getBindParams().iterator().next();

            String expResult = "teste";
            String result = param.getName().getType();
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