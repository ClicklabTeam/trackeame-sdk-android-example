package com.clicklab.exampleapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.clicklab.sdk.ClickLab;
import com.clicklab.sdk.model.event.ConversionEvent;

import java.util.Currency;

public class ConversionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion_activity);
        setTitle("Conversion Event");

        final EditText transactionId = findViewById(R.id.transactionId);
        final EditText productName = findViewById(R.id.productName);
        final EditText price = findViewById(R.id.price);
        final EditText exchangeRate = findViewById(R.id.exchangeRate);
        final RadioGroup currency = findViewById(R.id.radioGroup);

        findViewById(R.id.track).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConversionEvent.Builder builder;
                if (TextUtils.isEmpty(transactionId.getText())) {
                    Utils.showLongToast(ConversionActivity.this, "Transaction Id should not be null");
                    return;
                } else {
                    builder = new ConversionEvent.Builder(transactionId.getText().toString());
                }

                if (!TextUtils.isEmpty(getProductNameString())) {
                    builder.setProductName(getProductNameString());
                }

                if (priceOrExchangeOrCurrencyAreSet(getPriceString(), getExchangeRateString(), currency)) {
                    if (!priceANDexchangeANDcurrencyAreSet(getPriceString(), getExchangeRateString(), currency)) {
                        Utils.showLongToast(ConversionActivity.this, "All Price Exchange rate and Currency should be set if one of them is defined");
                        return;
                    } else {
                        if (!isValidLong(getPriceString())) {
                            Utils.showLongToast(ConversionActivity.this, "Price should be a floating point number");
                            return;
                        }
                        if (!isValidDouble(getExchangeRateString())) {
                            Utils.showLongToast(ConversionActivity.this, "Exchange Rate should be a floating point number");
                            return;
                        }
                        builder.setPriceExchangeAndCurrency(Float.valueOf(getPriceString()), Float.valueOf(getExchangeRateString()), getCurrency(currency));
                    }
                }
                ClickLab.get().logEvent(builder.build());
                Toast.makeText(ConversionActivity.this, "Event Sent", Toast.LENGTH_SHORT).show();
            }

            @NonNull
            private String getProductNameString() {
                return productName.getText().toString();
            }

            @NonNull
            private String getExchangeRateString() {
                return exchangeRate.getText().toString();
            }

            @NonNull
            private String getPriceString() {
                return price.getText().toString();
            }
        });


    }

    private boolean isValidLong(String text) {
        try {
            Long.parseLong(text);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean isValidDouble(String text) {
        try {
            Double.parseDouble(text);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean priceANDexchangeANDcurrencyAreSet(String price, String exchangeRate, RadioGroup currency) {
        return !TextUtils.isEmpty(price) && !TextUtils.isEmpty(exchangeRate) && getCurrency(currency) != null;
    }

    private boolean priceOrExchangeOrCurrencyAreSet(String price, String exchangeRate, RadioGroup currency) {
        return !TextUtils.isEmpty(price) || !TextUtils.isEmpty(exchangeRate) || getCurrency(currency) != null;
    }

    public Currency getCurrency(RadioGroup radioGroup) {
        int currencyRadioId = radioGroup.getCheckedRadioButtonId();
        switch (currencyRadioId) {
            case R.id.euro:
                return Currency.getInstance("EUR");
            case R.id.yen:
                return Currency.getInstance("JPY");
            case R.id.dollar:
                return Currency.getInstance("USD");
            default:
                return null;
        }
    }
}
