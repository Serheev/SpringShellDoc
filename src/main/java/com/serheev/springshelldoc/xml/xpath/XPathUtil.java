package com.serheev.springshelldoc.xml.xpath;

import lombok.experimental.UtilityClass;
import org.xml.sax.SAXException;

import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;

@UtilityClass
public class XPathUtil {
    public static Object evaluate(File xmlFile, String xpathExpression) throws IOException, SAXException, XPathExpressionException {
        XPathProcessor processor = XPathProcessor.of(xmlFile);
        XPathExpression expression = XPathProcessor.compileExpression(xpathExpression);
        Object result = processor.evaluate(expression);
        System.out.println(result);
        return result;
    }
}

