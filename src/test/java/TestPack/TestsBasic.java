package TestPack;

import actions.CompareDoc;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestsBasic {
    private CompareDoc comp;
    private String doc1="";
    private String doc2="";
    private String result="";
    @BeforeClass
    void setUp(){
        comp=new CompareDoc();
        doc1="src/test/java/TestPack/source.docx";
        doc2="src/test/java/TestPack/target.docx";
        result="src/test/java/TestPack/result.txt";
    }

    @Test
    void Test1(){
        try {
            comp.compareTwo(doc1,doc2,result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void Test2(){
        doc2="src/test/java/TestPack/target1.docx";
        try {
            comp.compareTwo(doc1,doc2,result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
