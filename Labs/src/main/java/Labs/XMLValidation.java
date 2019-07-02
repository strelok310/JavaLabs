package Labs;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class XMLValidation {
    public static void main(String[] args) throws Exception {
        Validator validator = getValidator("human.xsd");

        validateXML("person.xml",   validator);
        validateXML("person2.xml",  validator);
        validateXML("person3.xml",  validator);
        validateXML("person4.xml",  validator);
        validateXML("person5.xml",  validator);
        validateXML("person6.xml",  validator);
    }

    static Validator getValidator(String xsd) throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        Source schemaFile = new StreamSource(new File(ClassLoader.getSystemResource(xsd).getPath()));
        Schema schema = factory.newSchema(schemaFile);

        return schema.newValidator();
    }

    static void validateXML(String name, Validator validator) throws Exception {
        System.out.println("Validation: " + name);

        DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        Document document;
        try {
            document = parser.parse(new File(ClassLoader.getSystemResource(name).getPath()));
            validator.validate(new DOMSource(document));
            System.out.println("Success!");
        }
        catch (SAXParseException e) {
            System.out.println("Failed: " + e.getMessage());
        }

        System.out.println();
    }
}
