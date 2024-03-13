package com.serheev.springshelldoc.xml.xslt;

import lombok.experimental.UtilityClass;

import javax.xml.transform.TransformerException;
import java.io.File;

@UtilityClass
public class XsltUtil {

    public static void transform(File inputFile, File xslFile, File outputFile) throws TransformerException {
        XsltProcessor processor = XsltProcessor.of(xslFile);
        processor.transform(inputFile, outputFile);
        System.out.println("XSLT transformation completed successfully, result in " + outputFile.getAbsolutePath());
    }
}
