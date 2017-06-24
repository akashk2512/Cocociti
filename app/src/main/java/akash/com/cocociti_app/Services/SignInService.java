package akash.com.cocociti_app.Services;

import android.content.Context;

import akash.com.cocociti_app.Model.SignInRequest;
import akash.com.cocociti_app.Model.SignInResponse;
import akash.com.cocociti_app.Model.User;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Akash on 6/22/2017.
 */

public class SignInService extends BaseService {

    private Context context;
    private OnSignInListener signInCallBack = null;
    Call<SignInResponse> mCall = null;

    public SignInService(Context context, OnSignInListener signInCallBack) {
        this.context = context;
        this.signInCallBack = signInCallBack;
    }

    @Override
    public void cancelRequest() {

        if (mCall !=null){
            mCall = null;
            signInCallBack = null;
        }
    }

    public interface OnSignInListener {
        void onSignSuccess(SignInResponse response);
        void onSignFailure( Throwable error);
    }

    public void getResponse(String userEmail, String userPassword){

        User user = new User();
        user.setEmail(userEmail);
        user.setPassword(userPassword);

        SignInRequest signInRequest = new SignInRequest();

        signInRequest.setUser(user);

        IServiceApis serviceApis = mRetrofit.create(IServiceApis.class);
        mCall = serviceApis.getResponse("application/json","application/json" ,signInRequest);

        mCall.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Response<SignInResponse> response, Retrofit retrofit) {

                if (signInCallBack != null){
                    signInCallBack.onSignSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Throwable t) {

                if (signInCallBack != null){
                    signInCallBack.onSignFailure(t);
                }
            }
        });
    }


}
