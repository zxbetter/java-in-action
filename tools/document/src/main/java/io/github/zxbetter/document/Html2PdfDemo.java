package io.github.zxbetter.document;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.layout.font.FontProvider;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * html转pdf
 *
 * @author zxbetter
 */
public class Html2PdfDemo {
    public static void main(String[] args) {
        ConverterProperties properties = new ConverterProperties();
        FontProvider font = new FontProvider();
        font.addFont("/Users/devin/Codes/Java/java-in-action/resources/fonts/simsun.ttf");
        properties.setFontProvider(font);

        // mkdir tmp
        File file = new File("/Users/devin/Codes/Java/java-in-action/tmp/Test.pdf");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);) {

            HtmlConverter.convertToPdf("<h1>你好,世界!</h1>", bufferedOutputStream, properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
