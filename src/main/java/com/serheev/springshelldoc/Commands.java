package com.serheev.springshelldoc;

import com.serheev.springshelldoc.xml.jaxb.JaxbUtil;
import com.serheev.springshelldoc.xml.stax.StaxUtil;
import com.serheev.springshelldoc.xml.xpath.XPathUtil;
import jakarta.xml.bind.JAXBException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.xml.sax.SAXException;


import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.serheev.springshelldoc.util.Util.parseParams;

@ShellComponent
public class Commands {
    @ShellMethod(key = "xsd", value = "Validate XML file against XSD (XML Schema)")
    public void xsdValidate(@ShellOption(value = {"input", "-i"}, help = "Input file") File inputFile,
                            @ShellOption(value = {"schema", "-s"}, help = "XSD schema file") File xsdSchema) {
        System.out.println("\nInput file: " + inputFile.getAbsolutePath());
        System.out.println("XSD schema file: " + xsdSchema.getAbsolutePath());
    }

    @ShellMethod(key = "jaxb", value = "Process XML file via JAXB")
    public void jaxbProcess(
            @ShellOption(value = {"input", "-i"}, help = "Input file") File inputFile,
            @ShellOption(value = {"filter", "-f"}, help = "Filter params", defaultValue = "") List<String> params,
            @ShellOption(value = {"output", "-o"}, help = "Output file") File outputFile) throws JAXBException, IOException {
        JaxbUtil.process(inputFile, parseParams(params), outputFile);
    }

    @ShellMethod(key = "stax", value = "Process XML file via StAX")
    public void staxProcess(
            @ShellOption(value = {"input", "-i"}, help = "Input file") File inputFile,
            @ShellOption(value = {"email", "-e"}, help = "User email") String email,
            @ShellOption(value = {"filters", "-f"}, help = "Filter params", defaultValue = "") List<String> params,
            @ShellOption(value = {"output", "-o"}, help = "Output file") File outputFile) throws JAXBException, IOException, XMLStreamException {
        StaxUtil.process(inputFile, parseParams(params), email, outputFile);
    }

    @ShellMethod(key = "xpath", value = "Evaluate XPath against XML")
    public void xpathEvaluate(
            @ShellOption(value = {"input", "-i"}, help = "Input file") File inputFile,
            @ShellOption(value = {"exp", "-e"}, help = "XPath expression") String xpathExpression) throws XPathExpressionException, IOException, SAXException {
        XPathUtil.evaluate(inputFile, xpathExpression);
    }

    @ShellMethod(key = "xslt", value = "Transform XML with XSLT")
    public void xsltTransform(
            @ShellOption(value = {"input", "-i"}, help = "Input file") File inputFile,
            @ShellOption(value = {"xsl", "-x"}, help = "XSL transformation file") File xslFile,
            @ShellOption(value = {"filter", "-f"}, help = "Filter params", defaultValue = "") List<String> params,
            @ShellOption(value = {"output", "-o"}, help = "Output file") File outputFile) {
        System.out.println("\nInput file: " + inputFile.getAbsolutePath());
        System.out.println("XSL transformation file: " + xslFile.getAbsolutePath());
        System.out.println("Filter params: " + params.toString());
        System.out.println("Output file: " + outputFile.getAbsolutePath());
    }

    @ShellMethod(key = "pdf-fop", value = "Convert XML to PDF via Apache FOP")
    public void pdfFopConvert(
            @ShellOption(value = {"input", "-i"}, help = "Input file") File inputFile,
            @ShellOption(value = {"template", "-t"}, help = "Transform template file") File templateFile,
            @ShellOption(value = {"output", "-o"}, help = "Output file") File outputFile) {
        System.out.println("\nInput file: " + inputFile.getAbsolutePath());
        System.out.println("Transform template file: " + templateFile);
        System.out.println("Output file: " + outputFile.getAbsolutePath());
    }

    @ShellMethod(key = "iText", value = "Convert XML to PDF via iText")
    public void pdfiTextConvert(
            @ShellOption(value = {"input", "-i"}, help = "Input file") File inputFile,
            @ShellOption(value = {"filter", "-f"}, help = "Filter params", defaultValue = "") List<String> params,
            @ShellOption(value = {"output", "-o"}, help = "Output file") File outputFile) {
        System.out.println("\nInput file: " + inputFile.getAbsolutePath());
        System.out.println("Filter params: " + params.toString());
        System.out.println("Output file: " + outputFile.getAbsolutePath());
    }

    @ShellMethod(key = "excel-poi", value = "Convert XML to Excel via Apache POI")
    public void excelPoiConvert(
            @ShellOption(value = {"input", "-i"}, help = "Input file") File inputFile,
            @ShellOption(value = {"template", "-t"}, help = "Transform template file") File templateFile,
            @ShellOption(value = {"filter", "-f"}, help = "Filter params", defaultValue = "") List<String> params,
            @ShellOption(value = {"output", "-o"}, help = "Output file") File outputFile) {
        System.out.println("\nInput file: " + inputFile.getAbsolutePath());
        System.out.println("Transform template file: " + templateFile);
        System.out.println("Filter params: " + params.toString());
        System.out.println("Output file: " + outputFile.getAbsolutePath());
    }
}
