package com.serheev.springshelldoc.xml.xslt;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XsltProcessor {
    private static final TransformerFactory FACTORY = TransformerFactory.newInstance();
    private final Transformer xFormer;

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
}
