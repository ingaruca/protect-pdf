package com.ingaruca.protectpdf.demo.service;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.PDFEncryption;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class PDFService {

  @Autowired
  private TemplateEngine templateEngine;

  public void createPdf() throws IOException, DocumentException {
    Context context = new Context();
    context.setVariable("name", "Luis Ingaruca");
    String processHtml = templateEngine.process("helloworld", context);

    OutputStream outputStream = new FileOutputStream("message.pdf");

    PDFEncryption pdfEncryption = new PDFEncryption();
    String password = "123456";
    pdfEncryption.setUserPassword(password.getBytes());

    ITextRenderer renderer = new ITextRenderer();
    renderer.setPDFEncryption(pdfEncryption);
    renderer.setDocumentFromString(processHtml);
    renderer.layout();
    renderer.createPDF(outputStream, false);
    renderer.finishPDF();
    outputStream.close();

  }

}
