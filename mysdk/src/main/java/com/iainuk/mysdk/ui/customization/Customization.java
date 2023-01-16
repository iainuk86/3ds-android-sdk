package com.iainuk.mysdk.ui.customization;

import static com.iainuk.mysdk.Utils.requireHasLength;
import static com.iainuk.mysdk.Utils.requireValidHexCode;
import static com.iainuk.mysdk.Utils.requireNonNegative;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class Customization implements Parcelable {
    final int textFontSize;
    final String textColor;
    final String textFontName;

    abstract static class Builder<T extends Builder<T>> {
        int textFontSize;
        String textColor;
        String textFontName;

        public T setTextFontName(String textFontName) {
            this.textFontName = requireHasLength(textFontName, "textFontName");
            return self();
        }

        public T setTextColor(String textColor) {
            this.textColor = requireValidHexCode(textColor, "textColor");
            return self();
        }

        public T setTextFontSize(int textFontSize) {
            this.textFontSize = requireNonNegative(textFontSize, "textFontSize");
            return self();
        }

        protected abstract T self();

        abstract Customization build();
    }

    Customization(Builder<?> builder) {
        textColor = builder.textColor;
        textFontSize = builder.textFontSize;
        textFontName = builder.textFontName;
    }

    public String getTextColor() { return this.textColor; }

    public int getTextFontSize() { return this.textFontSize; }

    public String getTextFontName() { return this.textFontName; }

    // Parcelable implementation

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(textFontSize);
        out.writeString(textColor);
        out.writeString(textFontName);
    }

    Customization(Parcel in) {
        textFontSize = in.readInt();
        textColor = in.readString();
        textFontName = in.readString();
    }
}
