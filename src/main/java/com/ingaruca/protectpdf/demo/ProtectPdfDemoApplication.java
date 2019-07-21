package com.ingaruca.protectpdf.demo;

import com.ingaruca.protectpdf.demo.service.PDFService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Slf4j
@SpringBootApplication
public class ProtectPdfDemoApplication implements CommandLineRunner {

  @Autowired
  private PDFService pdfService;

  public static void main(String[] args) {
    SpringApplication.run(ProtectPdfDemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("Creating PDF.");
    pdfService.createPdf();
    log.info("PDF creation done.");

    byte[] input = Files.readAllBytes(Paths.get("/Users/ingarukadev/Documents/Projects/protect-pdf/message.pdf"));
    byte[] encodedInput = Base64.getEncoder().encode(input);

    String fileInBase64 = new String(encodedInput);

    log.info("base 64: " + fileInBase64);
  }
}
