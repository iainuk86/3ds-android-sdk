package com.iainuk.mysdk.ui.customization;

import static com.iainuk.mysdk.Utils.requireNonNegative;
import static com.iainuk.mysdk.Utils.requireValidHexCode;

import android.os.Parcel;

public final class ButtonCustomization extends Customization {
    private final int cornerRadius;
    private final String backgroundColor;

    public final static class Builder extends Customization.Builder<Builder> {
        private int cornerRadius;
        private String backgroundColor;

        public Builder setCornerRadius(int cornerRadius) {
            this.cornerRadius = requireNonNegative(cornerRadius, "cornerRadius");
            return this;
        }

        public Builder setBackgroundColor(String backgroundColor) {
            this.backgroundColor = requireValidHexCode(backgroundColor, "backgroundColor");
            return this;
        }

        protected Builder self() {
            return this;
        }

        public ButtonCustomization build() {
            return new ButtonCustomization(this);
        }
    }

    private ButtonCustomization(Builder builder) {
        super(builder);
        cornerRadius = builder.cornerRadius;
        backgroundColor = builder.backgroundColor;
    }

    public int getCornerRadius() { return cornerRadius; }

    public String getBackgroundColor() { return backgroundColor; }

    // Parcelable implementation

    public static final Creator<ButtonCustomization> CREATOR = new Creator<>() {
        public ButtonCustomization createFromParcel(Parcel in) {
            return new ButtonCustomization(in);
        }

        public ButtonCustomization[] newArray(int size) {
            return new ButtonCustomization[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(cornerRadius);
        out.writeString(backgroundColor);
    }

    public ButtonCustomization(Parcel in) {
        super(in);
        cornerRadius = in.readInt();
        backgroundColor = in.readString();
    }
}
