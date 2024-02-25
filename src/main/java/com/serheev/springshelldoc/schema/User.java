
package com.serheev.springshelldoc.schema;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://serheev.com}Meals" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="email" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="password" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="caloriesPerDay" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="registered" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *       &lt;attribute name="enabled" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="roles" type="{http://serheev.com}roles" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "User", namespace = "http://serheev.com")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "email", required = true)
    protected String email;
    @XmlAttribute(name = "password", required = true)
    protected String password;
    @XmlAttribute(name = "caloriesPerDay", required = true)
    protected int caloriesPerDay;
    @XmlAttribute(name = "registered", required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date registered;
    @XmlAttribute(name = "enabled", required = true)
    protected boolean enabled;
    @XmlAttribute(name = "roles")
    protected List<RoleTypes> roles = new ArrayList<>();
    @XmlElement(name = "Meals", namespace = "http://serheev.com")
    protected Meals meals;
}