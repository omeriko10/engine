public class termsEntitieChecker {
    public boolean isEntitie(String termA,String termB){
        char firstLetterA=termA.charAt(0);
        if(firstLetterA>64&&firstLetterA<91){
            char firstLetterB=termB.charAt(0);
            if(firstLetterB>64&&firstLetterB<91){
                return true;
            }
        }
        return false;
    }
}
