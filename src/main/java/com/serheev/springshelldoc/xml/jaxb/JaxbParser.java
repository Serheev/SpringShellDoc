package com.serheev.springshelldoc.xml.jaxb;

import com.serheev.springshelldoc.xml.xsd.Schemas;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

import javax.xml.validation.Schema;
import java.io.File;

public class JaxbParser {

    private final JAXBContext ctx;
    protected Schema schema;

    public static JaxbParser of(Class... classesToBeBound) throws JAXBException {
        return new JaxbParser(classesToBeBound);
    }

    private JaxbParser(Class... classesToBeBound) throws JAXBException {
        ctx = JAXBContext.newInstance(classesToBeBound);
    }

    public void setSchema(File xsdSchema) {
        this.schema = Schemas.of(xsdSchema);
    }

    //    https://stackoverflow.com/a/7400735/548473
    public JaxbMarshaller createMarshaller() throws JAXBException {
        JaxbMarshaller marshaller = new JaxbMarshaller(ctx);
        if (schema != null) {
            marshaller.setSchema(schema);
        }
        return marshaller;
    }

    //    https://stackoverflow.com/a/7400735/548473
    public JaxbUnmarshaller createUnmarshaller() throws JAXBException {
        JaxbUnmarshaller unmarshaller = new JaxbUnmarshaller(ctx);
        if (schema != null) {
            unmarshaller.setSchema(schema);
        }
        return unmarshaller;
    }
}
