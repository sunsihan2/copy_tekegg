package com.stripe.api.charges;

public class ChargesResponseExpected implements ChargesResponseCalibratable {
    private final Boolean isPaymentSuccessful;

    public ChargesResponseExpected(long amount) {
        isPaymentSuccessful = amount >= 50L && amount < 100000000L;

    }

    public static ChargesResponseExpected getInstance(long amount) {
        return new ChargesResponseExpected(amount);
    }

    @Override
    public Boolean isPaymentSuccessful() {
        return isPaymentSuccessful;
    }
}
