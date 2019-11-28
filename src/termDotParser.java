/**
 * takes one term that contain dots and splits it into root terms
 */
public class termDotParser {
    public String[] parseDots(String term){
        String[] parts= term.split("\\.");
        if(parts.length==1)
            return parts;
        if(parts.length>2)
            return parts;
        if(!isInteger(parts[0])||!isInteger(parts[1]))
            return parts;
        String[] doubleNum=new String[1];
        doubleNum[0]=(parts[0]+"."+parts[1]);
        return doubleNum;
    }
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
