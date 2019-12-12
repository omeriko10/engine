public class termCommaParser {
    public String[] parsecommas(String term){
        String[] parts= term.split("\\,");
        if(parts.length==1||parts.length==0)
            return parts;
        int i=0;
        while (i<parts.length-1){
            if(!isInteger(parts[i]))
                return parts;
            if(isInteger(parts[i+1])){
                i++;
                continue;
            }
            else{
                return parts;
            }
        }
            String[] Num=new String[1];
            Num[0]="";
            for (int j=0;j<parts.length;j++){
                Num[0]+=parts[j]+ ',';
            }
            Num[0]=Num[0].substring(0,Num[0].length()-1);
            return Num;
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
