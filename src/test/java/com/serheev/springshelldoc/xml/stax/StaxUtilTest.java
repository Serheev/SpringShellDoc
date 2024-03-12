package com.serheev.springshelldoc.xml.stax;

import com.serheev.springshelldoc.schema.User;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.serheev.springshelldoc.TestData.inputFile;
import static com.serheev.springshelldoc.TestData.paramsMap;

public class StaxUtilTest {

    @ParameterizedTest
    @MethodSource("com.serheev.springshelldoc.TestData#getUsers")
    void process(User user) throws XMLStreamException, JAXBException, IOException {
        StaxUtil.process(inputFile, Map.of(), user.getEmail(), new File("out/stax" + user.getName() + ".xml"));
    }


    @ParameterizedTest
    @MethodSource("com.serheev.springshelldoc.TestData#getFilteredUsers")
    void processFiltered(User user) throws XMLStreamException, JAXBException, IOException {
        StaxUtil.process(inputFile, paramsMap, user.getEmail(), new File("out/stax" + user.getName() + "Filtered.xml"));
    }
}