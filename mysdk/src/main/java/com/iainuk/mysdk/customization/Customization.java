package com.iainuk.mysdk.customization;

import com.iainuk.mysdk.exception.InvalidInputException;

public class Customization {
    public String textFontName;
    public String textColor;
    public int textFontSize;

    public Customization() {}

    public String getTextFontName() { return this.textFontName; }

    public void setTextFontName(String fontName) throws InvalidInputException {
        verifyInputStringHasLength(fontName, "text font name");
        this.textFontName = fontName;
    }

    public String getTextColor() { return this.textColor; }

    public void setTextColor(String hexColorCode) throws InvalidInputException {
        verifyHexColorCode(hexColorCode, "text color");
        this.textColor = hexColorCode;
    }

    public int getTextFontSize() { return this.textFontSize; }

    public void setTextFontSize(int fontSize) throws InvalidInputException {
        verifyNotNegative(fontSize, "text font size");
        this.textFontSize = fontSize;
    }

    public void verifyInputStringHasLength(String string, String inputField) {
        if (string == null || string.isEmpty()) {
            throw new InvalidInputException("Invalid input for " + inputField);
        }
    }

    public void verifyHexColorCode(String hexColorCode, String inputField) {
        if (hexColorCode == null || hexColorCode.isEmpty() || hexColorCode.charAt(0) != '#') {
            throw new InvalidInputException("Invalid Hex colour code provided for " + inputField);
        }
    }

    public void verifyNotNegative(int value, String inputField) {
        if (value < 0) {
            throw new InvalidInputException("Negative value provided for " + inputField);
        }
    }
}
