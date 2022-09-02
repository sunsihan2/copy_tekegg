package com.tekgs.nextgen.tekegg.view.payment;

import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.payment.TekEggPayment;
import com.tekgs.nextgen.tekegg.data.value.Cents;

public class PaymentViewExpected implements PaymentViewCalibratable {
    private final TekEggPayment payment;
    private final Cents cents;

    public PaymentViewExpected(Cart cart, TekEggPayment payment) {
        int amount = cart == null ? 0 : cart.getTotal();
        cents = Cents.getInstance(amount);
        this.payment = payment;
    }

    public static PaymentViewExpected getInstance(TekEggPayment payment) {
        return new PaymentViewExpected(null, payment);
    }

    public static PaymentViewExpected getInstance(Cart cart) {
        return new PaymentViewExpected(cart, null);
    }

    public static PaymentViewExpected getInstance(Cart cart, TekEggPayment payment) {
        return new PaymentViewExpected(cart, payment);
    }

    public static PaymentViewExpected getInstance() {
        return new PaymentViewExpected(null, null);
    }

    @Override
    public String getTotalAmountDue() {
        return cents.inDollarFormat();
    }

    @Override
    public String getInvalidCurrencyMessage() {
        return payment == null ? "" : "usd".equals(payment.getCurrency()) ? "" : "Currency invalid";
    }

    @Override
    public String getInvalidSourceMessage() {
        return payment == null ? "" : payment.isValidSource() ? "" : "Source invalid";
    }
}
