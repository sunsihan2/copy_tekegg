package com.stripe.api.charges;

import org.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {GauntletTest.Service.STRIPE, GauntletTest.Endpoint.CHARGES})
public class ChargesResponseTests extends GauntletTest {
    @DataProvider
    public static Object[][] scenarios() {
        long fortyNineCents = 49L;
        long fiftyCentsPaymentServiceLowerLimit = 50L;
        long oneCentLowerThanOneMillionPaymentServiceUpperLimit = 99999999L;
        long oneMillionDollars = 100000000L;
        long negativeValue = -100L;
        return new Object[][]{
                {fortyNineCents}
                , {fiftyCentsPaymentServiceLowerLimit}
                , {oneCentLowerThanOneMillionPaymentServiceUpperLimit}
                , {oneMillionDollars}
                , {negativeValue}
        };
    }

    @Test(groups = {TestSuite.SMOKE, TestSuite.RELEASE})
    public void smoke() {
        addRequirements("feature/114-Purchase-Confirmation");
        long amount = 100L;
        ChargesResponseExpected expected = ChargesResponseExpected.getInstance(amount);
        ChargesResponse actual = ChargesRequest.getInstance(amount, "usd", "tok_amex").post();
        then(ChargesResponseCalibrator.getInstance(expected, actual));
    }

    @Test(dependsOnMethods = "smoke", dataProvider = "scenarios")
    public void submitCharge(long amount) {
        ChargesResponseExpected expected = ChargesResponseExpected.getInstance(amount);
        ChargesResponse actual = ChargesRequest.getInstance(amount, "usd", "tok_amex").post();
        then(ChargesResponseCalibrator.getInstance(expected, actual));
    }
}
