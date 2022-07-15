package photonPack;

import actions.CompareDoc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class DeleteLater {
    private static final Logger log = LogManager.getLogger(DeleteLater.class);

    public static void main(String[] args) throws IOException {
        /*Compare from compareTwo method*/
        CompareDoc compareDoc = new CompareDoc();
        compareDoc.compareTwo("src/main/java/photonPack/source.docx",
                "src/main/java/photonPack/target.docx",
                "src/main/java/photonPack/result.docx");


    }
}
