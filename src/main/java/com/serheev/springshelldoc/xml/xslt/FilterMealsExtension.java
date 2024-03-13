package com.serheev.springshelldoc.xml.xslt;

import net.sf.saxon.s9api.*;
import com.serheev.springshelldoc.schema.Meal;
import com.serheev.springshelldoc.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.List;

import static net.sf.saxon.s9api.ItemType.ANY_NODE;
import static net.sf.saxon.s9api.ItemType.INT;
import static net.sf.saxon.s9api.OccurrenceIndicator.ONE;
import static net.sf.saxon.s9api.OccurrenceIndicator.ZERO_OR_MORE;
import static net.sf.saxon.s9api.SequenceType.makeSequenceType;

public class FilterMealsExtension implements ExtensionFunction {
    @Override
    public QName getName() {
        return new QName("p", "http://serheev.com", "filter");
    }

    @Override
    public SequenceType getResultType() {
        return makeSequenceType(ANY_NODE, ZERO_OR_MORE);
    }

    // Argument types: "p:filter($meals, @caloriesPerDay)"
    @Override
    public SequenceType[] getArgumentTypes() {
        return new SequenceType[]{makeSequenceType(ANY_NODE, ZERO_OR_MORE), makeSequenceType(INT, ONE)};
    }

    @Override
    public XdmValue call(XdmValue[] xdmValues) throws SaxonApiException {
        List<XdmNode> nodes = xdmValues[0].stream().asListOfNodes(); // get $meals

        List<Meal> rawMeals = nodes.stream()
                .map(node -> new Meal(node.getStringValue(), Integer.parseInt(node.attribute("calories")),
                        LocalDateTime.parse(node.attribute("dateTime")), false))
                .toList();
        int caloriesPerDay = ((XdmAtomicValue) xdmValues[1]).getDecimalValue().intValue(); // @caloriesPerDay
        List<Meal> filteredMeals = MealsUtil.filterAndAddExcess(rawMeals, caloriesPerDay, null, null, null, null);

        // Transform Meals (add excess attribute to Meal is very not-trivial task).
        // As workaround we cache excess (dateTime is unique for user's meals)
        XsltUtil.clearCache();
        filteredMeals.forEach(meal -> XsltUtil.cacheExcess(meal.getDateTime(), meal.getExcess()));

        return new XdmValue(nodes);
    }
}
