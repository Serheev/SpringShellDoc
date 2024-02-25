package com.serheev.springshelldoc.xml.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.xml.validation.Schema;
import java.io.StringWriter;
import java.io.Writer;

public class JaxbMarshaller {
    private final Marshaller marshaller;

    public JaxbMarshaller(JAXBContext ctx) throws JAXBException {
        marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
    }

    public void setSchema(Schema schema) {
        marshaller.setSchema(schema);
    }

    public void marshal(Object instance, Writer writer) throws JAXBException {
        marshaller.marshal(instance, writer);
    }

    public String marshal(Object instance) throws JAXBException {
        StringWriter sw = new StringWriter();
        marshal(instance, sw);
        return sw.toString();
    }
}
