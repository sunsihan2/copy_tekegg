package com.tekgs.nextgen.tekegg.view.purchase;

import com.tekgs.nextgen.tekegg.data.payment.TekEggPayment;
import com.tekgs.nextgen.tekegg.data.payment.TekEggPaymentDefinition;
import com.tekgs.nextgen.tekegg.data.payment.TekEggPaymentProvider;
import com.tekgs.nextgen.tekegg.view.payment.PaymentView;
import org.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {GauntletTest.Application.TEKEGG, GauntletTest.View.PURCHASE})
public class PurchaseViewTests extends GauntletTest {
    @DataProvider
    public static Object[][] scenarios() {
        int fortyNineCents = 49;
        int fiftyCentsPaymentServiceLowerLimit = 50;
        int oneMillionDollars = 100000000;
        int negativeValue = -100;
        int oneLessThanOneMillion = 99999999;
        String validCurrency = "usd";
        String validSource = "tok_visa";
        return new Object[][]{
                    {TekEggPaymentDefinition.getInstance().withAmount(fortyNineCents).withCurrency(validCurrency).withSource(validSource)},
                {TekEggPaymentDefinition.getInstance().withAmount(fiftyCentsPaymentServiceLowerLimit).withCurrency(validCurrency).withSource(validSource)},
                {TekEggPaymentDefinition.getInstance().withAmount(oneLessThanOneMillion).withCurrency(validCurrency).withSource(validSource)},
                {TekEggPaymentDefinition.getInstance().withAmount(oneMillionDollars).withCurrency(validCurrency).withSource(validSource)},
                {TekEggPaymentDefinition.getInstance().withAmount(negativeValue).withCurrency(validCurrency).withSource(validSource)}
        };
    }

    @Test(groups = {TestSuite.SMOKE})
    public void smoke() {
        PurchaseViewExpected expected = PurchaseViewExpected.getInstance();
        PurchaseView actual = PurchaseView.directNav();
        then(PurchaseViewCalibrator.getInstance(expected, actual));
    }

    @Test(groups = {TestSuite.RELEASE}, dataProvider = "scenarios")
    public void fromPayment(TekEggPaymentDefinition tekEggPaymentDefinition) {
        addRequirements("feature/115-Purchase-Confirmation-customer-message");
        TekEggPayment payment = TekEggPaymentProvider.getInstance().get(tekEggPaymentDefinition);
        PurchaseViewExpected expected = PurchaseViewExpected.getInstance(payment);
        PurchaseView actual = PaymentView.directNav(payment).submit(payment);
        then(PurchaseViewCalibrator.getInstance(expected, actual));
    }
}
