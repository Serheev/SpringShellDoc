
package com.serheev.springshelldoc.schema;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.serheev.springshelldoc.schema package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.serheev.springshelldoc.schema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UsersWithMeals }
     * 
     */
    public UsersWithMeals createUsersWithMeals() {
        return new UsersWithMeals();
    }

    /**
     * Create an instance of {@link UsersWithMeals.Users }
     * 
     */
    public UsersWithMeals.Users createUsersWithMealsUsers() {
        return new UsersWithMeals.Users();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Meals }
     * 
     */
    public Meals createMeals() {
        return new Meals();
    }

    /**
     * Create an instance of {@link Meal }
     * 
     */
    public Meal createMeal() {
        return new Meal();
    }

}
