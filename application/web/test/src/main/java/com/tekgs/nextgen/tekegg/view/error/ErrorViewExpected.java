package com.tekgs.nextgen.tekegg.view.error;

public class ErrorViewExpected implements ErrorViewCalibratable {
    private final String errorMessage = "404 error";

    public static ErrorViewExpected getInstance() {
        return new ErrorViewExpected();
    }


    @Override
    public String get404ErrorMessage() {
        return errorMessage;
    }
}
