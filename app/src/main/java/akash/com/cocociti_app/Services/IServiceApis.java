package akash.com.cocociti_app.Services;

import akash.com.cocociti_app.AppConstant;
import akash.com.cocociti_app.Model.FeedRequest;
import akash.com.cocociti_app.Model.FeedResponse;
import akash.com.cocociti_app.Model.SignInRequest;
import akash.com.cocociti_app.Model.SignInResponse;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Akash on 6/22/2017.
 */

public interface IServiceApis {

    @POST(AppConstant.METHOD_SIGNIN)
    Call<SignInResponse> getResponse(@Body SignInRequest signInRequest);

    @GET(AppConstant.METHOD_FEEDS)
    Call<FeedResponse> getResponse(@Body FeedRequest feedRequest);
}
