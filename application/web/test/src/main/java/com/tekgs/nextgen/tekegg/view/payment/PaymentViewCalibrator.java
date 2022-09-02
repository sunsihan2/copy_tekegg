package com.tekgs.nextgen.tekegg.view.payment;

import com.softwareonpurpose.calibrator4test.Calibrator;

public class PaymentViewCalibrator extends Calibrator {
    private static final String DESCRIPTION = "'Payment' view";
    private final PaymentViewExpected expected;
    private final PaymentView actual;

    private PaymentViewCalibrator(PaymentViewExpected expected, PaymentView actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
    }

    public static PaymentViewCalibrator getInstance(PaymentViewExpected expected, PaymentView actual) {
        return new PaymentViewCalibrator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        String invalidCurrencyDescription = "'Invalid Currency' message";
        String invalidSourceDescription = "'Invalid Source' message";
        String totalAmountDueDescription = "Total Amount Due";
        verify(totalAmountDueDescription, expected.getTotalAmountDue(), actual.getTotalAmountDue());
        verify(invalidCurrencyDescription, expected.getInvalidCurrencyMessage(), actual.getInvalidCurrencyMessage());
        verify(invalidSourceDescription, expected.getInvalidSourceMessage(), actual.getInvalidSourceMessage());
    }
}
