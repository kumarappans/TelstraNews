
package com.telstra.telstranews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Kumarappan Subramanian
 *
 * Created on 1/21/16.
 *
 *
 *  NewsDataResponse is model class for the GSON ogbect
 *
 *
 */
public class NewsDataResponse {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rows")
    @Expose
    private List<NewsRowData> rows = new ArrayList<NewsRowData>();

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The rows
     */
    public List<NewsRowData> getRows() {
        return rows;
    }

    /**
     * 
     * @param rows
     *     The rows
     */
    public void setRows(List<NewsRowData> rows) {
        this.rows = rows;
    }

}
