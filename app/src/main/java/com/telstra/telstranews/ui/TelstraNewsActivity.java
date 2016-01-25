package com.telstra.telstranews.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.telstra.telstranews.R;
import com.telstra.telstranews.model.NewsDataResponse;
import com.telstra.telstranews.model.NewsRowData;
import com.telstra.telstranews.network.NewsDataNetworkCallback;
import com.telstra.telstranews.network.NewsRequestService;
import com.telstra.telstranews.util.Utils;

import java.util.ArrayList;

/**
 * @author Kumarappan Subramanian
 *         <p/>
 *         Created on 1/21/16.
 *         <p/>
 *         <p/>
 *         TelstraNewsActivity is used to show the News feeds. It is using Re-cycler view to show the
 *         list.
 *         <p/>
 *         We can able to pull down to refresh the list view and update the news feeds.
 */
public class TelstraNewsActivity extends AppCompatActivity {


    /*
     * RecyclerView - to list out all the news
     */
    private RecyclerView mRecyclerView;
    /*
     * RecyclerView.Adapter - is used for RecyclerView
     */
    public RecyclerView.Adapter mNewsListAdapter;
    /*
     * LayoutManager is used for RecyclerView
     */
    private RecyclerView.LayoutManager mLayoutManager;
    /*
     * SwipeRefreshLayout is used to pull down and refresh the news
     */
    private SwipeRefreshLayout swipeView;

    private ProgressBar progressBar;

    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Set the content view of the activity
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Initialize the pull down refresh list
        swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe);

        //Set the color of the progress bar of swipe view
        swipeView.setColorSchemeColors(Color.BLUE, Color.GRAY);

        // Add the listener for the swipe view
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //clear all the items
                clearAllData();

                // Call the service call to get the news feed
                callNewsDataServiceCall();

            }
        });


        // Initialize the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Add the line between the items in recycler view
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        //Initialize the news adapter
        mNewsListAdapter = new NewsRecyclerViewAdapter(this, new ArrayList<NewsRowData>());

        //Set the adapter for the recycler view
        mRecyclerView.setAdapter(mNewsListAdapter);

        //Initialize the progress bar
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        //Set the intermediate to true
        progressBar.setIndeterminate(true);

        //Set visible to the user for the first time
        progressBar.setVisibility(View.VISIBLE);

        // Call the service call to get the news feed
        callNewsDataServiceCall();

    }


    /**
     * callNewsDataServiceCall - used to call the service call to get the news feed using Volley library
     */
    private void callNewsDataServiceCall() {


        if (!Utils.isNetworkAvailable(this)) {

            //Dismiss the progress bar
            progressBar.setVisibility(View.GONE);

            //show the alert dialog
            Utils.showAlertDialog(this, getString(R.string.internet_error_message));


            return;
        }

        //Call the swipe refreshing to true
        swipeView.setRefreshing(true);

        NewsRequestService.callNewsRequest(this, new NewsDataNetworkCallback() {
            @Override
            public void onSuccess(NewsDataResponse responseData) {

                //Dismiss the progress bar
                dismissProgressbar();

                String newsTitle = responseData.getTitle();
                if(!TextUtils.isEmpty(newsTitle)) {
                    mToolbar.setTitle(newsTitle);
                }

                //Show the list
                showNewsDataList((ArrayList<NewsRowData>) responseData.getRows());
            }

            @Override

            public void onFailure(String errorMessage) {
                //Dismiss the progress bar
                dismissProgressbar();
            }
        });
    }


    /**
     * Show the data to the list/ We are using recycler list view
     *
     * @param rowData - data to list the view
     */
    private void showNewsDataList(ArrayList<NewsRowData> rowData) {
        mNewsListAdapter = new NewsRecyclerViewAdapter(this, rowData);
        mRecyclerView.swapAdapter(mNewsListAdapter, true);
    }


    /**
     * Dismiss the progress bar
     */
    private void dismissProgressbar() {
        //Set the pull down to false to dismiss the progress bar
        swipeView.setRefreshing(false);

        //Dismiss the progress bar
        if (progressBar != null && progressBar.isShown()) {
            progressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {

            //clear all the items
            clearAllData();

            // Call the service call to get the news feed
            callNewsDataServiceCall();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * clearAllData - used to clear all the data in the recycle view
     *
     */
    private void clearAllData() {
            mNewsListAdapter.notifyItemRangeRemoved(0, mRecyclerView.getAdapter().getItemCount());
    }


}
