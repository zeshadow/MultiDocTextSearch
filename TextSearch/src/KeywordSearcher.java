import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.lang.*;




public class KeywordSearcher {

    private Document doc;

    private ArrayList<SearchHitWithKey> searchKeys;
    private int hitsFound;




    //private ArrayList<Integer>searchHits;



    public KeywordSearcher(Document d){
        doc = d;
        hitsFound=-1;
        //searchHits = new ArrayList<>();
        searchKeys = new ArrayList<>();




    }

    public void Search(String searchTerms){
        //parse SearchTerm and store into keys
        parse(searchTerms);

        //Timestamp t = new Timestamp(System.currentTimeMillis());

        //Search returns the locations of the found strings, but the locations arnet in the same order
        searchKeys();

//        Timestamp t2 = new Timestamp(System.currentTimeMillis());
//
//        BigInteger b = BigInteger.valueOf(   t2.getNanos() - t.getNanos()  );
//
//        System.out.println(b.toString());
        //search each key and store location into searchHits


    }

    private void parse(String searchTerms){
        ArrayList<String> st = new ArrayList<>();
        String s = "";
        Boolean frontQuote=true;

        for(int i = 0; i < searchTerms.length(); i++){

            if(searchTerms.charAt(i) != '"'){
                s+=searchTerms.charAt(i);
            }else {
                if(frontQuote){
                    if(s.length()>0)
                        st.add(s);
                    s = "";
                    frontQuote=false;
                }else{
                    if(s.length()>0)

                        searchKeys.add(new SearchHitWithKey(s));
                    s="";
                    frontQuote=true;

                }

            }
        }
        if(s.length()>0)
            st.add(s);

        String delim = "[ ]";
        for(String i : st){
            String[] tokens = i.split(delim);

            for(String token : tokens){
                if(token.length() > 0)
                    searchKeys.add(new SearchHitWithKey(token) );
            }


        }

    }



    public void searchKeys(){

        ArrayList<Thread> threads = new ArrayList<>();

        for(SearchHitWithKey s : searchKeys) {
            Thread t = new Thread(){

                public void run(){

                    simpleSearch(s);
                }

            };
            threads.add(t);
            t.start();
//            searchHits.add(num);
        }

        try {
            for (Thread i : threads) {

                i.join();

            }
        }catch (Exception e){

        }





    }

    public void simpleSearch(SearchHitWithKey hitAndKey){

        String s = hitAndKey.getSearchKey();

        if(s.length() == 0){
            return ;
        }

        String data = doc.getContent();
        char a;
        char b;

        for(int i = 0; i < doc.getContentLength(); i++){

            a=data.charAt(i);
            b=s.charAt(0);

            if( a == b ){
                int sl = s.length();
                for(int j = 1; j < s.length();j++ ){

                    a=data.charAt(i+j);
                    b=s.charAt(j);
                    if(! (a == b)){
                       break;

                    }
                }
                if(a==b){
                    hitAndKey.searchHits.add(i+1);
                    //return i+1;
                }


            }

        }
        if(hitAndKey.searchHits.size() == 0)
            hitAndKey.searchHits.add(-1);
    }






    public int hitsFound(){

        if(hitsFound!= -1){
            return hitsFound;
        }
        hitsFound=0;

        for(SearchHitWithKey s : searchKeys){

            if(s.searchHits.size()>1){
                hitsFound++;
            }else if(s.searchHits.get(0) != -1 ){
                 hitsFound++;
            }

        }

        return hitsFound;
    }



}
