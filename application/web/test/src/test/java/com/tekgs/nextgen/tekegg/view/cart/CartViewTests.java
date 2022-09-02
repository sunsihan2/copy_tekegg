package com.tekgs.nextgen.tekegg.view.cart;

import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.cart.CartDefinition;
import com.tekgs.nextgen.tekegg.data.cart.CartProvider;
import com.tekgs.nextgen.tekegg.data.cart.product.ProductDefinition;
import org.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {GauntletTest.Application.TEKEGG, GauntletTest.View.CART})
public class CartViewTests extends GauntletTest {

    @DataProvider
    public static Object[][] scenarios() {
        int fortyNineCents = 49;
        int fiftyCentsPaymentServiceLowerLimit = 50;
        int oneMillionDollars = 100000000;
        int negativeValue = -100;
        int oneLessThanOneMillion = 99999999;
        ProductDefinition normalProduct = ProductDefinition.getInstance().withDescription("product 1 description");
        ProductDefinition productNoDescription = ProductDefinition.getInstance().withDescription(null);
        ProductDefinition productWithTag = ProductDefinition.getInstance().withDescription("<h2>hack attempt<h2>");
        ProductDefinition productWithBackspace = ProductDefinition.getInstance().withDescription("(U+2408)");
        String wordOfLength256 = "kjhbafsbkjknbfjasbkjsfabkjasfbkjfasbkjhbkjhsafbkjhsfabkjhbkjsfakbjfsabkjsfabkjbkjfsabkjfsajbjk.fbkjsabjk.fkjasbfkjbasjbf.jkasjkfbjkas.jkbf.bjkasb.jkf.jkabsfbkj.asbfjkbasjkbfjksabjkbfjkasbfj.kbasj.kbfjkabsfjkbaskjkjf.ajksbafjkbbsajsbfkabasfkj.bsajkbafs.kbkj";
        ProductDefinition productDescriptionOneWord256 = ProductDefinition.getInstance().withDescription(wordOfLength256);
        ProductDefinition productWithSQL = ProductDefinition.getInstance().withDescription("SELECT * FROM Users WHERE UserId = 2");

        return new Object[][]{
                {CartDefinition.getInstance().withTotal(fortyNineCents).withProduct(normalProduct).withID(2L)}
                , {CartDefinition.getInstance().withTotal(fiftyCentsPaymentServiceLowerLimit).withProduct(normalProduct).withID(3L)}
                , {CartDefinition.getInstance().withTotal(oneLessThanOneMillion).withProduct(normalProduct).withID(4L)}
                , {CartDefinition.getInstance().withTotal(oneMillionDollars).withProduct(normalProduct).withID(5L)}
                , {CartDefinition.getInstance().withTotal(negativeValue).withProduct(normalProduct).withID(6L)}
                , {CartDefinition.getInstance().withTotal(fiftyCentsPaymentServiceLowerLimit)
                .withID(1L)
                .withProduct(normalProduct)
                .withProduct(productNoDescription)
                .withProduct(productWithTag)
                .withProduct(productWithBackspace)
                .withProduct(productDescriptionOneWord256)
                .withProduct(productWithSQL)}
        };
    }

    @Test(groups = {TestSuite.SMOKE, TestSuite.RELEASE})
    public void smoke() {
        CartViewExpected expected = CartViewExpected.getInstance();
        CartView actual = CartView.directNav();
        then(CartViewCalibrator.getInstance(expected, actual));
    }

    @Test(groups = {TestSuite.RELEASE}, dependsOnMethods = "smoke", dataProvider = "scenarios")
    public void directNav(CartDefinition cartDefinition) {
        addRequirements("feature/120-Cart-item-description", "feature/119-total-amount-(valid)");
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        CartViewExpected expected = CartViewExpected.getInstance(cart);
        CartView actual = CartView.directNav(cart);
        then(CartViewCalibrator.getInstance(expected, actual));
    }
}
