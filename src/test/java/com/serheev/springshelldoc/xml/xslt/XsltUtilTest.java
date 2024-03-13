package com.serheev.springshelldoc.xml.xslt;

import org.junit.jupiter.api.Test;

import javax.xml.transform.TransformerException;
import java.io.File;

import static com.serheev.springshelldoc.TestData.inputFile;

class XsltUtilTest {
    private static final File xslFile = new File("in/xslt.xsl");

    @Test
    void transform() throws TransformerException {
        XsltUtil.transform(inputFile, xslFile, new File("out/xslt.html"));
    }
}