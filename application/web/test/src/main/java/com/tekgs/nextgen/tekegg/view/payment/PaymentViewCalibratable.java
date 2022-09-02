package com.tekgs.nextgen.tekegg.view.payment;

public interface PaymentViewCalibratable {
    String getTotalAmountDue();

    String getInvalidCurrencyMessage();

    String getInvalidSourceMessage();
}
