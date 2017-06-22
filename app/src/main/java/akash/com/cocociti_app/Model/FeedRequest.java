package akash.com.cocociti_app.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Akash on 6/22/2017.
 */

public class FeedRequest {
    @SerializedName("access_token")
    @Expose
    private String access_token;

    @SerializedName("email")
    @Expose
    private String email;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
