/**
 * creates one date from two adjacent words
 */
public class termsDateCreator {
    public String createDate(String termA,String termB){
        termsDateChecker dateChecker=new termsDateChecker();
        if(dateChecker.isMonth(termA)&&dateChecker.isDay(termB))
            return createMonthDay(termA,termB);
        if(dateChecker.isDay(termA)&&dateChecker.isMonth(termB))
            return createMonthDay(termB,termA);
        return createYearDate(termB,termA);

    }
    public String createYearDate(String year,String month){
        return (year + "-" + createMonth(month));
    }
    public String createMonthDay(String month,String day){
        return (createMonth(month) + "-" + createDay(day));
    }
    private String createDay(String day){
        int d=Integer.parseInt(day);
        if(d<10)
            return ("0" + d);
        else
            return day;
    }
    private String createMonth(String month){
        if(month.equals("january")||month.equals("jan")||month.equals("January")||month.equals("Jan")||month.equals("JANUARY")||month.equals("JAN")||month.equals("1")||month.equals("01"))
            return "01";
        if(month.equals("february")||month.equals("feb")||month.equals("February")||month.equals("Feb")||month.equals("FEBRUARY")||month.equals("FEB")||month.equals("2")||month.equals("02"))
            return "02";
        if(month.equals("march")||month.equals("mar")||month.equals("March")||month.equals("Mar")||month.equals("MARCH")||month.equals("MAR")||month.equals("3")||month.equals("03"))
            return "03";
        if(month.equals("april")||month.equals("apr")||month.equals("April")||month.equals("Apr")||month.equals("APRIL")||month.equals("APR")||month.equals("4")||month.equals("04"))
            return "04";
        if(month.equals("may")||month.equals("May")||month.equals("MAY")||month.equals("5")||month.equals("05"))
            return "05";
        if(month.equals("june")||month.equals("jun")||month.equals("June")||month.equals("Jun")||month.equals("JUNE")||month.equals("JUN")||month.equals("6")||month.equals("06"))
            return "06";
        if(month.equals("july")||month.equals("jul")||month.equals("July")||month.equals("Jul")||month.equals("JULY")||month.equals("JUL")||month.equals("7")||month.equals("07"))
            return "07";
        if(month.equals("august")||month.equals("aug")||month.equals("August")||month.equals("Aug")||month.equals("AUGUST")||month.equals("AUG")||month.equals("8")||month.equals("08"))
            return "08";
        if(month.equals("september")||month.equals("sep")||month.equals("September")||month.equals("Sep")||month.equals("SEPTEMBER")||month.equals("SEP")||month.equals("9")||month.equals("09"))
            return "09";
        if(month.equals("october")||month.equals("oct")||month.equals("October")||month.equals("Oct")||month.equals("OCTOBER")||month.equals("OCT")||month.equals("10"))
            return "10";
        if(month.equals("november")||month.equals("nov")||month.equals("November")||month.equals("Nov")||month.equals("NOVEMBER")||month.equals("NOV")||month.equals("11"))
            return "11";
        if(month.equals("december")||month.equals("dec")||month.equals("December")||month.equals("Dec")||month.equals("DECEMBER")||month.equals("DEC")||month.equals("12"))
            return "12";
        return "";
    }
}
