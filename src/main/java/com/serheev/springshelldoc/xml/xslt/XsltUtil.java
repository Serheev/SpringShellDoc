package com.serheev.springshelldoc.xml.xslt;

import lombok.experimental.UtilityClass;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class XsltUtil {
    // thread safe
    private static final ThreadLocal<Map<LocalDateTime, Boolean>> excessMap = ThreadLocal.withInitial(HashMap::new);

    public static void transform(File inputFile, File xslFile, File outputFile) throws TransformerException {
        XsltProcessor.registerExtensionFunctions(new FilterMealsExtension(), new GetExcessExtension()); // before create processor!

        XsltProcessor processor = XsltProcessor.of(xslFile);
        processor.transform(inputFile, outputFile);
        System.out.println("XSLT transformation completed successfully, result in " + outputFile.getAbsolutePath());
    }

    public static void clearCache() {
        excessMap.get().clear();
    }

    static void cacheExcess(LocalDateTime dateTime, boolean excess) {
        excessMap.get().put(dateTime, excess);
    }

    static boolean getExcess(LocalDateTime dateTime) {
        return excessMap.get().get(dateTime);
    }
}
