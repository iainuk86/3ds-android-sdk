package com.iainuk.mysdk.customization;

import com.iainuk.mysdk.exception.InvalidInputException;

public final class TextBoxCustomization extends Customization {
    private String borderColor;
    private int borderWidth;
    private int cornerRadius;

    public TextBoxCustomization() {}

    public String getBorderColor() { return borderColor; }

    public void setBorderColor(String borderColor) throws InvalidInputException {
        verifyHexColorCode(borderColor, "TextBox border color");
        this.borderColor = borderColor;
    }

    public int getBorderWidth() { return borderWidth; }

    public void setBorderWidth(int borderWidth) throws InvalidInputException {
        verifyNotNegative(borderWidth, "TextBox border width");
        this.borderWidth = borderWidth;
    }

    public int getCornerRadius() { return cornerRadius; }

    public void setCornerRadius(int cornerRadius) throws InvalidInputException {
        verifyNotNegative(cornerRadius, "TextBox corner radius");
        this.cornerRadius = cornerRadius;
    }
}
