package com.clicklab.exampleapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.clicklab.sdk.ClickLab;
import com.clicklab.sdk.Configuration;

/**
 * Created by Facundo A. Paredes on 26/12/2017.
 */

public class SplashActivity extends Activity {

    private EditText licenseKeyET;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!Utils.getLicenseKey(getApplicationContext()).isEmpty()){
            // If there's an ApiKey saved, start the DemoApp
            startDemoApp();
            return;
        }

        setContentView(R.layout.activity_splash);

        licenseKeyET = findViewById(R.id.license_key_et);
        findViewById(R.id.set_license_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String apiKey = licenseKeyET.getText().toString();
                // If the ET is empty, show a Toast and return
                if (apiKey.isEmpty()){
                    Utils.showShortToast(getApplicationContext(),"ApiKey field is empty!");
                    return;
                }
                // Set the given ApiKey
                Utils.setLicenseKey(getApplicationContext(), apiKey);
                Configuration configuration =
                        new Configuration(apiKey, "Appname in configuration");
                ClickLab.init(getApplication(), configuration);
                startDemoApp();
            }
        });
    }

    private void startDemoApp(){
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
