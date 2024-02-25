package com.serheev.springshelldoc.xml.xsd;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.File;
import java.io.IOException;

import static com.serheev.springshelldoc.TestData.inputFile;
import static org.junit.jupiter.api.Assertions.*;

class SchemaUtilTest {
    private static final File xmlSchema = new File("in/usersWithMeals.xsd");

    @Test
    void validateOK() throws IOException, SAXException {
        SchemaUtil.validate(inputFile, xmlSchema);
    }

    @Test
    void testValidateNOK() throws IOException {
        assertThrows(SAXParseException.class,
                () -> SchemaUtil.validate(new File("in/badXmlFile.xml"), xmlSchema), "Except bad format exception");
    }
}