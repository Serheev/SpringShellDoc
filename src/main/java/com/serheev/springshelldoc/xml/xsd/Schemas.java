package com.serheev.springshelldoc.xml.xsd;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class Schemas {
    private static final SchemaFactory SCHEMA_FACTORY = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

    // SchemaFactory is not thread-safe
    public static synchronized Schema of(File file) {
        try {
            return SCHEMA_FACTORY.newSchema(file);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static void validate(String str, Schema schema) throws IOException, SAXException {
        validate(new StringReader(str), schema);
    }

    public static void validate(Reader reader, Schema schema) throws IOException, SAXException {
        schema.newValidator().validate(new StreamSource(reader));
    }
}
