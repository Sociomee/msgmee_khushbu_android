package com.inmortal.messenger.URL;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface ResponseListener {
    void onSuccess(JSONObject object, int Tag);

    void onError(VolleyError error, int Tag);
}
