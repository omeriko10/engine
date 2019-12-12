import java.math.RoundingMode;
import java.text.DecimalFormat;

public class termsNumbers {
    String[] symbols = {"k", "K", "m", "M", "bn", "b", "B", "%", "$"};


    private boolean checkNumberUnitK(String term) {
        if (term.equals("K") || term.equals("k"))
            return true;
        if (term.equals("Thousand") || term.equals("thousand"))
            return true;
        return false;
    }

    private boolean checkNumberUnitM(String term) {
        if (term.equals("M") || term.equals("m"))
            return true;
        if (term.equals("Million") || term.equals("million"))
            return true;
        return false;
    }

    private boolean checkNumberUnitB(String term) {
        if (term.equals("B") || term.equals("b"))
            return true;
        if (term.equals("Billion") || term.equals("billion") || term.equals("bn"))
            return true;
        return false;
    }

    private boolean checkNumberUnitT(String term) {
        if (term.equals("trillion"))
            return true;
        return false;
    }

    private boolean checkNumberUnitPrice(String term) {
        if (term.equals("Dollars") || term.equals("dollars"))
            return true;
        if (term.equals("$"))
            return true;
        return false;
    }

    private boolean checkNumberUnitUS(String term) {
        if (term.equals("U.S."))
            return true;
        return false;
    }

    private boolean checkNumberUnitPercent(String term) {
        if (term.equals("%"))
            return true;
        if (term.equals("percent") || term.equals("percentage"))
            return true;
        return false;
    }

    private boolean checkNumberIsFraction(String term) {
        if (term.contains("/"))
            return true;
        return false;
    }

    private boolean checkIfStringIsNumber(String term) {
        String temp = term.replaceAll(",", "");
        temp = temp.replaceAll("/", "");
        try {
            double check = Double.parseDouble(temp);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private boolean checkTermIsUnit(String term) {
        if(checkNumberUnitK(term) || checkNumberUnitM(term)|| checkNumberUnitB(term)||checkNumberUnitT(term))
            return true;
        return false;
    }

    public String[] fixNumber(String term) {
        String[] result = new String[2];
        boolean findFix = false;
        for (String s : symbols) {
            findFix=false;
            if (term.contains(s)) {
                int index = term.indexOf(s);
                if (s.equals("$")) {
                    if (index == term.length() - 1) {
                        result[0] = term.substring(0, index);
                        result[1] = s;
                        findFix = true;
                    } else if (index == 0) {
                        result[0] = term.substring(1);
                        result[1] = s;
                        findFix = true;
                    }

                } else {
                    if (index == term.length() - 1) {
                        result[0] = term.substring(0, index);
                        result[1] = s;
                        findFix = true;
                    }
                }
            }
            if(findFix && checkIfStringIsNumber(result[0]))
                return result;
        }
        return null;
    }

    public String[] convertNumber(String term1,String term2,String term3,String term4) {
        String[] result=new String[4];
        for(int i=0;i<result.length;i++){
            result[i]=null;
        }
        if (!checkIfStringIsNumber(term1))
            return null;
        //----------------------------price------------------
        if(checkTermIsUnit(term2) && checkNumberUnitUS(term3) && checkNumberUnitPrice(term4)) {
            result[0]= convertBigPriceNumber(term1, term2);
            return result;
        }
        if(checkTermIsUnit(term2) && checkNumberUnitPrice(term3)) {
            result[0]=convertBigPriceNumber(term1, term2);
            result[3]=term4;
            return result;
        }
        if(checkNumberUnitPrice(term2) && checkTermIsUnit(term3)) {
            result[0]=convertBigPriceNumber(term1, term3);
            result[3]=term4;
            return result;
        }
        if(checkNumberIsFraction(term2) && checkNumberUnitPrice(term3)) {
            result[0]=convertFractionPirce(term1, term2, term3);
            result[3]=term4;
            return result;
        }
        if(checkNumberUnitPrice(term2)) {
            result[0]=convertPriceNumber(term1);
            result[2]=term3;
            result[3]=term4;
            return result;
        }

        //-------------percent-------------------
        if (checkNumberUnitPercent(term2)) {
            result[0]=convertPercentNumber(term1);
            result[2]=term3;
            result[3]=term4;
            return result;
        }

        //--------------------regular number----------
        if(checkNumberIsFraction(term3) && checkTermIsUnit(term2)) {
            result[0]=convertBigNumberFraction(term1, term3, term2);
            result[3]=term4;
            return result;
        }
        if(checkTermIsUnit(term2)) {
            result[0]=convertBigNumber(term1, term2);
            result[2]=term3;
            result[3]=term4;
            return result;
        }
        if(checkNumberIsFraction(term2) && !checkNumberIsFraction(term1)) {
            result[0]=convertRegularNumber(term1) + " " + convertFractionNumber(term2);
            result[2]=term3;
            result[3]=term4;
            return result;
        }
        if(checkNumberIsFraction(term1)) {
            result[0]=convertFractionNumber(term1);
            result[1]=term2;
            result[2]=term3;
            result[3]=term4;
            return result;
        }
        result[0]= convertRegularNumber(term1);
        result[1]=term2;
        result[2]=term3;
        result[3]=term4;
        return result;
    }





    private String convertFractionPirce(String number, String farc, String price) {
        return number+" "+farc+ " Dollars";
    }

    private String convertRegularNumber(String number) {
        String result;
        result = number.replaceAll(",", "");
        double num = Double.parseDouble(result);
        DecimalFormat dc = new DecimalFormat("#.###");
        dc.setRoundingMode(RoundingMode.FLOOR);
        if (num < 1000) {
            num = Double.parseDouble(dc.format(num));
            if (!checkDecimal(num)) {
                int tempNum = (int) num;
                result = Integer.toString(tempNum);
            }
            else {
                result = Double.toString(num);
            }
        }
        else if (num < 1000000) {
            num = num / 1000;
            num = Double.parseDouble(dc.format(num));
            if (!checkDecimal(num)) {
                int tempNum = (int) num;
                result = Integer.toString(tempNum) + "K";
            }
            else {
                result = Double.toString(num) + "K";
            }
        }
        else if (num < 1000000000) {
            num = num / 1000000;
            num = Double.parseDouble(dc.format(num));
            if (!checkDecimal(num)) {
                int tempNum = (int) num;
                result = Integer.toString(tempNum) + "M";
            }
            else {
                result = Double.toString(num) + "M";
            }
        }
        else {
            num = num / 1000000000;
            num = Double.parseDouble(dc.format(num));
            if (!checkDecimal(num)) {
                int tempNum = (int) num;
                result = Integer.toString(tempNum) + "B";
            }
            else {
                result = Double.toString(num) + "B";
            }
        }
        return result;
    }

    private String convertPriceNumber(String number){
        String result;
        result = number.replaceAll(",", "");
        double num=Double.parseDouble(result);
        if(num>=1000000) {
            double tempnum;
            String unit;
            if(num>=1000000000){
                unit="B";
                tempnum=num/1000000000;
            }
            else{
                unit="M";
                tempnum=num/1000000;
            }
            if(checkDecimal(tempnum)){
                return convertBigPriceNumber(Double.toString(tempnum),unit);
            }
            else{
                int intnum=(int)tempnum;
                return convertBigPriceNumber(Integer.toString(intnum),unit);
            }

        }
        return number+" Dollars";
    }
    private String convertFractionNumber(String number){
        return number;
    }
    private String convertPercentNumber(String number){
        return number+"%";
    }

    private String convertBigPriceNumber(String number,String unit) {
        String result=new String();
        if (checkNumberUnitM(unit))
            result = number + " M Dollars";
        else if (checkNumberUnitB(unit)) {
            result = number + ",000" + " M Dollars";
        } else if (checkNumberUnitT(unit)) {
            result = number + ",000,000" + " M Dollars";
        }
        return result;
    }


    private String convertBigNumber(String number, String unit) {
        String result=new String();
        if(checkNumberIsFraction(number)){
            if(checkNumberUnitK(unit))
                result=convertFractionNumber(number)+"K";
            else if(checkNumberUnitM(unit))
                result=convertFractionNumber(number)+"M";
            else if(checkNumberUnitB(unit))
                result=convertFractionNumber(number)+"B";
            return result;
        }
        result = number.replaceAll(",", "");
        double num=Double.parseDouble(result);
        if(checkNumberUnitK(unit))
            num=num*1000;
        else if(checkNumberUnitM(unit))
            num=num*1000000;
        else if(checkNumberUnitB(unit))
            num=num*1000000000;
        return convertRegularNumber(Double.toString(num));
    }
    private String convertBigNumberFraction(String number, String frac, String unit) {
        String result=convertBigNumber(number,unit)+" "+convertFractionNumber(frac);
        return result;
    }


    private boolean checkDecimal(double num){
        int tempNum=(int)num;
        double sub=num-tempNum;
        if(sub==0){
            return false;
        }
        return true;
    }

}
