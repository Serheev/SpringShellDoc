
package com.serheev.springshelldoc.schema;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
 * <p>Java class for roleTypes.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="roleTypes"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="admin"/&gt;
 *     &lt;enumeration value="user"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "roleTypes", namespace = "http://serheev.com")
@XmlEnum
@Getter
@RequiredArgsConstructor
public enum RoleTypes {

    @XmlEnumValue("admin")
    ADMIN("admin"),
    @XmlEnumValue("user")
    USER("user");
    private final String value;

    public static RoleTypes fromValue(String v) {
        for (RoleTypes c : RoleTypes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
