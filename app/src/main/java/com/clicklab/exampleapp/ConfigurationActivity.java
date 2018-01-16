package com.clicklab.exampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.clicklab.sdk.ClickLab;

import java.util.Locale;

public class ConfigurationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_activity);
        setTitle("Configuration");

        Button changeMail = findViewById(R.id.changeMail);
        final EditText editText = findViewById(R.id.editText);
        changeMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editText.getText() != null ? editText.getText().toString() : "";
                ClickLab.get().setEmail(email);
                Utils.showShortToast(ConfigurationActivity.this, "Email set to " + email);
            }
        });

        findViewById(R.id.buttonItaly).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCountry(Locale.ITALY);
            }
        });

        findViewById(R.id.buttonJapan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCountry(Locale.JAPAN);
            }
        });

        findViewById(R.id.buttonUSA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCountry(Locale.US);
            }
        });

        findViewById(R.id.buttonClearApiKey).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear current ApiKey
                Utils.setLicenseKey(getApplicationContext(), "");
                startActivity(new Intent(
                        ConfigurationActivity.this, RestartActivity.class));
                finish();
            }
        });
    }

    private void setCountry(Locale locale) {
        ClickLab.get().setCountry(locale);
        Utils.showShortToast(ConfigurationActivity.this, "country set");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
