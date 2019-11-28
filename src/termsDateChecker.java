/**
 * this class checking if two adjacent terms are one date
 */
public class termsDateChecker {
    public boolean isDate(String termA,String termB){
        if(isDay(termA)){
            if(isMonth(termB))
                return true;
        }
        if(isMonth(termA)){
            if(isDay(termB)||isYear(termB))
                return true;
        }
        return false;
    }
    public boolean isMonth(String term){
        if(term.equals("january")||term.equals("february")||term.equals("march")||term.equals("april")||term.equals("may")||term.equals("june")||term.equals("july")||term.equals("august")||term.equals("september")||term.equals("october")||term.equals("november")||term.equals("december"))
            return true;
        if(term.equals("jan")||term.equals("feb")||term.equals("mar")||term.equals("apr")||term.equals("may")||term.equals("jun")||term.equals("jul")||term.equals("aug")||term.equals("sep")||term.equals("oct")||term.equals("nov")||term.equals("dec"))
            return true;
        if(term.equals("January")||term.equals("February")||term.equals("March")||term.equals("April")||term.equals("May")||term.equals("June")||term.equals("July")||term.equals("August")||term.equals("September")||term.equals("October")||term.equals("November")||term.equals("December"))
            return true;
        if(term.equals("Jan")||term.equals("Feb")||term.equals("Mar")||term.equals("Apr")||term.equals("May")||term.equals("Jun")||term.equals("Jul")||term.equals("Aug")||term.equals("Sep")||term.equals("Oct")||term.equals("Nov")||term.equals("Dec"))
            return true;
        if(term.equals("JANUARY")||term.equals("FEBRUARY")||term.equals("MARCH")||term.equals("APRIL")||term.equals("MAY")||term.equals("JUNE")||term.equals("JULY")||term.equals("AUGUST")||term.equals("SEPTEMBER")||term.equals("OCTOBER")||term.equals("NOVEMBER")||term.equals("DECEMBER"))
            return true;
        if(term.equals("JAN")||term.equals("FEB")||term.equals("MAR")||term.equals("APR")||term.equals("MAY")||term.equals("JUN")||term.equals("JUL")||term.equals("AUG")||term.equals("SEP")||term.equals("OCT")||term.equals("NOV")||term.equals("DEC"))
            return true;
        if(term.equals("1")||term.equals("2")||term.equals("3")||term.equals("4")||term.equals("5")||term.equals("6")||term.equals("7")||term.equals("8")||term.equals("9")||term.equals("10")||term.equals("11")||term.equals("12"))
            return true;
        if(term.equals("01")||term.equals("02")||term.equals("03")||term.equals("04")||term.equals("05")||term.equals("06")||term.equals("07")||term.equals("08")||term.equals("09")||term.equals("10")||term.equals("11")||term.equals("12"))
            return true;
        return false;
    }
    public boolean isYear(String term){
        if(isInteger(term)){
            int y=Integer.parseInt(term);
            if(y>31&&y<10000)
                return true;
        }
        return false;
    }
    public boolean isDay(String term){
        if(isInteger(term)){
            int d=Integer.parseInt(term);
            if(d>0&&d<32)
                return true;
        }
        return false;
    }
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
