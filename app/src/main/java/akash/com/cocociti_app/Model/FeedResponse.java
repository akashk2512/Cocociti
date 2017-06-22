package akash.com.cocociti_app.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akash on 6/22/2017.
 */

public class FeedResponse {

    @SerializedName("showcases")
    @Expose
    private List<Showcase> showcases = new ArrayList<>();

    public List<Showcase> getShowcases() {
        return showcases;
    }

    public void setShowcases(List<Showcase> showcases) {
        this.showcases = showcases;
    }
}
