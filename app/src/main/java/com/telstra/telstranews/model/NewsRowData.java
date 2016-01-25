
package com.telstra.telstranews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


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
public class NewsRowData {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageHref")
    @Expose
    private String imageHref;

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
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The imageHref
     */
    public String getImageHref() {
        return imageHref;
    }

    /**
     * 
     * @param imageHref
     *     The imageHref
     */
    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

}
