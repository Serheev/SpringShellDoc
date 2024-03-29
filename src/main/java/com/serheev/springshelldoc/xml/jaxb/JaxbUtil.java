package com.serheev.springshelldoc.xml.jaxb;

import com.serheev.springshelldoc.schema.ObjectFactory;
import com.serheev.springshelldoc.schema.User;
import com.serheev.springshelldoc.schema.UsersWithMeals;
import com.serheev.springshelldoc.util.MealsUtil;
import jakarta.xml.bind.JAXBException;
import lombok.experimental.UtilityClass;

import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Map;

@UtilityClass
public class JaxbUtil {
    private static final JaxbParser jaxbParser;
    private static final JaxbMarshaller marshaller;
    private static final JaxbUnmarshaller unmarshaller;

    static {
        try {
            jaxbParser = JaxbParser.of(ObjectFactory.class);
            jaxbParser.setSchema(new File("in/usersWithMeals.xsd"));
            marshaller = jaxbParser.createMarshaller();
            unmarshaller = jaxbParser.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static UsersWithMeals process(File inputXml, Map<String, Object> params, File outputXml) throws IOException, JAXBException {
        UsersWithMeals users = unmarshalAndFilter(inputXml, params);
        marshal(users, outputXml);
        System.out.println("JAXB processing completed successfully, result in " + outputXml.getAbsolutePath());
        return users;
    }

    public static UsersWithMeals unmarshalAndFilter(File inputXml, Map<String, Object> params) throws IOException, JAXBException {
        UsersWithMeals users = unmarshal(inputXml);
        MealsUtil.filterAndAddExcess(users, params);
        return users;
    }

    public static UsersWithMeals unmarshal(File inputXml) throws IOException, JAXBException {
        try (Reader reader = Files.newBufferedReader(inputXml.toPath())) {
            return unmarshaller.unmarshal(reader);
        }
    }

    public static User unmarshalUser(XMLStreamReader reader) throws JAXBException {
        return unmarshaller.unmarshal(reader, User.class);
    }

    public static void marshal(Object instance, File outputXml) throws IOException, JAXBException {
        try (Writer writer = Files.newBufferedWriter(outputXml.toPath())) {
            marshaller.marshal(instance, writer);
        }
    }
}
