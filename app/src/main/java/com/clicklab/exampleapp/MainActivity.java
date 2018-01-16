package com.clicklab.exampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.clicklab.sdk.ClickLab;
import com.clicklab.sdk.model.event.ConversionEvent;
import com.clicklab.sdk.model.event.GenericEvent;
import com.clicklab.sdk.model.event.OpenAppEvent;
import com.clicklab.sdk.model.event.Parameters;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Parameters parameters = new Parameters();
        parameters.set("parameter1", "value1");
        parameters.set("parameter2", "value2");

        findViewById(R.id.changeConfiguration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ConfigurationActivity.class));
            }
        });

        findViewById(R.id.genericEvent).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ClickLab.get().logEvent(new GenericEvent("Generic " + UUID.randomUUID(), parameters));
                Utils.showShortToast(MainActivity.this, "Event Sent");
            }
        });

        findViewById(R.id.openApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickLab.get().logEvent(new OpenAppEvent("urlUUID" + UUID.randomUUID(), "empty referrer"));
                Utils.showShortToast(MainActivity.this, "Event Sent");
            }
        });

        findViewById(R.id.trackConversion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickLab.get().logEvent(new ConversionEvent(UUID.randomUUID().toString(), "product", 1, 1, Currency.getInstance(Locale.getDefault())));
                Utils.showShortToast(MainActivity.this, "Event Sent");
            }
        });

        findViewById(R.id.customGenericEvent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GenericEventActivity.class));
            }
        });


        findViewById(R.id.customOpenApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OpenAppActivity.class));
            }
        });

        findViewById(R.id.customConversion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ConversionActivity.class));
            }
        });
    }
}
