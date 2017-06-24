package akash.com.cocociti_app.Services;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import akash.com.cocociti_app.AppConstant;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


/**
 * Created by Akash on 6/22/2017.
 */

public abstract class BaseService {

    protected Retrofit mRetrofit;

    public abstract void cancelRequest();

    public BaseService() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient();
        httpClient.interceptors().add(logging);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }
}
