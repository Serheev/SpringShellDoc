package com.serheev.springshelldoc.xml.jaxb;

import com.serheev.springshelldoc.schema.ObjectFactory;
import com.serheev.springshelldoc.schema.UsersWithMeals;
import com.serheev.springshelldoc.xml.xsd.Schemas;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;

@UtilityClass
public class JaxbUtil {
    private static final JaxbUnmarshaller unmarshaller;

    static {
        try {
            JAXBContext ctx = JAXBContext.newInstance(ObjectFactory.class);
            unmarshaller = new JaxbUnmarshaller(ctx);
            unmarshaller.setSchema(Schemas.of(new File("in/usersWithMeals.xsd")));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static UsersWithMeals unmarshal(File inputXml) throws IOException, JAXBException {
        try (Reader reader = Files.newBufferedReader(inputXml.toPath())) {
            return unmarshaller.unmarshal(reader);
        }
    }
}
