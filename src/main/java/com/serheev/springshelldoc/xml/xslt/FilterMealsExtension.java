package com.serheev.springshelldoc.xml.xslt;

import net.sf.saxon.s9api.*;
import com.serheev.springshelldoc.schema.Meal;
import com.serheev.springshelldoc.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.serheev.springshelldoc.xml.xslt.SaxonUtil.toLocalDate;
import static com.serheev.springshelldoc.xml.xslt.SaxonUtil.toLocalTime;
import static net.sf.saxon.s9api.ItemType.*;
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

    // Argument types: "javaops:filter($meals, @caloriesPerDay, $startDate, $endDate, $startTime, $endTime)"
    @Override
    public SequenceType[] getArgumentTypes() {
        return new SequenceType[]{
                makeSequenceType(ANY_NODE, ZERO_OR_MORE), makeSequenceType(INT, ONE),
                makeSequenceType(DATE, ONE), makeSequenceType(DATE, ONE),
                makeSequenceType(TIME, ONE), makeSequenceType(TIME, ONE)};
    }

    @Override
    public XdmValue call(XdmValue[] xdmValues) throws SaxonApiException {
        List<XdmNode> nodes = xdmValues[0].stream().asListOfNodes(); // get $meals

        List<Meal> rawMeals = nodes.stream()
                .map(node -> new Meal(node.getStringValue(), Integer.parseInt(node.attribute("calories")),
                        LocalDateTime.parse(node.attribute("dateTime")), false))
                .toList();
        int caloriesPerDay = ((XdmAtomicValue) xdmValues[1]).getDecimalValue().intValue(); // @caloriesPerDay
        List<Meal> filteredMeals = MealsUtil.filterAndAddExcess(rawMeals, caloriesPerDay,
                toLocalDate(xdmValues[2]), toLocalDate(xdmValues[3]), toLocalTime(xdmValues[4]), toLocalTime(xdmValues[5]));

        // Transform Meals (add excess attribute to Meal is very not-trivial task).
        // As workaround we cache excess (dateTime is unique for user's meals)
        XsltUtil.clearCache();
        filteredMeals.forEach(meal -> XsltUtil.cacheExcess(meal.getDateTime(), meal.getExcess()));


        // filer $meals nodes only with filtered dateTimes (dateTime is unique for user's meals)
        Set<LocalDateTime> filteredDateTimes = filteredMeals.stream()
                .map(Meal::getDateTime)
                .collect(Collectors.toSet());
        return new XdmValue(nodes.stream()
                .filter(node -> filteredDateTimes.contains(LocalDateTime.parse(node.attribute("dateTime")))));
    }
}
