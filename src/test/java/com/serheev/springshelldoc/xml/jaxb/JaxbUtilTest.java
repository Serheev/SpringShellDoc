package com.serheev.springshelldoc.xml.jaxb;

import com.serheev.springshelldoc.schema.UsersWithMeals;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.UnmarshalException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.serheev.springshelldoc.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

public class JaxbUtilTest {

    @Test
    public void unmarshalOK() throws JAXBException, IOException {
        UsersWithMeals usersWithMeals = JaxbUtil.unmarshal(inputFile);
        assertEquals(new UsersWithMeals.Users(List.of(user, admin, guest)), usersWithMeals.getUsers());
    }

    @Test
    void unmarshalNOK() {
        assertThrows(UnmarshalException.class,
                () -> JaxbUtil.unmarshal(new File("in/badXmlFile.xml")), "Except bad format exception");
    }
}