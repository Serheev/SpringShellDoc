package com.serheev.springshelldoc.xml.xslt;

import net.sf.saxon.s9api.ExtensionFunction;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;

public class XsltProcessor {
    static final TransformerFactory FACTORY = TransformerFactory.newInstance();
    private final Transformer xFormer;

    public static void registerExtensionFunctions(ExtensionFunction... functions) {
        Arrays.stream(functions).forEach(SaxonUtil::registerExtensionFunction);
    }

    public static XsltProcessor of(File xslTemplate) throws TransformerConfigurationException {
        return new XsltProcessor(xslTemplate);
    }

    public XsltProcessor(File xslTemplate) throws TransformerConfigurationException {
        xFormer = FACTORY.newTransformer(new StreamSource(xslTemplate));
    }

    public void transform(File in, File out) throws TransformerException {
        transform(new StreamSource(in), new StreamResult(out));
    }

    public void transform(Source xmlSource, Result outputTarget) throws TransformerException {
        xFormer.transform(xmlSource, outputTarget);
    }

    public void setParameters(Map<String, Object> params) {
        params.forEach(this::setParameter);
    }

    public void setParameter(String name, Object value) {
        if (value instanceof LocalTime time) {
            xFormer.setParameter(name, time.format(DateTimeFormatter.ISO_LOCAL_TIME));
        } else {
            xFormer.setParameter(name, value);
        }
    }
}
