package com.clicklab.exampleapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class Utils {

    private static final String DEMO_PREFS = "DEMO_PREFS";
    private static final String DEMO_API_KEY = "DEMO_API_KEY";

    public static void showLongToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static String getLicenseKey(Context context){
        return getSharedPreferences(context)
                .getString(DEMO_API_KEY, "");
    }

    public static void setLicenseKey(Context context, String licenseKey){
        getSharedPreferences(context).edit()
                .putString(DEMO_API_KEY,licenseKey).apply();
    }

    private static SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(DEMO_PREFS, Context.MODE_PRIVATE);
    }
}
