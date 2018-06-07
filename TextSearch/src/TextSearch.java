import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.math.BigInteger;

public class TextSearch {

    private ArrayList<Document> files;
    private ArrayList<ReadFile> allThreads;
    private ArrayList<Boolean> running;
    private String toSearch;
    private ArrayList<KeywordSearcher> searcherForKeyWords;
    private ArrayList<Document> documents;

    public TextSearch(){
        String fileNames = "test";
        files = new ArrayList<>();
        allThreads = new ArrayList<>();
        running= new ArrayList<>();
        toSearch="";
        searcherForKeyWords = new ArrayList<>();
        documents = new ArrayList<>();




        //System.out.println(files.get(0).getContent());
    }

    public void waitForThreads(){

        try {
            for (ReadFile i : allThreads) {

                i.thread.join();

            }
        }catch (Exception e){

        }


//        while(true){
//            try{
//                for(int i = 0; i < allThreads.size() ; i++) {
//                    ReadFile t = allThreads.get(i);
//                    running.set(i,t.thread.isAlive());
//                    t.thread.join();
//
//                }
//            }catch (Exception e){
//
//            }
//            finishedRunning = false;
//            for(Boolean i : running){
//                finishedRunning = (finishedRunning || i);
//            }
//            if(!finishedRunning){
//                return;
//            }
//        }

    }

    public void setToSearch(String toSearch) {
        this.toSearch = toSearch;
    }

    public void addDocuments(){
        for(Document doc : files){
            KeywordSearcher key = new KeywordSearcher(doc);
            key.Search(toSearch);
            searcherForKeyWords.add(key);

        }
    }

    public void hitsFound(){
        for(KeywordSearcher k : searcherForKeyWords ){
            System.out.println("Hits found:"+ k.hitsFound() );

        }
    }
    public void readFiles(String fileNames){
        ReadFile rf = null;

        for(int i = 0; i < 10; i++){

            rf=new ReadFile(fileNames + i + ".txt");
            allThreads.add(rf);
            running.add(true);

        }
        waitForThreads();

        for(ReadFile i : allThreads){
            files.add(i.getDocument());
        }

    }

    public ArrayList<KeywordSearcher> getSearcherForKeyWords() {
        return searcherForKeyWords;
    }

    public static void main(String[] args) {

        TextSearch ts = new TextSearch();
        ts.readFiles("test");

        ts.setToSearch(" \"veritatis et quasi\" Zeyad test Hello in");
        ts.addDocuments();

        ts.hitsFound();
//        ArrayList<KeywordSearcher> ks = new ArrayList<>();


//        for(Document doc : ts.files){
//            KeywordSearcher key = new KeywordSearcher(doc);
//            key.Search(toSearch);
//            ks.add(key);
//
//        }





        //KeywordSearcher ks = new KeywordSearcher(ts.files.get(8));
        //ks.Search("this is amore tests yep\"test how are you bob\"5 9 9 6 5 6\"veritatis et quasi\" till going in \", cum\" ");

        //System.out.println("Hits found:"+ ks.hitsFound() );
//        int ret = ks.simpleSearch("veritatis");
//        System.out.println("found at:" + ret);
    }


    public static void main1(String[] args){

        TextSearch ts;
        BigInteger bigy = new BigInteger("0");

        int j = 0;
        for(int i = 0; i < 100; i++){

            Timestamp t = new Timestamp(System.currentTimeMillis());

            ts = new TextSearch();

            Timestamp t2 = new Timestamp(System.currentTimeMillis());

            BigInteger b = BigInteger.valueOf(   t2.getNanos() - t.getNanos()  );


            if(b.compareTo(BigInteger.valueOf( 0 ) ) == -1){

                //System.out.println("negative val: " + b.toString());
            }else{
                bigy = bigy.add(   b  );
                j++;
            }




        }

        bigy = bigy.divide(BigInteger.valueOf(j));

        System.out.println(bigy.toString() + " "+ j);
    }


}
