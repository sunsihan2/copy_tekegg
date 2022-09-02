package com.tekgs.nextgen.tekegg.data.payment;

public class TekEggPaymentProvider {
    public static TekEggPaymentProvider getInstance() {
        return new TekEggPaymentProvider();
    }

    public TekEggPayment get() {
        return TekEggPayment.getInstance();
    }

    public TekEggPayment get(TekEggPaymentDefinition tekEggPaymentDefinition) {
        return TekEggPaymentRepository.getInstance().query(tekEggPaymentDefinition);
    }
}
