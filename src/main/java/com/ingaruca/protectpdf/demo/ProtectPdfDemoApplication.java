package com.ingaruca.protectpdf.demo;

import com.ingaruca.protectpdf.demo.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@SpringBootApplication
public class ProtectPdfDemoApplication implements CommandLineRunner {

  @Autowired
  private PDFService pdfService;

  public static void main(String[] args) {
    SpringApplication.run(ProtectPdfDemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Creating PDF.");
    pdfService.createPdf();
    System.out.println("PDF creation done.");

    byte[] input = Files.readAllBytes(Paths.get("/Users/ingarukadev/Documents/Projects/protect-pdf/message.pdf"));
    byte[] endodedInput = Base64.getEncoder().encode(input);

    String fileInBase64 = new String(endodedInput);

    System.out.println("base 64: " + fileInBase64);
  }
}
