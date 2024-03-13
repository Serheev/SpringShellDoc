package com.serheev.springshelldoc.pdf;

import lombok.experimental.UtilityClass;
import org.apache.fop.apps.FOPException;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

@UtilityClass
public class PdfFopUtil {
    public void convert(File inputFile, File templateFile, File outputFile) throws TransformerException, FOPException, IOException {
        PdfFopConverter converter = PdfFopConverter.of(templateFile);
        converter.convert(inputFile, outputFile);
        System.out.println("Convert to PDF completed successfully, result in " + outputFile.getAbsolutePath());
    }
}
