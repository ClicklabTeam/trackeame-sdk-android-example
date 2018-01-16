package com.clicklab.exampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.clicklab.sdk.ClickLab;
import com.clicklab.sdk.model.event.GenericEvent;
import com.clicklab.sdk.model.event.Parameters;

public class GenericEventActivity extends AppCompatActivity {

    public static final String SHOULD_NOT_BE_EMPTY = "should not be empty";
    public static final String SHOULD_NOT_BE_EMPTY_KEY = "should not be empty if value has data";
    public static final String SHOULD_NOT_BE_EMPTY_VALUE = "should not be empty if key has data";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_activity);
        setTitle("Generic Event");

        final EditText eventType = findViewById(R.id.eventType);
        final EditText event1key = findViewById(R.id.param1key);
        final EditText event1value = findViewById(R.id.param1value);

        final EditText event2key = findViewById(R.id.param2key);
        final EditText event2value = findViewById(R.id.param2value);

        findViewById(R.id.track).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean control = checkEventType(eventType)
                        && checkExtraParameter(event1key, event1value)
                        && checkExtraParameter(event2key, event2value);

                if (control) {
                    Parameters parameters = new Parameters();

                    addValues(parameters, event1key, event1value);
                    addValues(parameters, event2key, event2value);

                    ClickLab.get().logEvent(new GenericEvent(eventType.getText().toString(), parameters));
                    Utils.showShortToast(GenericEventActivity.this, "Event Sent");
                }
            }
        });
    }

    private void addValues(Parameters parameters, EditText event1key, EditText event1value) {
        if (!TextUtils.isEmpty(event1key.getText())) {
            parameters.set(event1key.getText().toString(), event1value.getText().toString());
        }
    }

    private boolean checkExtraParameter(EditText key, EditText value) {
        return checkKey(key, value) && checkValue(key, value);
    }

    private boolean checkEventType(EditText eventType) {
        if (TextUtils.isEmpty(eventType.getText().toString())) {
            eventType.setError(SHOULD_NOT_BE_EMPTY);
            return false;
        }
        return true;
    }

    private boolean checkKey(EditText key, EditText value) {
        key.setError(null);
        if (TextUtils.isEmpty(key.getText()) && !TextUtils.isEmpty(value.getText())) {
            key.setError(SHOULD_NOT_BE_EMPTY_KEY);
            return false;
        }
        return true;
    }

    private boolean checkValue(EditText key, EditText value) {
        value.setError(null);
        if (!TextUtils.isEmpty(key.getText()) && TextUtils.isEmpty(value.getText())) {
            value.setError(SHOULD_NOT_BE_EMPTY_VALUE);
            return false;
        }
        return true;
    }
}
