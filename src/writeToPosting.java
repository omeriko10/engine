
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

public class writeToPosting {
    private HashMap<String,Integer> data;
    private File posting;
    private final String path="postingFiles//";

    public writeToPosting(String term) {
        try {
            data=new HashMap<>();
            posting=new File(path+term + ".txt");
            if(!posting.createNewFile())
                load();
        }
        catch (Exception e){
            System.out.println(e.toString());
        };

    }

    public void add(String docID,Integer frec){
        try {
            String input=docID+","+frec.toString();
            FileWriter fw=new FileWriter(posting,true);
            PrintWriter pw=new PrintWriter(fw);
            pw.println(input);
            data.put(docID,frec);
            pw.close();
            fw.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        };
    }

    private void load(){
        try {
            String line;
            String[] split_data;
            FileReader fr = new FileReader(posting);
            BufferedReader br=new BufferedReader(fr);
            line=br.readLine();
            while (line!=null){
                split_data=line.split(",");
                data.put(split_data[0],Integer.parseInt(split_data[1]));
                line=br.readLine();
            }
            br.close();
            fr.close();
        }
        catch (Exception e){};
    }





}

