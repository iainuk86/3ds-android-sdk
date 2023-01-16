package com.iainuk.mysdk.ui.customization;

import static com.iainuk.mysdk.Utils.requireHasLength;
import static com.iainuk.mysdk.Utils.requireNonNegative;
import static com.iainuk.mysdk.Utils.requireValidHexCode;

import android.os.Parcel;

public final class LabelCustomization extends Customization {
    private final int headingTextFontSize;
    private final String headingTextColor;
    private final String headingTextFontName;

    public final static class Builder extends Customization.Builder<Builder> {
        private int headingTextFontSize;
        private String headingTextColor;
        private String headingTextFontName;

        public Builder setHeadingTextFontSize(int headingTextFontSize) {
            this.headingTextFontSize = requireNonNegative(headingTextFontSize, "headingTextFontSize");
            return this;
        }

        public Builder setHeadingTextColor(String headingTextColor) {
            this.headingTextColor = requireValidHexCode(headingTextColor, "headingTextColor");
            return this;
        }

        public Builder setHeadingTextFontName(String headingTextFontName) {
            this.headingTextFontName = requireHasLength(headingTextFontName, "headingTextFontName");
            return this;
        }

        protected Builder self() {
            return this;
        }

        public LabelCustomization build() {
            return new LabelCustomization(this);
        }
    }

    private LabelCustomization(Builder builder) {
        super(builder);
        headingTextColor = builder.headingTextColor;
        headingTextFontName = builder.headingTextFontName;
        headingTextFontSize = builder.headingTextFontSize;
    }

    public String getHeadingTextColor() { return headingTextColor; }

    public int getHeadingTextFontSize() { return headingTextFontSize; }

    public String getHeadingTextFontName() { return headingTextFontName; }

    // Parcelable implementation

    public static final Creator<LabelCustomization> CREATOR = new Creator<>() {
        public LabelCustomization createFromParcel(Parcel in) {
            return new LabelCustomization(in);
        }

        public LabelCustomization[] newArray(int size) {
            return new LabelCustomization[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(headingTextFontSize);
        out.writeString(headingTextColor);
        out.writeString(headingTextFontName);
    }

    public LabelCustomization(Parcel in) {
        super(in);
        headingTextFontSize = in.readInt();
        headingTextColor = in.readString();
        headingTextFontName = in.readString();
    }
}
