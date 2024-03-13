package com.serheev.springshelldoc.pdf;

import org.apache.fop.apps.FOPException;
import org.junit.jupiter.api.Test;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

import static com.serheev.springshelldoc.TestData.inputFile;

class PdfFopUtilTest {

    @Test
    void convert() throws TransformerException, FOPException, IOException {
        PdfFopUtil.convert(inputFile, new File("in/fop.xsl"), new File("out/fop.pdf"));
    }
}