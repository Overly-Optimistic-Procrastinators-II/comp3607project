package comp3607project.tool;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import comp3607project.TestResult;

import java.util.ArrayList;

public class PDFGenerator {
    private static Font defaultFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.BLACK);
    private static Font headerFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
    public static void generate(String filePath, ArrayList<TestResult> summary, int totalMark) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(new File(filePath, "results.pdf")));
            document.open();
            addMetaData(document);
            addContent(document, summary);
            document.add(new Paragraph("Total Mark: " + totalMark));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addContent(Document document, ArrayList<TestResult> summary) throws DocumentException {
        if (summary.isEmpty()) {
            document.add(new Paragraph("This submission has invalid files", defaultFont));
        }

        else {
            for (TestResult result : summary) {
                PdfPCell nameCell = new PdfPCell(new Paragraph(result.getTestName(), defaultFont));
                nameCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                table.addCell(nameCell);

                PdfPCell scoreCell = new PdfPCell(new Paragraph(String.valueOf(result.getStatus()), defaultFont));
                scoreCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER); 
                table.addCell(scoreCell);

                PdfPCell commentsCell = new PdfPCell(new Paragraph(String.valueOf(result.getMark()), defaultFont));
                commentsCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER); 
                table.addCell(commentsCell);
            }
        }
    }

    private static void addMetaData(Document document) {
        document.addTitle("COMP 2603 A1 Results");
        document.addSubject("");
        document.addKeywords("Java, OOP1, COMP2603, A1");
        document.addAuthor("Overly-Optimistic Procrastinators II");
        document.addCreator("Overly-Optimistic Procrastinators II");
    }
}

/*
 * References: 
 * 
 * - https://www.vogella.com/tutorials/JavaPDF/article.html
 * 
 */
