package com.iainuk.mysdk.ui.customization;

import static com.iainuk.mysdk.Utils.requireNonNull;

import java.util.HashMap;
import java.util.Map;

public final class UiCustomization {
    private final LabelCustomization labelCustomization;
    private final ToolbarCustomization toolbarCustomization;
    private final TextBoxCustomization textBoxCustomization;
    private final Map<ButtonType, ButtonCustomization> buttonCustomizations;

    public final static class Builder {
        private LabelCustomization labelCustomization;
        private ToolbarCustomization toolbarCustomization;
        private TextBoxCustomization textBoxCustomization;
        private Map<ButtonType, ButtonCustomization> buttonCustomizations = new HashMap<>();

        public Builder setLabelCustomization(LabelCustomization labelCustomization) {
            this.labelCustomization = requireNonNull(labelCustomization, "labelCustomization");
            return this;
        }

        public Builder getSetToolbarCustomization(ToolbarCustomization toolbarCustomization) {
            this.toolbarCustomization = requireNonNull(toolbarCustomization, "toolbarCustomization");
            return this;
        }

        public Builder setTextBoxCustomization(TextBoxCustomization textBoxCustomization) {
            this.textBoxCustomization = requireNonNull(textBoxCustomization, "textBoxCustomization");
            return this;
        }

        public Builder setButtonCustomizations(
                Map<ButtonType, ButtonCustomization> buttonCustomizations) {
            this.buttonCustomizations = new HashMap<>(buttonCustomizations);
            return this;
        }

        public Builder setButtonCustomization(ButtonType buttonType,
                                              ButtonCustomization buttonCustomization) {
            this.buttonCustomizations.put(
                    requireNonNull(buttonType, "buttonType"),
                    requireNonNull(buttonCustomization, "buttonCustomization"));
            return this;
        }

        public UiCustomization build() {
            return new UiCustomization(this);
        }
    }

    private UiCustomization(Builder builder) {
        this.labelCustomization = builder.labelCustomization;
        this.toolbarCustomization = builder.toolbarCustomization;
        this.textBoxCustomization = builder.textBoxCustomization;
        this.buttonCustomizations = builder.buttonCustomizations;
    }

    public LabelCustomization getLabelCustomization() {
        return labelCustomization;
    }

    public ToolbarCustomization getToolbarCustomization() {
        return toolbarCustomization;
    }

    public TextBoxCustomization getTextBoxCustomization() {
        return textBoxCustomization;
    }

    public ButtonCustomization getButtonCustomization(ButtonType buttonType) {
        return buttonCustomizations.get(requireNonNull(buttonType, "buttonType"));
    }

    public enum ButtonType {SUBMIT, CONTINUE, NEXT, CANCEL, RESEND}
}