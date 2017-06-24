package akash.com.cocociti_app.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import akash.com.cocociti_app.Model.FeedResponse;
import akash.com.cocociti_app.Model.Showcase;
import akash.com.cocociti_app.R;
import akash.com.cocociti_app.Services.FeedDataService;
import akash.com.cocociti_app.Utils.AppUtils;
import akash.com.cocociti_app.View.Adapters.ShowCaseAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class FeedActivity extends AppCompatActivity implements FeedDataService.OnFeedListener {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private FeedDataService feedDataService = null;
    private Context mContext;
    ProgressDialog dialog = null;
//    Intent intentData;
    public List<Showcase> showcaseList = null;
    private ShowCaseAdapter showCaseAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);
        mContext = FeedActivity.this;
        dialog = new ProgressDialog(mContext);
//        intentData = getIntent();
        getSupportActionBar().setTitle("Show Case");

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        callService();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (feedDataService != null){
            feedDataService = null;
        }
    }

    void callService(){

        if (AppUtils.isNetworkAvailable(mContext)) {
            showcaseList.clear();
            AppUtils.showLoadingDialog(mContext, dialog);
            feedDataService = new FeedDataService(this, this);
//            feedDataService.getResponse(intentData.getStringExtra("email"), intentData.getStringExtra("token"));
            feedDataService.getResponse(StoreData.getEmailId(), StoreData.getAccessToken());
        }else {
            Toast.makeText(mContext,"Please check internet connection",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSuccessFeed(FeedResponse response) {

        AppUtils.dissMissDailog(dialog);
        if (response != null){
            showcaseList = new ArrayList<>();
            for (Showcase showcase : response.getShowcases()){
                showcaseList.add(showcase);
                Log.d("ShowCaseData","response "+showcase.getId()+" title "+showcase.getTitle()+" description "+showcase.getDescription()+" year "+showcase.getYear());
            }

            // set Adapter here pass arrayList inside adapter

            showCaseAdapter = new ShowCaseAdapter(mContext, showcaseList);
            recyclerView.setAdapter(showCaseAdapter);
            showCaseAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onFailureFeed(Throwable error) {
        AppUtils.dissMissDailog(dialog);
        Log.d("ShowCaseData","Failure "+error);
    }
}
