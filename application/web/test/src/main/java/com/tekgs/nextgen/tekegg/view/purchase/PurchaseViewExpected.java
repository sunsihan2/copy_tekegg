package com.tekgs.nextgen.tekegg.view.purchase;

import com.tekgs.nextgen.tekegg.data.payment.TekEggPayment;

@SuppressWarnings("ClassCanBeRecord")
public class PurchaseViewExpected implements PurchaseViewCalibratable {
    private final TekEggPayment payment;

    public PurchaseViewExpected(TekEggPayment payment) {
        this.payment = payment;
    }

    public static PurchaseViewExpected getInstance() {
        return new PurchaseViewExpected(null);
    }

    public static PurchaseViewExpected getInstance(TekEggPayment payment) {
        return new PurchaseViewExpected(payment);
    }

    @Override
    public String getPayment() {
        return payment != null && (payment.getAmount() > 49 && payment.getAmount() < 100000000)
                ? "Purchase was successful"
                : "Purchase failed";
    }
}
