package com.tekgs.nextgen.tekegg.data.payment;

import java.util.Arrays;

public class TekEggPayment implements TekEggPaymentCalibratable {
    private final Integer amount;
    private final String currency;
    private final String source;
    private final String[] validSources = new String[]{"tok_amex", "tok_visa", "tok_visa_debit", "tok_mastercard", "tok_mastercard_debit", "tok_mastercard_prepaid", "tok_discover", "tok_diners", "tok_jcb", "tok_unionpay"};

    public TekEggPayment(TekEggPaymentDefinition tekEggPaymentDefinition) {
        this.amount = tekEggPaymentDefinition.getAmount();
        this.currency = tekEggPaymentDefinition.getCurrency();
        this.source = tekEggPaymentDefinition.getSource();
    }

    public static TekEggPayment getInstance() {
        return new TekEggPayment(TekEggPaymentDefinition.getInstance());
    }

    public static TekEggPayment getInstance(TekEggPaymentDefinition tekEggPaymentDefinition) {
        return new TekEggPayment(tekEggPaymentDefinition);
    }

    public Boolean isValidSource() {
        return Arrays.asList(validSources).contains(this.source);
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
        return this.currency;
    }
}
