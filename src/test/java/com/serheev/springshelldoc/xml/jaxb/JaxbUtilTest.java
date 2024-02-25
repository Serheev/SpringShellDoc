package com.serheev.springshelldoc.xml.jaxb;

import com.serheev.springshelldoc.schema.UsersWithMeals;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.UnmarshalException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.serheev.springshelldoc.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JaxbUtilTest {

    @Test
    public void processOK() throws JAXBException, IOException {
        UsersWithMeals usersWithMeals = JaxbUtil.process(inputFile, Map.of(), new File("out/jaxb.xml"));
        assertEquals(new UsersWithMeals.Users(getUsers()), usersWithMeals.getUsers());
    }

    @Test
    void processFiltered() throws IOException, JAXBException {
        UsersWithMeals usersWithMeals = JaxbUtil.process(inputFile, paramsMap, new File("out/jaxbFiltered.xml"));
        assertEquals(new UsersWithMeals.Users(getFilteredUsers()), usersWithMeals.getUsers());
    }

    @Test
    void processNOK() {
        assertThrows(UnmarshalException.class,
                () -> JaxbUtil.unmarshal(new File("in/badXmlFile.xml")), "Except bad format exception");
    }
}