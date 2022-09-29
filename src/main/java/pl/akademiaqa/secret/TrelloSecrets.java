package pl.akademiaqa.secret;

public class TrelloSecrets {

    private static final String KEY = "85a327a47f15de66e90717624eea42bc";
    private static final String TOKEN = "1d18f06cad68e937726a11f0d7e3c375ecfa23e49eb097aab56320070dfb43e3";

    private TrelloSecrets(){
    }

    public static String getKey(){
        return KEY;
    }

    public static String getToken(){
        return TOKEN;
    }

}
