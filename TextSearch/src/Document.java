public class Document {

    private String title;
    private String content;

    public Document(String t, String c){

        title = t;
        content = c;

    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
    public int getContentLength(){
        return content.length();
    }
}
