package com.telstra.telstranews.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.telstra.telstranews.R;
import com.telstra.telstranews.model.NewsRowData;

import java.util.ArrayList;


/**
 *
 * @author kumarappan
 *
 *
 * NewsRecyclerViewAdapter is the adapter class for Recycler view
 *
 *
 *
 */
public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {
    private ArrayList<NewsRowData> mNewInfoData;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {

       /*
        *  each data item available in the list
        */
        public TextView txtNewsHeader;
        public TextView txtNewsDescription;
        public ImageView imgNewsIcon;

        public ViewHolder(View v) {
            super(v);
            txtNewsHeader = (TextView) v.findViewById(R.id.newsTitle);
            txtNewsDescription = (TextView) v.findViewById(R.id.newsDescription);
            imgNewsIcon = (ImageView) v.findViewById(R.id.newsIcon);
        }
    }

    // Provide a suitable constructor (depends on the kind of data set)
    public NewsRecyclerViewAdapter(Context context, ArrayList<NewsRowData> dataset) {
        mNewInfoData = dataset;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NewsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row_layout, parent, false);
        // set the view's size, margins, padding and layout parameters
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        NewsRowData newsData = mNewInfoData.get(position);

        final String newsDataTitle = newsData.getTitle();
        final String newsDataDescription = newsData.getDescription();
        final String newsImageURL = newsData.getImageHref();

        holder.txtNewsHeader.setText(newsDataTitle);
        holder.txtNewsDescription.setText(newsDataDescription);

        if(!TextUtils.isEmpty(newsImageURL)) {
            Picasso.with(mContext).load(newsImageURL).error(R.drawable.default_icon).placeholder(R.drawable.default_icon).into(holder.imgNewsIcon);
        } else {
            holder.imgNewsIcon.setImageResource(R.drawable.default_icon);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mNewInfoData.size();
    }


}