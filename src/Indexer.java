import javax.print.Doc;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

public class Indexer {
    Reader reader=new Reader();
    HashMap<String, String> mainDictionary ;
    HashMap<String,DocInfo> DocumentsDictionary;
    Parse parser;

    public Indexer( boolean stemmerTF) {
        this.reader = new Reader();
        this.mainDictionary = new HashMap<>();
        DocumentsDictionary = new HashMap<>();
        this.parser = new Parse(stemmerTF);
    }

    public void index(String pathToCorpus){
        String[] filesPath=reader.getAllFilespath(pathToCorpus);
        for (String currFilePath:filesPath) {///loop throuh all files
            StringBuilder fileAsText=reader.readOneFile(new File(currFilePath));
            LinkedList<StringBuilder> multipleDocs=reader.fromFiletoDocs(fileAsText);//takes one file and break it to the documents inside
            for (StringBuilder doc:multipleDocs) {
                indexSingleDoc(doc);
            }
        }
    }
    private void indexSingleDoc(StringBuilder doc){///what to do with the header???????????????????????????
        String[] docParts=reader.fromDocToParts(doc);//explanatioon of the parts is in thee reader
        HashMap<String,Integer> uniqueTerms=parser.parse(docParts[2]);
        int maxTF=getMaxTermFreq(uniqueTerms);
        DocInfo currDocInfo=new DocInfo(maxTF);
        DocumentsDictionary.put(docParts[0],currDocInfo);
        for (String term:uniqueTerms.keySet()) {
            writeToPosting wtoPosting=new writeToPosting(term);            ////insert to posting
            wtoPosting.add(docParts[0],uniqueTerms.get(term));
        }
    }
    private int getMaxTermFreq(HashMap<String,Integer> terms){
        int max=0;
        for (Integer val:terms.values()) {
            if(val>max)
                max=val;
        }
        return max;
    }
}



