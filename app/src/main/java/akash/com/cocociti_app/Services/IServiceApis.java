package akash.com.cocociti_app.Services;

import akash.com.cocociti_app.AppConstant;
import akash.com.cocociti_app.Model.FeedRequest;
import akash.com.cocociti_app.Model.FeedResponse;
import akash.com.cocociti_app.Model.SignInRequest;
import akash.com.cocociti_app.Model.SignInResponse;
import akash.com.cocociti_app.View.StoreData;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Akash on 6/22/2017.
 */

public interface IServiceApis {

    @POST(AppConstant.METHOD_SIGNIN)
    Call<SignInResponse> getResponse(@Header("Content-Type") String contentType,
                                     @Header("Accept") String content,
                                     @Body SignInRequest signInRequest);

    @GET(AppConstant.METHOD_FEEDS)
    Call<FeedResponse> getResponse(@Header("X-ACCESS-TOKEN") String token,
                                   @Header("X-USER-EMAIL") String email);

}
