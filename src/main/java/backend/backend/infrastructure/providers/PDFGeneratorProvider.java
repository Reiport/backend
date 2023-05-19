package backend.backend.infrastructure.providers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.itextpdf.html2pdf.HtmlConverter;

import org.thymeleaf.context.Context;

import backend.backend.application.common.interfaces.IPDFGenerator;

public class PDFGeneratorProvider implements IPDFGenerator {

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    public void generatePDF() {

        String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf.pdf";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("to", "5091");

            Context context = new Context();
            context.setVariables(map);

            String htmlFile = springTemplateEngine
                    .process(
                            "invoiceTemplate",
                            context);

            InputStream htmlInputStream = new ByteArrayInputStream(htmlFile.getBytes());
            FileOutputStream pdfOutputStream = new FileOutputStream(outputFolder);

            HtmlConverter.convertToPdf(htmlInputStream, pdfOutputStream);

            htmlInputStream.close();
            pdfOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
