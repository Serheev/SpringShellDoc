package com.serheev.springshelldoc.xml.xpath;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import java.util.List;

import static com.serheev.springshelldoc.TestData.inputFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

class XPathUtilTest {
    @Test
    void evaluateBoolean() throws Exception {
        Boolean result = (Boolean) XPathUtil.evaluate(inputFile,
                "count(/UsersWithMeals/Users/User/Meals/Meal/text())<100");
        assertEquals(true, result);
    }

    @Test
    void evaluateNumber() throws Exception {
        Double result = (Double) XPathUtil.evaluate(inputFile,
                "count(/UsersWithMeals/Users/User/Meals/Meal/text())");
        assertEquals(9.0, result);
    }

    @Test
    void evaluateNode() throws Exception {
        Node node = (Node) XPathUtil.evaluate(inputFile,
                "/UsersWithMeals/Users/User[2]/Meals/Meal[1]/text()");
        assertEquals("Admin Lunch", node.getNodeValue());
    }

    @Test
    void evaluateNodeList() throws Exception {
        List<? extends Node> nodes = (List<? extends Node>) XPathUtil.evaluate(inputFile,
                "/UsersWithMeals/Users/User[2]/Meals/Meal/text()");
        assertEquals(2, nodes.size());
        assertEquals("Admin Lunch", nodes.get(0).getNodeValue());
        assertEquals("Admin Dinner", nodes.get(1).getNodeValue());
    }
}