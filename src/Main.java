import javafx.scene.Parent;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Parse p=new Parse();
        String path= "C:\\Users\\omer\\Desktop\\school\\information retrieval\\corpus";
        String[] directories= getAllDirs(path);
        for(int i=0;i<100;i++){
            String sp=path + "\\" + directories[i];
            File dir =new File(sp);
            System.out.println(dir.listFiles());
        }
    }
    public static String[] getAllDirs(String path){
        File file = new File(path);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        return directories;
    }

}
