package com.tekgs.nextgen.tekegg.view.error;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;
import com.softwareonpurpose.uinavigator.web.WebUiHost;
import com.tekgs.nextgen.tekegg.view.payment.PaymentView;
import org.softwareonpurpose.gauntlet.Environment;

public class ErrorView extends UiView implements ErrorViewCalibratable{
    private static final String LOCATOR_VALUE = "404-error";
    private static final String LOCATOR_TYPE = UiLocatorType.ID;
    private static final String DESCRIPTION = "'404 Error' view";
    private static final String DOMAIN = Environment.getInstance().getDomainUrl(); ;
    private static final String VIEW_URI = DOMAIN;

    public ErrorView() {
        super(VIEW_URI, UiElement.getInstance(DESCRIPTION, LOCATOR_TYPE, LOCATOR_VALUE));
    }

    public static ErrorView directNav() {
        new ErrorView().load("/404-error");
        return UiView.expect(ErrorView.class);
    }

    public static ErrorView directNav(String invalidUrl) {
        new ErrorView().load(invalidUrl);
        return UiView.expect(ErrorView.class);
    }

    @Override
    public String get404ErrorMessage() {
        return getErrorMessageElement().getText();
    }

    private UiElement getErrorMessageElement() {
        String description = " '404 Error' message";
        String locatorValue = "404-error-message";
        return UiElement.getInstance(description, UiLocatorType.ID, locatorValue, this.getElement());
    }

    @Override
    protected boolean confirmElementStates() {
        return WebUiHost.getInstance().getAddress().contains(VIEW_URI);
    }
}
