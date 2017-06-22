package akash.com.cocociti_app.Services;

import android.content.Context;

import akash.com.cocociti_app.Model.FeedRequest;
import akash.com.cocociti_app.Model.FeedResponse;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Akash on 6/22/2017.
 */

public class FeedDataService extends BaseService {


    private OnFeedListener feedCallBack = null;
    private Context context;
    Call<FeedResponse> mCall = null;


    @Override
    public void cancelRequest() {

        if (mCall != null){
            mCall= null;
            feedCallBack = null;
        }
    }

    public FeedDataService(Context context, OnFeedListener feedCallBack) {
        this.context = context;
        this.feedCallBack = feedCallBack;
    }

    public interface OnFeedListener {
        void onSuccessFeed(FeedResponse response);
        void onFailureFeed(Throwable error);
    }

    public void getResponse(String email, String token){

        final FeedRequest feedRequest = new FeedRequest();
        feedRequest.setEmail(email);
        feedRequest.setAccess_token(token);

        IServiceApis serviceApis = mRetrofit.create(IServiceApis.class);
        mCall = serviceApis.getResponse(feedRequest);
        mCall.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Response<FeedResponse> response, Retrofit retrofit) {
                if (feedCallBack != null){
                    feedCallBack.onSuccessFeed(response.body());
                }
            }

            @Override
            public void onFailure(Throwable t) {

                if (feedCallBack != null){
                    feedCallBack.onFailureFeed(t);
                }
            }
        });
    }
}
