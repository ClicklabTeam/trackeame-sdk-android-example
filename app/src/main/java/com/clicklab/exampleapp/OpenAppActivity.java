package com.clicklab.exampleapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.clicklab.sdk.ClickLab;
import com.clicklab.sdk.model.event.OpenAppEvent;

public class OpenAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.openapp_activity);
        setTitle("Open App Event");

        findViewById(R.id.track).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText url = findViewById(R.id.url);
                EditText referrer = findViewById(R.id.referrer);

                ClickLab.get().logEvent(new OpenAppEvent(url.getText().toString(), referrer.getText().toString()));
                Utils.showShortToast(OpenAppActivity.this, "Event Sent");
            }
        });
    }
}
