package com.serheev.springshelldoc.pdf;

import org.junit.jupiter.api.Test;

import javax.xml.transform.TransformerException;
import java.io.File;

import static com.serheev.springshelldoc.TestData.inputFile;

class PdfFopUtilTest {

    @Test
    void convert() throws TransformerException {
        PdfFopUtil.convert(inputFile, new File("in/fop.xsl"), new File("out/fop.xml"));
    }
}