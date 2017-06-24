package akash.com.cocociti_app.View;

/**
 * Created by Akash on 6/23/2017.
 * SingleTone class for hold data and pass in other class
 */
public class StoreData {
    private static StoreData ourInstance = null;
    private static String accessToken;
    private static String emailId;

    public static StoreData getInstance() {

        if (ourInstance == null){
            ourInstance = new StoreData();
        }
        return ourInstance;
    }

    private StoreData() {

    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        StoreData.accessToken = accessToken;
    }

    public static String getEmailId() {
        return emailId;
    }

    public static void setEmailId(String emailId) {
        StoreData.emailId = emailId;
    }
}
