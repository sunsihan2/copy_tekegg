package com.tekgs.nextgen.tekegg.view.payment;

import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.cart.CartDefinition;
import com.tekgs.nextgen.tekegg.data.cart.CartProvider;
import com.tekgs.nextgen.tekegg.data.payment.TekEggPayment;
import com.tekgs.nextgen.tekegg.data.payment.TekEggPaymentDefinition;
import com.tekgs.nextgen.tekegg.data.payment.TekEggPaymentProvider;
import org.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {GauntletTest.Application.TEKEGG, GauntletTest.View.PAYMENT})
public class PaymentViewTests extends GauntletTest {
    @DataProvider
    public static Object[][] directNavScenarios() {
        int fortyNineCents = 49;
        int fiftyCentsPaymentServiceLowerLimit = 50;
        int oneMillionDollars = 100000000;
        int negativeValue = -100;
        int oneLessThanOneMillion = 99999999;
        return new Object[][]{
                {CartDefinition.getInstance().withTotal(fortyNineCents)}
                , {CartDefinition.getInstance().withTotal(fiftyCentsPaymentServiceLowerLimit)}
                , {CartDefinition.getInstance().withTotal(oneLessThanOneMillion)}
                , {CartDefinition.getInstance().withTotal(oneMillionDollars)}
                , {CartDefinition.getInstance().withTotal(negativeValue)}
        };
    }

    @DataProvider
    public static Object[][] submitInvalidScenarios() {
        String invalid = "bogus";
        String validSource = "tok_visa";
        String validCurrency = "usd";
        return new Object[][]{
                {TekEggPaymentDefinition.getInstance().withCurrency(invalid).withSource(invalid)},
                {TekEggPaymentDefinition.getInstance().withCurrency(invalid).withSource(validSource)},
                {TekEggPaymentDefinition.getInstance().withCurrency(validCurrency).withSource(invalid)}
        };
    }

    @Test(groups = {TestSuite.SMOKE})
    public void smoke() {
        PaymentViewExpected expected = PaymentViewExpected.getInstance();
        PaymentView actual = PaymentView.directNav();
        then(PaymentViewCalibrator.getInstance(expected, actual));
    }

    @Test(groups = {TestSuite.RELEASE}, dependsOnMethods = "smoke", dataProvider = "directNavScenarios")
    public void directNav(CartDefinition cartDefinition) {
        addRequirements("feature/118-Payment-Submission-validation");
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        PaymentViewExpected expected = PaymentViewExpected.getInstance(cart);
        PaymentView actual = PaymentView.directNav(cart);
        then(PaymentViewCalibrator.getInstance(expected, actual));
    }

    @Test(groups = {TestSuite.ACCEPTANCE}, dependsOnMethods = "smoke", dataProvider = "submitInvalidScenarios")
    public void submitValidationError(TekEggPaymentDefinition paymentDefinition) {
        TekEggPayment payment = TekEggPaymentProvider.getInstance().get(paymentDefinition);
        PaymentViewExpected expected = PaymentViewExpected.getInstance(payment);
        PaymentView actual = PaymentView.directNav().submitInvalid(payment);
        then(PaymentViewCalibrator.getInstance(expected, actual));
    }
}
