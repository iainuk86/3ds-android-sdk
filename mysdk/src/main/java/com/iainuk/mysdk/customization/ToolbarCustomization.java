package com.iainuk.mysdk.customization;

import com.iainuk.mysdk.exception.InvalidInputException;

public final class ToolbarCustomization extends Customization {
    private String backgroundColor;
    private String headerText;
    private String buttonText;

    public ToolbarCustomization() {}

    public String getBackgroundColor() { return backgroundColor; }

    public void setBackgroundColor(String hexColorCode) throws InvalidInputException {
        verifyHexColorCode(hexColorCode, "toolbar background color");
        this.backgroundColor = hexColorCode;
    }

    public String getHeaderText() { return headerText; }

    public void setHeaderText(String headerText) throws InvalidInputException {
        verifyInputStringHasLength(headerText, "header text");
        this.headerText = headerText;
    }

    public String getButtonText() { return buttonText; }

    public void setButtonText(String buttonText) throws InvalidInputException {
        verifyInputStringHasLength(buttonText, "button text");
        this.buttonText = buttonText;
    }
}
