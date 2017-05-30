package com.smartsoftware.eh.motorcontroller;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jh on 17. 5. 29.
 */

public class ServerAccessObject {
    public static final int CONNECT_TIMEOUT = 3000;
    public static final int READ_TIMEOUT = 15000;

    public static String ipAddr;
    public static long portNum;

    private Activity calledActivity;

    public ServerAccessObject(Activity calledActivity) {
        this.calledActivity = calledActivity;
    }

    // Network(Wifi and Data) 켜져 있는지 확인
    private boolean checkAccessibleInternet() {
        if( ((ConnectivityManager)calledActivity.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() == null ) {
            calledActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AlertDialog.Builder ab = new AlertDialog.Builder(calledActivity);
                    ab.setTitle("네트워크 에러");
                    final DialogInterface dialog = ab.show();
                }
            });
            return false;
        }
        return true;
    }


    // Server Connect
    private HttpURLConnection connectServer(String strUrl) throws IOException {
        HttpURLConnection conn;
        URL url= new URL(strUrl);
        conn = (HttpURLConnection) url.openConnection();

        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setUseCaches(false);
        conn.setConnectTimeout(CONNECT_TIMEOUT);
        conn.setReadTimeout(READ_TIMEOUT);
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Content-Type", "application/json"); // form 방식일땐 application/x-www-form-urlencoded

        return conn;
    }

}
