package com.serheev.springshelldoc.xml.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import javax.xml.stream.XMLStreamReader;
import javax.xml.validation.Schema;
import java.io.Reader;

public class JaxbUnmarshaller {

    private Unmarshaller unmarshaller;

    public JaxbUnmarshaller(JAXBContext ctx) throws JAXBException {
        unmarshaller = ctx.createUnmarshaller();
    }

    public void setSchema(Schema schema) {
        unmarshaller.setSchema(schema);
    }

    public <T> T unmarshal(Reader reader) throws JAXBException {
        return (T) unmarshaller.unmarshal(reader);
    }

    public <T> T unmarshal(XMLStreamReader reader, Class<T> elementClass) throws JAXBException {
        return unmarshaller.unmarshal(reader, elementClass).getValue();
    }
}
