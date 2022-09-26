package com.inmortal.messenger.URL;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class ProjectVolleyRequest {
    private Context mContext;
    private JSONObject object;
    private String url;
    private ResponseListener listener;
    private final CustomProgressDialog customDialog;
    private int Tag;

    public ProjectVolleyRequest(Context mContext, JSONObject object, String url, ResponseListener listener, int Tag) {
        this.mContext = mContext;
        this.object = object;
        this.url = url;
        this.listener = listener;
        this.Tag = Tag;
        customDialog = CustomProgressDialog.getInstance(mContext);
    }

    synchronized public void execute() {
        if (NetworkUtils.isConnected(mContext)) {
            customDialog.showProgressBar();
            LogUtils.showLog("@@@@@@URL::", url);
            LogUtils.showLog("@@@@@@URL::", object.toString());
            Log.e("@@@@@@URL::", url);
            Log.e("@@@@@@URL::", object.toString());

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    url, object,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            listener.onSuccess(response, Tag);
                            customDialog.hideProgressBar();
                            Log.e("@@@@@@@@", response.toString());
                            LogUtils.showLog("@@@@@@@@", response.toString());

                        }
                    }, new Response.ErrorListener() {


                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    customDialog.hideProgressBar();
                    volleyError.printStackTrace();
                    listener.onError(volleyError, Tag);
                    if (volleyError instanceof NetworkError) {
                        Toast.makeText(mContext, "Network volleyError, please try again later", Toast.LENGTH_LONG).show();
                    } else if (volleyError instanceof ServerError) {
                        Toast.makeText(mContext, "Server volleyError, please try again later", Toast.LENGTH_LONG).show();
                    } else if (volleyError instanceof AuthFailureError) {
                        Toast.makeText(mContext, "AuthFailure volleyError, please try again later", Toast.LENGTH_LONG).show();
                    } else if (volleyError instanceof ParseError) {
                        Toast.makeText(mContext, "Parse volleyError, please try again later", Toast.LENGTH_LONG).show();
                    } else if (volleyError instanceof NoConnectionError) {
                        Toast.makeText(mContext, "No connection, please check your network", Toast.LENGTH_LONG).show();
                    } else if (volleyError instanceof TimeoutError) {
                        Toast.makeText(mContext, "Timeout volleyError, please try again later", Toast.LENGTH_LONG).show();
                    }
                    // AppAlertDialog.showDialogSelfFinish(mContext,"title","sms");
                }
            }
            );
            MyApplication.getInstance().addToRequestQueue(jsonObjReq, "" + Tag);
        } else {
            if (mContext != null)
                Toast.makeText(mContext, "No internet connection found", Toast.LENGTH_LONG).show();
        }
    }
}
