import java.io.*;
import java.util.*;
import java.lang.*;

public class ReadFile implements Runnable{

    private String fileName = "./docs/";
    private String content = "";
    public Thread thread;

    ReadFile(String fName){

        fileName += fName;
        thread = new Thread(this, fileName);
//        run();
        thread.start();

    }

    public void run(){
        readFile();


    }

    private void readFile(){
        File file = new File(fileName);
        try{
            BufferedReader br= new BufferedReader(new FileReader(file));
            //System.out.println("File "+ fileName+ " found.");

            try {
                String read ;
                while((read = br.readLine()) != null){
                    content+= read + "\n";

                }
                
            }catch (IOException e){
                System.out.println("Empty File");

            }

//            System.out.println("done: "+ fileName);



        }catch(FileNotFoundException e){

            System.out.println("File "+ fileName+ " does not exist.");

        }




    }

    public Document getDocument() {


        return new Document(fileName,content);

    }
}
