import java.util.*;





public class SearchHitWithKey{

    private String searchKey;
    public ArrayList<Integer> searchHits;

    private HashMap<Character, Integer> badMatchTable;


    public SearchHitWithKey(String key){
        searchKey=key;
        searchHits=new ArrayList<>();

        badMatchTable = new HashMap<>();

    }

    private void makeBadMatchTableValue(){




    }


    public String getSearchKey() {
        return searchKey;
    }

    public Integer getBadMatchTableValue(Character c){

        Integer i = badMatchTable.get(c);

        if(i != null)
            return i;
        else return -1; //PlaceHolder until i get it set out

    }








}