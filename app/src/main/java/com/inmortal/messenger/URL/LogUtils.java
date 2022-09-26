package com.inmortal.messenger.URL;

import android.util.Log;

public class LogUtils {
    private static LogUtils logUtils;

    public LogUtils(String msg, String title) {
        Log.e(title, msg);
    }

    public static LogUtils showLog(String title, String msg) {
        if (logUtils == null) {
            new LogUtils(msg, title);
            logUtils = null;
        }
        return logUtils;
    }
}
