package com.tekgs.nextgen.tekegg.view.purchase;

import com.softwareonpurpose.calibrator4test.Calibrator;

public class PurchaseViewCalibrator extends Calibrator {
  private static final String DESCRIPTION = "'Purchase' view";
  private final PurchaseViewExpected expected;
  private final PurchaseView actual;

  private PurchaseViewCalibrator(PurchaseViewExpected expected, PurchaseView actual) {
    super(DESCRIPTION, expected, actual);
    this.expected = expected;
    this.actual = actual;
  }

  public static PurchaseViewCalibrator getInstance(PurchaseViewExpected expected, PurchaseView actual) {
    return new PurchaseViewCalibrator(expected, actual);
  }


  @Override
  protected void executeVerifications() {
    verify("Purchase Confirmation", expected.getPayment(), actual.getPayment());
  }
}
