package com.example.hallticket.controller;

import com.example.hallticket.model.HallTicketRequest;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/hallticket")
public class HallTicketController {
    @PostMapping(
            value = "/generate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public ResponseEntity<byte[]> generateHallTicket(@RequestBody HallTicketRequest request) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();

        document.add(new Paragraph("===== STUDENT HALL TICKET ====="));
        document.add(new Paragraph("Student ID : " + request.getStudentId()));
        document.add(new Paragraph("Name       : " + request.getStudentName()));
        document.add(new Paragraph("Branch     : " + request.getBranch()));
        document.add(new Paragraph("Exam Center: " + request.getExamCenter()));

        document.close();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=HallTicket.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(out.toByteArray());
    }

}
