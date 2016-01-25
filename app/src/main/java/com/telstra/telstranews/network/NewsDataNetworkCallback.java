package com.telstra.telstranews.network;

import com.telstra.telstranews.model.NewsDataResponse;


/**
 *
 * @author Kumarappan Subramanian
 *
 * Created on 1/21/16.
 *
 *
 *  NewsDataNetworkCallback is interface callback for the service call
 *
 *
 */
public interface NewsDataNetworkCallback {


    public void onSuccess(NewsDataResponse responseData);

    public void onFailure(String errorMessage);


}
