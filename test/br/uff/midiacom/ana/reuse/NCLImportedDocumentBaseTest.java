package br.uff.midiacom.ana.reuse;

import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.NCLValues.NCLImportType;
import java.net.URISyntaxException;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class NCLImportedDocumentBaseTest {

    @Test
    public void test1() throws NCLInvalidIdentifierException {
        NCLImportedDocumentBase base = new NCLImportedDocumentBase();
        base.setId("IDb");

        String expResult = "<importedDocumentBase id='IDb'/>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() throws NCLInvalidIdentifierException, URISyntaxException {
        NCLImportedDocumentBase base = new NCLImportedDocumentBase();
        NCLImport imp = new NCLImport(NCLImportType.NCL);
        imp.setAlias("base");
        imp.setDocumentURI("base.ncl");
        base.addImportNCL(imp);

        String expResult = "<importedDocumentBase>\n\t<importNCL alias='base' documentURI='base.ncl'/>\n</importedDocumentBase>\n";
        String result = base.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLImportedDocumentBase instance = new NCLImportedDocumentBase(reader, null);
            String expResult = "<importedDocumentBase id='IDb'/>\n";

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

            NCLImportedDocumentBase instance = new NCLImportedDocumentBase(reader, null);
            String expResult = "<importedDocumentBase>\n\t<importNCL alias='base' documentURI='base.ncl'/>\n</importedDocumentBase>\n";

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
    public void test_validacao1() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLImportedDocumentBase instance = new NCLImportedDocumentBase(reader, null);
            String xml = "<importedDocumentBase id='IDb'/>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLImportedDocumentBase instance = new NCLImportedDocumentBase(reader, null);
            String xml = "<importedDocumentBase><importBase alias='base' documentURI='base.ncl'/></importedDocumentBase>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            assertFalse(instance.validate());
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void test_validacao3() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLImportedDocumentBase instance = new NCLImportedDocumentBase(reader, null);
            String xml = "<importedDocumentBase><importNCL alias='base' documentURI='base.ncl'/></importedDocumentBase>";

            reader.setContentHandler(instance);
            reader.parse(new InputSource(new StringReader(xml)));

            boolean result = instance.validate();

            for(String msg : instance.getWarnings())
                System.out.println(msg);
            for(String msg : instance.getErrors())
                System.out.println(msg);

            assertTrue(result);
        }
        catch(SAXException ex){
            fail(ex.getMessage());
        }
        catch(IOException ex){
            fail(ex.getMessage());
        }
    }
}