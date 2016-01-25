package com.telstra.telstranews.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.telstra.telstranews.R;

/**
 *
 * @author Kumarappan Subramanian
 *
 * Created on 1/21/16.
 *
 *
 *  Utils is utility methods, used in the application
 *
 *
 */
public class Utils {


    /**
     *
     * isNetworkAvailable is used to check internet connection is available or not
     *
     * @param context - context of the application
     * @return - true if the network is available
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    /**
     * showAlertDialog is used to show the alert dialog
     *
     *
     * @param context - context of the application
     * @param message - message to show the dialog
     */
    public static void showAlertDialog(Context context, String message) {
        //Initialize the alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //Set the values
        builder.setTitle(context.getResources().getString(R.string.alert_title));
        builder.setMessage(message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //dismiss the dialog
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
    }

}
