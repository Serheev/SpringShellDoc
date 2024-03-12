package com.serheev.springshelldoc.xml.xpath;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XPathProcessor {
    private static final DocumentBuilderFactory DOCUMENT_FACTORY;
    private static final DocumentBuilder DOCUMENT_BUILDER;

    private static final XPathFactory XPATH_FACTORY = XPathFactory.newInstance();
    private static final XPath XPATH = XPATH_FACTORY.newXPath();

    static {
        DOCUMENT_FACTORY = DocumentBuilderFactory.newInstance();
        DOCUMENT_FACTORY.setNamespaceAware(false);
        try {
            DOCUMENT_BUILDER = DOCUMENT_FACTORY.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    private final Document doc;

    public static XPathProcessor of(File xmlFile) throws IOException, SAXException {
        return new XPathProcessor(xmlFile);
    }

    private XPathProcessor(File xmlFile) throws IOException, SAXException {
        doc = DOCUMENT_BUILDER.parse(xmlFile);
    }

    public static synchronized XPathExpression compileExpression(String exp) throws XPathExpressionException {
        return XPATH.compile(exp);
    }

    public Object evaluate(XPathExpression expression) throws XPathExpressionException {
        XPathEvaluationResult<?> res = expression.evaluateExpression(doc);
        Object value = res.value();
        if (value instanceof Iterable<?> itr) {
            List<Object> list = new ArrayList<>();
            itr.forEach(list::add);
            if (list.size() == 1) {
                return list.get(0);
            } else {
                return list;
            }
        }
        return value;
    }
}
