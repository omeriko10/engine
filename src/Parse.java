import java.util.HashMap;
import java.util.LinkedList;

public class Parse {
    private boolean toStemmer;
    public Parse(boolean toStemmer) {
        this.toStemmer=toStemmer;
    }

    public HashMap<String,Integer> parse(String text){
        LinkedList<String> terms=fromTexttoTerms(text);
        parseDots(terms);
        parseCommas(terms);
        parseDates(terms);
        fixTerms(terms);
        parseNumbers(terms);
        parseEntities(terms);
        if(toStemmer){
            Stemmer stemer=new Stemmer();
            for (String term:terms) {
                term=parseStem(term,stemer);
            }
        }
        HashMap<String,Integer> parserResult=countSpecificTerms(terms);
        return parserResult;
    }
    /**
     * this function takes a text and split it into root terms
     * @param text
     */
    private LinkedList<String> fromTexttoTerms(String text){
        LinkedList<String> terms=new LinkedList<>();
        String delimiters="\\!|\\@|\\#|\\\\|\\*|\\^\\&|\\*|\\(|\\)\\+|\\=|\\{\\}|\\<|\\>|\\?|\\`|\\~|\\;|\\+|\\|\\n+|\\t+|\\s+|\\\"|\\'";
        String[] lines = text.split(delimiters);
        for (String term:lines) {
            if(!term.equals(""))
                terms.add(term);
        }
        return terms;
    }

    /**
     * this function takes the list of words and return it without dots except a decimal numbers
     * @param terms the list
     */
    public void parseDots(LinkedList<String> terms){
        termDotParser dotParser=new termDotParser();
        int i=0;
        while(i<terms.size()) {
            String curTerm=terms.get(i);
            if(curTerm.contains(".")){
                String[] temp=dotParser.parseDots(curTerm);
                terms.remove(i);
                if(temp.length==0){
                    i++;
                    continue;
                }
                for (String newTerm:temp) {
                    if(newTerm.equals(""))
                        continue;
                    terms.add(i,newTerm);
                    i++;
                }
            }
            else
                i++;
        }
    }
    public void parseCommas(LinkedList<String> terms){
        termCommaParser comap=new termCommaParser();
        int i=0;
        while(i<terms.size()) {
            String curTerm=terms.get(i);
            if(curTerm.contains(",")){
                String[] temp=comap.parsecommas(curTerm);
                terms.remove(i);
                if(temp.length==0){
                    i++;
                    continue;
                }
                for (String newTerm:temp) {
                    if(newTerm.equals(""))
                        continue;
                    terms.add(i,newTerm);
                    i++;
                }
            }
            else
                i++;
        }
    }

    /**
     * takes the list of words and return it with exact dates
     * @param terms list of terms to parse
     */
    public void parseDates(LinkedList<String> terms){
        termsDateCreator creator=new termsDateCreator();
        termsDateChecker checker=new termsDateChecker();
        int i=0;
        while(i<terms.size()-1) {
            String termA=terms.get(i);
            String termB=terms.get(i+1);
            if(checker.isDate(termA,termB)){
                String newDate=creator.createDate(termA,termB);
                terms.remove(i);
                terms.remove(i);
                terms.add(i,newDate);
            }
            i++;
        }
    }

    public void parseEntities(LinkedList<String> terms){
        int i=0;
        termsEntitieChecker checker=new termsEntitieChecker();
        termsEntitieCreator creator=new termsEntitieCreator();
        int listSize=terms.size()-1;
        while(i<listSize) {
            boolean isEntitie =checker.isEntitie(terms.get(i),terms.get(i+1));
            if(isEntitie){
                int j=i+1, counter=1;
                while (j<listSize){
                    boolean nextEntitie=checker.isEntitie(terms.get(j),terms.get(j+1));
                    if(nextEntitie){
                        counter++;
                        j++;
                    }else
                        break;
                }
                int k=0;
                String[] entities=new String[counter+1];
                while(k<=counter){
                    entities[k]=terms.get(i+k);
                    k++;
                }
                String newName=creator.createEntitie(entities);
                terms.addLast(newName);
                i+=counter+1;
                continue;
            }
            i++;
        }
    }

    public  void parseNumbers(LinkedList<String> terms){
        termsNumbers numberCreator=new termsNumbers();
        String[] result;
        int i=0,j;
        while(i<terms.size()-4) {
            result=numberCreator.convertNumber(terms.get(i),terms.get(i+1),terms.get(i+2),terms.get(i+3));
            if(result!=null){
                j=i;
                terms.remove(i);
                terms.remove(i);
                terms.remove(i);
                terms.remove(i);
                for(String s:result){
                    if(s!=null){
                        terms.add(j,s);
                        j++;
                    }
                }
            }
            i++;
        }
        i=terms.size();
        result=numberCreator.convertNumber(terms.get(i-3),terms.get(i-2),terms.get(i-1),"");
        if(result!=null) {
            terms.remove(i-1);
            terms.remove(i-2);
            terms.remove(i-3);
            for (int index = 0; index < 3; index++) {
                if (result[index] != null)
                    terms.add(result[index]);
            }
        }
        i=terms.size();
        result=numberCreator.convertNumber(terms.get(i-2),terms.get(i-1),"","");
        if(result!=null) {
            terms.remove(i-1);
            terms.remove(i-2);
            for (int index = 0; index < 2; index++) {
                if (result[index] != null)
                    terms.add(result[index]);
            }
        }
        i=terms.size();
        result=numberCreator.convertNumber(terms.get(i-1),"","","");
        if(result!=null){
            terms.remove(i-1);
            terms.add(result[0]);
        }

    }

    public void fixTerms(LinkedList<String> terms){
        termsNumbers tn=new termsNumbers();
        for(int i=0;i<terms.size();i++){
            String[] result=tn.fixNumber(terms.get(i));
            if(result!=null){
                terms.remove(i);
                terms.add(i,result[0]);
                terms.add(i+1,result[1]);
            }
        }
    }

    public String parseStem(String term,Stemmer stemer){
        for (int i = 0; i < term.length(); i++) {
            if(term.charAt(i)<97||term.charAt(i)>122){
                stemer.stem();
                return term;
            }
            stemer.add(term.charAt(i));
        }
        stemer.stem();
        return stemer.toString();
    }

    public void parseHypen(LinkedList<String> terms){
        for (String term:terms) {
            if(term.contains("-")){

            }
        }
    }////didnt touch it



    public HashMap<String,Integer> countSpecificTerms(LinkedList<String> terms){
        HashMap<String,Integer> frecList=new HashMap<>();
        for(String term:terms){
            if(frecList.containsKey(term)){
                Integer frec=frecList.get(term)+1;
                frecList.remove(term);
                frecList.put(term,frec);
            }
            else
                frecList.put(term,1);
        }
        return frecList;
    }




}
