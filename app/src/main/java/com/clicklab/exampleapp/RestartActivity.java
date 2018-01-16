package com.clicklab.exampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Process;

/**
 * Created by Facundo A. Paredes on 26/12/2017.
 */

public class RestartActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restart);
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }
}
