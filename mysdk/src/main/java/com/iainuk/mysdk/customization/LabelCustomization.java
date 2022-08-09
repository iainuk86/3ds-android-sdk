package com.iainuk.mysdk.customization;

import com.iainuk.mysdk.exception.InvalidInputException;

public final class LabelCustomization extends Customization {
    private String headingTextColor;
    private String headingTextFontName;
    private int headingTextFontSize;

    public LabelCustomization() {}

    public String getHeadingTextColor() { return headingTextColor; }

    public void setHeadingTextColor(String headingTextColor) throws InvalidInputException {
        verifyHexColorCode(headingTextColor, "label heading text color");
        this.headingTextColor = headingTextColor;
    }

    public String getHeadingTextFontName() { return headingTextFontName; }

    public void setHeadingTextFontName(String headingTextFontName) throws InvalidInputException {
        verifyInputStringHasLength(headingTextFontName, "heading text font name");
        this.headingTextFontName = headingTextFontName;
    }

    public int getHeadingTextFontSize() { return headingTextFontSize; }

    public void setHeadingTextFontSize(int headingTextFontSize) throws InvalidInputException {
        verifyNotNegative(headingTextFontSize, "heading text font size");
        this.headingTextFontSize = headingTextFontSize;
    }
}
