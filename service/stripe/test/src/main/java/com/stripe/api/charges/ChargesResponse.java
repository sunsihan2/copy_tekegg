package com.stripe.api.charges;

import com.google.gson.Gson;

@SuppressWarnings("unused")
public class ChargesResponse implements ChargesResponseCalibratable {
    private static final String SUCCEEDED = "succeeded";
    private String status;
    private boolean paid;

    private ChargesResponse() {
    }

    public static ChargesResponse getInstance(String response) {
        return new Gson().fromJson(response, ChargesResponse.class);
    }

    @Override
    public Boolean isPaymentSuccessful() {
        return paid && SUCCEEDED.equals(status);
    }
}
