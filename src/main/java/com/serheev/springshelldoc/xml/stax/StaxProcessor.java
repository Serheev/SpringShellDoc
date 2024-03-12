package com.serheev.springshelldoc.xml.stax;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class StaxProcessor {
    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();

    private final XMLStreamReader reader;

    public static StaxProcessor of(File inputFile) throws XMLStreamException {
        return new StaxProcessor(new StreamSource(inputFile));
    }

    private StaxProcessor(Source in) throws XMLStreamException {
        reader = FACTORY.createXMLStreamReader(in);
    }

    public boolean startElement(String element, String parent) throws XMLStreamException {
        while (reader.hasNext()) {
            int event = reader.next();
            if (parent != null && isElementEnd(event, parent)) {
                return false;
            }
            if (isElementStart(event, element)) {
                return true;
            }
        }
        return false;
    }

    private boolean isElementStart(int event, String el) {
        return event == XMLEvent.START_ELEMENT && el.equals(reader.getLocalName());
    }

    private boolean isElementEnd(int event, String el) {
        return event == XMLEvent.END_ELEMENT && el.equals(reader.getLocalName());
    }

    public XMLStreamReader getReader() {
        return reader;
    }

    public String getAttribute(String name) {
        return reader.getAttributeValue(null, name);
    }

    public boolean doUntil(int stopEvent, String value) throws XMLStreamException {
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == stopEvent && value.equals(getValue(event))) {
                return true;
            }
        }
        return false;
    }

    public String getValue(int event) {
        return (event == XMLEvent.CHARACTERS) ? reader.getText() : reader.getLocalName();
    }

    public String getElementValue(String element) throws XMLStreamException {
        return doUntil(XMLEvent.START_ELEMENT, element) ? reader.getElementText() : null;
    }

    public String getText() throws XMLStreamException {
        return reader.getElementText();
    }
}
