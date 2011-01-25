/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pensario.meta;

import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class NCLMetaTest {

    @Test
    public void test1() {
        NCLMeta meta = new NCLMeta();
        meta.setName("autor");
        meta.setContent("joel");

        String expResult = "<meta name='autor' content='joel'/>\n";
        String result = meta.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLMeta meta = new NCLMeta(reader, null);
            String expResult = "<meta name='autor' content='joel'/>\n";

            reader.setContentHandler(meta);
            reader.parse(new InputSource(new StringReader(expResult)));

            String result = meta.parse(0);
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