package com.iainuk.mysdk.customization;

import com.iainuk.mysdk.exception.InvalidInputException;

public final class ButtonCustomization extends Customization {
    private String backgroundColor;
    private int cornerRadius;

    public ButtonCustomization() {}

    public String getBackgroundColor() { return backgroundColor; }

    public void setBackgroundColor(String backgroundColor) throws InvalidInputException {
        verifyHexColorCode(backgroundColor, "button background color");
        this.backgroundColor = backgroundColor;
    }

    public int getCornerRadius() { return cornerRadius; }

    public void setCornerRadius(int cornerRadius) throws InvalidInputException {
        verifyNotNegative(cornerRadius, "button corner radius");
        this.cornerRadius = cornerRadius;
    }
}
