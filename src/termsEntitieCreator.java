public class termsEntitieCreator {
    public String createEntitie(String... terms){
        String entitie="";
        for (String term:terms) {
            entitie+=term + ' ';
        }
        entitie=entitie.substring(0,entitie.length()-1);
        return entitie;
    }
}
