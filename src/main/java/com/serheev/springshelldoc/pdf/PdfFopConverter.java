package com.serheev.springshelldoc.pdf;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;
import com.serheev.springshelldoc.xml.xslt.XsltProcessor;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PdfFopConverter {

    private static final FopFactory FOP_FACTORY;
    private final XsltProcessor xsltProcessor;

    static {
        try {
            FOP_FACTORY = FopFactory.newInstance(new File("in/fopConf.xml"));
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static PdfFopConverter of(File templateFile) throws TransformerConfigurationException {
        return new PdfFopConverter(templateFile);
    }

    private PdfFopConverter(File xslFoTemplate) throws TransformerConfigurationException {
        xsltProcessor = XsltProcessor.of(xslFoTemplate);
    }

    public void convert(File inputFile, File outputFile) throws TransformerFactoryConfigurationError, FOPException, TransformerException, IOException {
        try (OutputStream outputStream = new FileOutputStream(outputFile)) {
            Fop fop = FOP_FACTORY.newFop(MimeConstants.MIME_PDF, outputStream); // Formatting Objects Processor
            xsltProcessor.transform(new StreamSource(inputFile), new SAXResult(fop.getDefaultHandler()));
        }
    }
}
