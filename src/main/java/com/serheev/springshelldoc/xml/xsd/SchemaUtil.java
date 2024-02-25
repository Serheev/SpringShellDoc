package com.serheev.springshelldoc.xml.xsd;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;

public class SchemaUtil {

    public static void validate(File inputFile, File xsdSchema) throws IOException, SAXException {
        try (Reader reader = Files.newBufferedReader(inputFile.toPath())) {
            Schemas.validate(reader, Schemas.of(xsdSchema));
        }
        System.out.println(inputFile.getAbsolutePath() + " is valid");
    }
}
