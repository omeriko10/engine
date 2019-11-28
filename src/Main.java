import javafx.scene.Parent;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        ReadFile rf =new ReadFile();
        Parse p=new Parse();
        String path="C:\\Users\\omer\\Desktop\\school\\information retrieval\\tstcorp\\docexample";
        StringBuilder t=  rf.readOneFile(path);
        LinkedList<String> a= p.parse(t);
        //System.out.println(a);

    }
}
