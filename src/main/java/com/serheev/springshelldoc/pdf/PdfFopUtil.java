package com.serheev.springshelldoc.pdf;

import lombok.experimental.UtilityClass;
import com.serheev.springshelldoc.xml.xslt.XsltProcessor;

import javax.xml.transform.TransformerException;
import java.io.File;

@UtilityClass
public class PdfFopUtil {
    public void convert(File inputFile, File templateFile, File outputFile) throws TransformerException {
        XsltProcessor processor = XsltProcessor.of(templateFile);
        processor.transform(inputFile, outputFile);
        System.out.println("Transform completed successfully, result in " + outputFile.getAbsolutePath());
    }
}
