package com.serheev.springshelldoc.pdf;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class ITextPdfConverter {

    public static final Color DARK_GREEN = new DeviceRgb(0, 0x80, 0);

    static {
        // Register system fonts
        PdfFontFactory.registerSystemDirectories();
    }

    // font must be created after Document creation
    @SneakyThrows
    public static PdfFont createRegisteredFont(String fontName) {
        return PdfFontFactory.createRegisteredFont(fontName);
    }

    public static void convert(File outputFile, Consumer<Document> documentComposer) throws IOException {
        try (Document document = new Document(new PdfDocument(new PdfWriter(outputFile)))) {
            documentComposer.accept(document);
        }
    }

    public static Cell createCell(String text, Color color) {
        Cell cell = new Cell().setPadding(4).add(new Paragraph(text));
        if (color != null) {
            cell.setFontColor(color);
        }
        return cell;
    }
}
