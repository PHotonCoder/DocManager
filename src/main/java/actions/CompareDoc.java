package actions;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CompareDoc {
    public void compareTwo(String filePath1, String filePath2, String resultPath) throws IOException {
        /*Initializing the docs using poi-ooxml*/
        XWPFDocument doc1 = new XWPFDocument(new FileInputStream(filePath1));
        XWPFDocument doc2 = new XWPFDocument(new FileInputStream(filePath2));

        /*Fething all the paragraphs into a LIST*/
        List<XWPFParagraph> para1 = doc1.getParagraphs();
        List<XWPFParagraph> para2 = doc2.getParagraphs();

        /*max length for iteration */
        int max = (para1.size() > para2.size()) ? para1.size() : para2.size();

        /*Buffered writer to write the comparison in a FILE(mostly *.txt)*/
        BufferedWriter bw = new BufferedWriter(new FileWriter(resultPath));
        for (int i = 0; i < max; i++) {
            /*fetching the paragraph text from both list elements*/
            String paraLine1 = para1.get(i).getParagraphText();
            String paraLine2 = para2.get(i).getParagraphText();
            /*check paragraph mismatch*/
            if (!paraLine1.equals(paraLine2)) {
                /*storing in a file*/
                bw.write(String.format("Line %s ", i + 1));
                bw.write(paraLine1 + "!=" + paraLine2);
                bw.newLine();
            }
        }
        bw.close();
    }
}
