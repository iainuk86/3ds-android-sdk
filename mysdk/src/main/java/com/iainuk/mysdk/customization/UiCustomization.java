package com.iainuk.mysdk.customization;

import com.iainuk.mysdk.exception.InvalidInputException;

import java.util.HashMap;
import java.util.Map;

public final class UiCustomization {
    final Map<ButtonType, ButtonCustomization> buttonCustomizations = new HashMap<>();
    ToolbarCustomization toolbarCustomization;
    LabelCustomization labelCustomization;
    TextBoxCustomization textBoxCustomization;

    public UiCustomization() {}

    public ButtonCustomization getButtonCustomization(ButtonType buttonType)
            throws InvalidInputException {
        if (buttonType == null) {
            throw new InvalidInputException("Null buttonType provided");
        }
        return buttonCustomizations.get(buttonType);
    }

    public void setButtonCustomization(ButtonCustomization buttonCustomization,
                                       ButtonType buttonType) throws InvalidInputException {
        if (buttonCustomization == null || buttonType == null) {
            throw new InvalidInputException("Null value when setting buttonCustomization");
        }
        this.buttonCustomizations.put(buttonType, buttonCustomization);
    }

    public ToolbarCustomization getToolbarCustomization() { return toolbarCustomization; }

    public void setToolbarCustomization(ToolbarCustomization toolbarCustomization) {
        if (toolbarCustomization == null) {
            throw new InvalidInputException("Null value presented for toolbarCustomization");
        }
        this.toolbarCustomization = toolbarCustomization;
    }

    public LabelCustomization getLabelCustomization() { return labelCustomization; }

    public void setLabelCustomization(LabelCustomization labelCustomization) {
        if (labelCustomization == null) {
            throw new InvalidInputException("Null value presented for labelCustomization");
        }
        this.labelCustomization = labelCustomization;
    }

    public TextBoxCustomization getTextBoxCustomization() { return textBoxCustomization; }

    public void setTextBoxCustomization(TextBoxCustomization textBoxCustomization) {
        if (textBoxCustomization == null) {
            throw new InvalidInputException("Null value presented for textBoxCustomization");
        }
        this.textBoxCustomization = textBoxCustomization;
    }

    public enum ButtonType {SUBMIT, CONTINUE, NEXT, CANCEL, RESEND, OPEN_OOB_APP, ADD_CH}
    public enum UICustomizationType {DEFAULT, DARK, MONOCHROME}
}
