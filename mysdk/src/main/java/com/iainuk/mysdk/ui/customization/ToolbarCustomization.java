package com.iainuk.mysdk.ui.customization;

import static com.iainuk.mysdk.Utils.requireHasLength;
import static com.iainuk.mysdk.Utils.requireValidHexCode;

import android.os.Parcel;

public final class ToolbarCustomization extends Customization {
    private final String headerText;
    private final String buttonText;
    private final String backgroundColor;

    public final static class Builder extends Customization.Builder<Builder> {
        private String headerText;
        private String buttonText;
        private String backgroundColor;

        public Builder setHeaderText(String headerText) {
            this.headerText = requireHasLength(headerText, "headerText");
            return this;
        }

        public Builder setButtonText(String buttonText) {
            this.buttonText = requireHasLength(buttonText, "buttonText");
            return this;
        }

        public Builder setBackgroundColor(String backgroundColor) {
            this.backgroundColor = requireValidHexCode(backgroundColor, "backgroundColor");
            return this;
        }

        protected Builder self() {
            return this;
        }

        public ToolbarCustomization build() {
            return new ToolbarCustomization(this);
        }
    }

    public ToolbarCustomization(Builder builder) {
        super(builder);
        headerText = builder.headerText;
        buttonText = builder.buttonText;
        backgroundColor = builder.backgroundColor;
    }

    public String getHeaderText() { return headerText; }

    public String getButtonText() { return buttonText; }

    public String getBackgroundColor() { return backgroundColor; }

    // Parcelable implementation

    public static final Creator<ToolbarCustomization> CREATOR = new Creator<>() {
        public ToolbarCustomization createFromParcel(Parcel in) {
            return new ToolbarCustomization(in);
        }

        public ToolbarCustomization[] newArray(int size) {
            return new ToolbarCustomization[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeString(headerText);
        out.writeString(buttonText);
        out.writeString(backgroundColor);
    }

    public ToolbarCustomization(Parcel in) {
        super(in);
        headerText = in.readString();
        buttonText = in.readString();
        backgroundColor = in.readString();
    }
}
