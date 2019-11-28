import com.sun.deploy.util.StringUtils;

import java.util.LinkedList;

public class Parse {

    public LinkedList<String> parse(StringBuilder doc){
        String[] parts = doc.toString().split("\\<TEXT>|\\</TEXT>");
        if(parts.length!=3)
            return null;
        String text=parts[1];
        LinkedList<String> terms=fromTexttoTerms(text);
        parseDots(terms);
        //parse numbers
        parseDates(terms);
        return terms;
    }
    /**
     * this function takes a text and split it into root terms
     * @param text
     */
    private LinkedList<String> fromTexttoTerms(String text){
        LinkedList<String> terms=new LinkedList<>();
        String delimiters="\\!|\\@|\\#|\\\\|\\*|\\^\\&|\\*|\\(|\\)\\+|\\=|\\{\\}|\\<|\\>|\\?|\\`|\\~|\\;|\\+|\\|\\n+|\\t+|\\s+|\\\"|\\'|\\/";
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
    private void parseDots(LinkedList<String> terms){
        termDotParser dotParser=new termDotParser();
        int i=0;
        while(i<terms.size()) {
            String curTerm=terms.get(i);
            if(curTerm.contains(".")){
                String[] temp=dotParser.parseDots(curTerm);
                terms.remove(i);
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






}
