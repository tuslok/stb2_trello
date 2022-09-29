package pl.akademiaqa.secret;

public class TrelloSecrets {

    private static final String KEY = "YOUR_KEY";
    private static final String TOKEN = "YOUR_TOKEN";

    private TrelloSecrets(){
    }

    public static String getKey(){
        return KEY;
    }

    public static String getToken(){
        return TOKEN;
    }

}
