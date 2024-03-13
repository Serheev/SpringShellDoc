package com.serheev.springshelldoc.xml.xslt;

import net.sf.saxon.s9api.ExtensionFunction;
import net.sf.saxon.s9api.QName;
import net.sf.saxon.s9api.SequenceType;
import net.sf.saxon.s9api.XdmValue;

import java.time.LocalDateTime;

import static net.sf.saxon.s9api.ItemType.BOOLEAN;
import static net.sf.saxon.s9api.ItemType.DATE_TIME;
import static net.sf.saxon.s9api.OccurrenceIndicator.ONE;
import static net.sf.saxon.s9api.SequenceType.makeSequenceType;
import static com.serheev.springshelldoc.xml.xslt.SaxonUtil.toLocalDateTime;

public class GetExcessExtension implements ExtensionFunction {
    @Override
    public QName getName() {
        return new QName("p", "http://serheev.com", "getExcess");
    }

    @Override
    public SequenceType getResultType() {
        return makeSequenceType(BOOLEAN, ONE);
    }

    // Argument types: "p:getExcess(@dateTime)"
    @Override
    public SequenceType[] getArgumentTypes() {
        return new SequenceType[]{makeSequenceType(DATE_TIME, ONE)};
    }

    // get from cache
    @Override
    public XdmValue call(XdmValue[] xdmValues) {
        LocalDateTime dateTime = toLocalDateTime(xdmValues[0]);
        boolean excess = XsltUtil.getExcess(dateTime);
        return XdmValue.makeValue(excess);
    }
}
