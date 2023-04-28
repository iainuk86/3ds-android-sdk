package com.iainuk.mysdk.ui.customization;

import static com.iainuk.mysdk.Utils.requireNonNegative;
import static com.iainuk.mysdk.Utils.requireValidHexCode;

import android.os.Parcel;

public final class TextBoxCustomization extends Customization {
    private final int borderWidth;
    private final int cornerRadius;
    private final String borderColor;

    public final static class Builder extends Customization.Builder<Builder> {
        private int borderWidth;
        private int cornerRadius;
        private String borderColor;

        public Builder setBorderWidth(int borderWidth) {
            this.borderWidth = requireNonNegative(borderWidth, "borderWidth");
            return this;
        }

        public Builder setCornerRadius(int cornerRadius) {
            this.cornerRadius = requireNonNegative(cornerRadius, "cornerRadius");
            return this;
        }

        public Builder setBorderColor(String borderColor) {
            this.borderColor = requireValidHexCode(borderColor, "borderColor");
            return this;
        }

        protected Builder self() {
            return this;
        }

        public TextBoxCustomization build() {
            return new TextBoxCustomization(this);
        }
    }

    public TextBoxCustomization(Builder builder) {
        super(builder);
        borderWidth = builder.borderWidth;
        borderColor = builder.borderColor;
        cornerRadius = builder.cornerRadius;
    }

    public int getBorderWidth() { return borderWidth; }

    public int getCornerRadius() { return cornerRadius; }

    public String getBorderColor() { return borderColor; }

    // Parcelable implementation

    public static final Creator<TextBoxCustomization> CREATOR = new Creator<>() {
        public TextBoxCustomization createFromParcel(Parcel in) {
            return new TextBoxCustomization(in);
        }

        public TextBoxCustomization[] newArray(int size) {
            return new TextBoxCustomization[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(borderWidth);
        out.writeInt(cornerRadius);
        out.writeString(borderColor);
    }

    public TextBoxCustomization(Parcel in) {
        super(in);
        borderWidth = in.readInt();
        cornerRadius = in.readInt();
        borderColor = in.readString();
    }
}
