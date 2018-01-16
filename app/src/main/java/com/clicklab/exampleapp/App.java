package com.clicklab.exampleapp;

import android.app.Application;

import com.clicklab.sdk.ClickLab;
import com.clicklab.sdk.Configuration;
import com.clicklab.sdk.repository.SdkRepository;
import com.clicklab.sdk.repository.SdkRepositoryFactory;


public class App extends Application {

    private static final String API_KEY = "REPLACE_WITH_API_KEY";

    @Override
    public void onCreate() {
        super.onCreate();

        String apiKey = Utils.getLicenseKey(getApplicationContext());
        if (!apiKey.isEmpty()) {
            Configuration configuration = new Configuration(apiKey, "Appname in configuration");
            ClickLab.init(this, configuration);
        }
    }
}
