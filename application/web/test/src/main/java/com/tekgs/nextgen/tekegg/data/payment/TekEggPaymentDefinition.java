package com.tekgs.nextgen.tekegg.data.payment;

import com.google.gson.Gson;

public class TekEggPaymentDefinition implements TekEggPaymentCalibratable {
    private Integer amount;
    private String currency;
    private String source;

    public static TekEggPaymentDefinition getInstance() {
        return new TekEggPaymentDefinition();
    }

    public TekEggPaymentDefinition withAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public TekEggPaymentDefinition withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public TekEggPaymentDefinition withSource(String source) {
        this.source = source;
        return this;
    }

    public TekEggPayment toPayment() {
        return TekEggPayment.getInstance(this);
    }

    @Override
    public Integer getAmount() {
        return amount;
    }


    @Override
    public String getSource() {
        return source;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return String.format("TekEggPaymentDefinition %s ", new Gson().toJson(this));
    }
}
