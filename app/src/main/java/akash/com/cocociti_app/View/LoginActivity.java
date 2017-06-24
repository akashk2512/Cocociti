package akash.com.cocociti_app.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import akash.com.cocociti_app.AppConstant;
import akash.com.cocociti_app.Model.SignInResponse;
import akash.com.cocociti_app.R;
import akash.com.cocociti_app.Services.SignInService;
import akash.com.cocociti_app.Utils.AppUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements SignInService.OnSignInListener , View.OnClickListener{

    @Bind(R.id.ed_username)
    EditText User_email;
    @Bind(R.id.ed_password)
    EditText password;
    @Bind(R.id.btn_signin)
    Button btnSignIn;
    String userEmail;
    String userPassword;
    private SignInService signInService = null;
    Context mContext;
    ProgressDialog dialog = null;
    StoreData storeData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        btnSignIn.setOnClickListener(this);
        mContext = LoginActivity.this;
        getSupportActionBar().setTitle("Login");
        dialog = new ProgressDialog(mContext);
        storeData = StoreData.getInstance();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (signInService != null){
            signInService.cancelRequest();
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_signin:

                userEmail = User_email.getText().toString().trim();
                userPassword = password.getText().toString().trim();
                Log.d("userDetails",userEmail+","+userPassword+","+userEmail.length());
                if (User_email.getText().toString().length()== 0 || password.getText().toString().length() == 0){

                    Toast.makeText(mContext,"Please enter email and password",Toast.LENGTH_LONG).show();
                }else {
                    callService();
                }

                break;

            default:
                break;
        }
    }

    public void callService(){

        if (AppUtils.isNetworkAvailable(mContext)){
            AppUtils.showLoadingDialog(mContext, dialog);

            signInService = new SignInService(this,this);
            signInService.getResponse(userEmail, userPassword);
        }else {
            Toast.makeText(mContext,"Please check internet connection",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onSignSuccess(SignInResponse response) {

        AppUtils.dissMissDailog(dialog);

        if (response != null){
            if (response.getStatus().equals(AppConstant.SUCCESS)){

                Log.d("Status"," Response "+response.getStatus());
                if (response.getUser() != null){
                    Log.d("Status"," user "+response.getUser().getAccess_token());
                    storeData.setAccessToken(response.getUser().getAccess_token());
                    storeData.setEmailId(response.getUser().getEmail());
                    Intent intent = new Intent(mContext, FeedActivity.class);
//                    intent.putExtra("token",response.getUser().getAccess_token());
//                    intent.putExtra("email",response.getUser().getEmail());
                    startActivity(intent);
                }

                AppUtils.showToast(mContext,response.getStatus().toString());

            }else {
                AppUtils.showToast(mContext,"status "+response.getStatus().toString());
            }

        }else {
            Log.d("Status"," Response "+response);
            AppUtils.showToast(mContext,"Sorry server not responding");
        }

    }

    @Override
    public void onSignFailure(Throwable error) {
        AppUtils.dissMissDailog(dialog);
        Log.d("Status","Failure "+error);
        AppUtils.showToast(mContext,"Sorry..!!! "+error);


    }

}
