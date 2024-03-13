package com.serheev.springshelldoc.xml.xslt;

import org.junit.jupiter.api.Test;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.util.Map;

import static com.serheev.springshelldoc.TestData.inputFile;
import static com.serheev.springshelldoc.TestData.paramsMap;

class XsltUtilTest {
    private static final File xslFile = new File("in/xslt.xsl");

    @Test
    void transformDateFiltered() throws TransformerException {
        XsltUtil.transform(inputFile, xslFile,
                Map.of("startDate", paramsMap.get("startDate"),
                        "endDate", paramsMap.get("endDate")),
                new File("out/xsltDateFiltered.html"));
    }

    @Test
    void transformTimeFiltered() throws TransformerException {
        XsltUtil.transform(inputFile, xslFile,
                Map.of("startTime", paramsMap.get("startTime"),
                        "endTime", paramsMap.get("endTime")),
                new File("out/xsltTimeFiltered.html"));
    }

    @Test
    void transformFiltered() throws TransformerException {
        XsltUtil.transform(inputFile, xslFile, paramsMap, new File("out/xsltFiltered.html"));
    }

    @Test
    void transform() throws TransformerException {
        XsltUtil.transform(inputFile, xslFile, Map.of(), new File("out/xslt.html"));
    }
}