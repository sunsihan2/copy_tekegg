package com.tekgs.nextgen.tekegg.data.payment;

public class TekEggPaymentRepository {
    public static TekEggPaymentRepository getInstance() {
        return new TekEggPaymentRepository();
    }

    public TekEggPayment query(TekEggPaymentDefinition definition) {
        return definition.toPayment();
    }
}
