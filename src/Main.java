
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Indexer ind=new Indexer(false);
        ind.index("C://Users//omer//Desktop//example");
    }
}
/*Reader r=new Reader();
        StringBuilder s=r.readOneFile(new File("C://Users//omer//Desktop//example//FB396001//FB396001"));
        LinkedList<StringBuilder> slist=r.fromFiletoDocs(s);
        StringBuilder oneDoc=slist.getFirst();
        String text=r.fromDoctoText(oneDoc);
        Parse p=new Parse(false);
        HashMap<String,Integer> h=p.parse(text);*/