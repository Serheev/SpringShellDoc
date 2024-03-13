package com.serheev.springshelldoc.xml.xslt;

import lombok.experimental.UtilityClass;
import net.sf.saxon.jaxp.SaxonTransformerFactory;
import net.sf.saxon.s9api.ExtensionFunction;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.XdmAtomicValue;
import net.sf.saxon.s9api.XdmValue;

import java.time.LocalDateTime;

@UtilityClass
public class SaxonUtil {

    public static void registerExtensionFunction(ExtensionFunction function) {
        Processor processor = ((SaxonTransformerFactory) XsltProcessor.FACTORY).getProcessor();
        processor.registerExtensionFunction(function);
    }

    public static LocalDateTime toLocalDateTime(XdmValue dateTime) {
        return ((XdmAtomicValue) dateTime).getLocalDateTime();
    }
}
