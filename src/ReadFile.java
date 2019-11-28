import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class ReadFile {
    /**
     * this function read a single file
     * @param path a path to the file
     */

    public StringBuilder readOneFile(String path){
        InputStream is;
        StringBuilder sb = new StringBuilder();
        try {
                is = new FileInputStream(path);
                BufferedReader buf = new BufferedReader(new InputStreamReader(is));
                String line = buf.readLine();
                while(line != null){
                    sb.append(line).append("\n");
                    line = buf.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return sb;
    }


    /**
     * this function takes entire file and seperate it into multiple docs
     * @param file the file we want to seperate
     * @return list of documents
     */
    public LinkedList<StringBuilder> fromFiletoDocs(StringBuilder file){
        LinkedList<StringBuilder> docs =new LinkedList<>();
        String[] lines = file.toString().split("\\n");
        int i=0;
        while(i<lines.length){
            if(lines[i].equals("<DOC>")){
                i++;
                StringBuilder temp=new StringBuilder();
                while(!lines[i].equals("</DOC>")){
                    temp.append(lines[i]);
                    temp.append("\n");
                    i++;
                }
                docs.add(temp);
            }
            i++;
        }
        return docs;
    }

    /**
     * the function takes a documents and return its text part
     * @param Doc the current document
     * @return the text
     */
    public StringBuilder fromDoctoText(StringBuilder Doc){
        StringBuilder text =new StringBuilder();
        String[] lines = Doc.toString().split("\\n");
        int i=0;
        while(i<lines.length){
            if(lines[i].equals("<TEXT>")){
                i++;
                while(!lines[i].equals("</TEXT>")){
                    text.append(lines[i]);
                    text.append("\n");
                    i++;
                }
                break;
            }
            i++;
        }
        return text;

    }

    public LinkedList<StringBuilder> readAllFiles(String path){
        LinkedList<StringBuilder> allDoc=new LinkedList<StringBuilder>();
        File mainDirectory = new File(path);
        File[] fList = mainDirectory.listFiles();
        for (File file:fList) {
            File directory=new File(file.getPath()+ "//" +file.getName());
            StringBuilder oneFile=readOneFile(directory.getPath());
            LinkedList<StringBuilder> docs = fromFiletoDocs(oneFile);
            allDoc.addAll(docs);
        }
        return allDoc;
    }

}

