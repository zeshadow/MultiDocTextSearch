import java.util.*;





public class SearchHitWithKey{

    private String searchKey;
    public ArrayList<Integer> searchHits;

                    //key       //value
    private HashMap<Character, Integer> badMatchTable;
    private Integer otherChar;

    public SearchHitWithKey(String key){
        searchKey=key;
        searchHits=new ArrayList<>();

        badMatchTable = new HashMap<>();
        makeBadMatchTableValue();
    }

    private void makeBadMatchTableValue(){

        Character c = null; //key
        int v=0;  //value
        int l=searchKey.length();
        
        for(int i=0; i<l; i++){

            c=searchKey.charAt(i);
            v= l-i-1;

            if(!badMatchTable.containsKey(c)){
                badMatchTable.put(c,v);
            }else if (v>0){
                badMatchTable.replace(c,v);
            }
        }

        if(badMatchTable.get(c) == 0){
            badMatchTable.replace(c,l);
        }
        otherChar =l;
    }


    public String getSearchKey() {
        return searchKey;
    }

    public Integer getBadMatchTableValue(Character c){

        Integer i = badMatchTable.get(c);

        if(i != null)
            return i;
        else return otherChar;

    }








}