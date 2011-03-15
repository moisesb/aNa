package br.uff.midiacom.ana;

import br.uff.midiacom.ana.connector.NCLConnectorBase;
import br.uff.midiacom.ana.descriptor.NCLDescriptorBase;
import br.uff.midiacom.ana.meta.NCLMeta;
import br.uff.midiacom.ana.meta.NCLMetadata;
import br.uff.midiacom.ana.region.NCLRegionBase;
import br.uff.midiacom.ana.reuse.NCLImportedDocumentBase;
import br.uff.midiacom.ana.rule.NCLRuleBase;
import br.uff.midiacom.ana.transition.NCLTransitionBase;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class NCLHeadTest {

    @Test
    public void test1() {
        NCLHead h = new NCLHead();
        h.setImportedDocumentBase(new NCLImportedDocumentBase());
        h.setRuleBase(new NCLRuleBase());
        h.setTransitionBase(new NCLTransitionBase());
        h.setRegionBase(new NCLRegionBase());
        h.setDescriptorBase(new NCLDescriptorBase());
        h.setConnectorBase(new NCLConnectorBase());

        NCLMeta m = new NCLMeta();
        m.setName("autor");
        m.setContent("joel");

        NCLMetadata mt = new NCLMetadata();
        mt.setRDFTree("arvore rdf");

        h.addMeta(m);
        h.addMetadata(mt);

        String expResult = "<head>\n\t<importedDocumentBase/>\n\t<ruleBase/>\n\t<transitionBase/>\n\t<regionBase/>\n\t<descriptorBase/>\n\t<connectorBase/>"+
                "\n\t<meta name='autor' content='joel'/>\n\t<metadata>\narvore rdf\n\t</metadata>\n</head>\n";
        String result = h.parse(0);
        assertEquals(expResult, result);
    }

    @Test
    public void test2() {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();

            NCLHead instance = new NCLHead(reader, null);
            String expResult = "<head>\n\t<importedDocumentBase/>\n\t<ruleBase/>\n\t<transitionBase/>\n\t<regionBase/>\n\t<descriptorBase/>\n\t<connectorBase/>"+
                "\n\t<meta name='autor' content='joel'/>\n\t<metadata>\narvore rdf\n\t</metadata>\n</head>\n";

            reader.setContentHandler(instance);
            String xml = "<head>\n\t<importedDocumentBase/>\n\t<ruleBase/>\n\t<transitionBase/>\n\t<regionBase/>\n\t<descriptorBase/>\n\t<connectorBase/>"+
                "\n\t<meta name='autor' content='joel'/>\n\t<metadata>arvore rdf</metadata>\n</head>\n";
            reader.parse(new InputSource(new StringReader(xml)));

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

            NCLHead instance = new NCLHead();
            instance.setReader(reader);
            String xml = "<head/>";

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
}