import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class Reader {
    /**
     * this function read a single file
     * @param path a path to the file
     */

    public StringBuilder readOneFile(File path){
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
    public String[] fromDocToParts(StringBuilder Doc){
        String[] parts=new String[3];//first is document name //second is document header // third is document text
        parts[0]=fromDoctoDocNum(Doc);
        parts[1]=fromDoctoHeader(Doc);
        parts[2]=fromDoctoText(Doc);
        return parts;
    }
    public String fromDoctoText(StringBuilder Doc){
        String[] lines = Doc.toString().split("\\<TEXT>|\\</TEXT>");
        if(lines.length!=3)
            throw new ArithmeticException("problem in reading the doc number");
        return lines[1];

    }
    public String fromDoctoHeader(StringBuilder Doc){
        String[] lines = Doc.toString().split("\\<HEADER>|\\</HEADER>");
        if(lines.length!=3)
            throw new ArithmeticException("problem in reading the doc number");
        return lines[1];
    }
    public String fromDoctoDocNum(StringBuilder Doc){
        StringBuilder text =new StringBuilder();
        String[] lines = Doc.toString().split("\\<DOCNO>|\\</DOCNO>");
        if(lines.length!=3)
            throw new ArithmeticException("problem in reading the doc number");
        return lines[1];
    }

    public String[] getAllDirs(String path){
        File file = new File(path);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        return directories;
    }
    public String[] getAllFilespath(String path){
        String[] dirs=getAllDirs(path);
        String[] files=new String[dirs.length];
        for (int i=0;i<files.length;i++){
            files[i]=path + "//" + dirs[i] + "//" + dirs[i];
        }
        return files;
    }
}