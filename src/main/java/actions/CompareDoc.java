package actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CompareDoc {
    private static final Logger log = LogManager.getLogger(CompareDoc.class);

    public void compareTwo(String filePath1, String filePath2, String resultPath) throws IOException {
        /*Initializing the docs using poi-ooxml*/
        XWPFDocument doc1 = new XWPFDocument(new FileInputStream(filePath1));
        log.info("Loads source/first document(doc1): " + filePath1);
        XWPFDocument doc2 = new XWPFDocument(new FileInputStream(filePath2));
        log.info("Loads target/second document(doc2): " + filePath2);

        /*Fething all the paragraphs into a LIST*/
        List<XWPFParagraph> para1 = doc1.getParagraphs();
        log.info("Scanning Paragraphs/Lines from source(doc1)");
        log.info("Total Paragraphs/Lines found in doc1 " + para1.size());
        List<XWPFParagraph> para2 = doc2.getParagraphs();
        log.info("Scanning Paragraphs/Lines from target(doc2)");
        log.info("Total Paragraphs/Lines found in doc2 " + para2.size());


        /*max length for iteration */
        int max = (para1.size() > para2.size()) ? para1.size() : para2.size();
        String maxLines = (para1.size() > para2.size()) ? "doc1 has maximum " :
                (para1.size()<para2.size())?"doc2 has maximum":"Both documents has equal";
        log.info(maxLines+" number of paragraphs/lines -> "+max);

        /*Buffered writer to write the comparison in a FILE(mostly *.txt)*/
        BufferedWriter bw = new BufferedWriter(new FileWriter(resultPath));
        /*Difference counter*/
        int diff=0;
        log.info("Comparing...");
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

                /*diff counter*/
                diff++;
            }
        }
        bw.close();
        /*Difference log*/
        String result=(diff==0)?"No difference between the documents.":
                (diff>1)?"Found "+diff+" differences.":"Found a difference.";
        log.info(result);
        if(diff>0){
            log.info("Result file created: "+resultPath);
        }
    }

}
