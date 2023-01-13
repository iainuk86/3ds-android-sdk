package com.iainuk.mysdk.device;

import static android.Manifest.permission.READ_PHONE_STATE;
import static com.iainuk.mysdk.device.DPNAEnum.RE03;
import static com.iainuk.mysdk.device.DPNAEnum.RE04;

import android.annotation.SuppressLint;
import android.os.Build;

import java.util.Arrays;

final class BuildParamCollector extends ParamCollector {
    static void addBuildParams(ParamAccumulator accumulator) {
        addA042(accumulator);
        addA043(accumulator);
        addA044(accumulator);
        addA045(accumulator);
        addA046(accumulator);
        addA047(accumulator);
        addA048(accumulator);
        addA049(accumulator);
        addA050(accumulator);
        addA051(accumulator);
        addA052(accumulator);
        addA053(accumulator);
        addA054(accumulator);
        addA055(accumulator);
        addA056(accumulator);
        addA057(accumulator);
        addA058(accumulator);
        addA059(accumulator);
    }

    private static void addA042(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A042", Build.BOARD);
    }

    private static void addA043(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A043", Build.BOOTLOADER);
    }

    private static void addA044(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A044", Build.BRAND);
    }

    private static void addA045(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A045", Build.DEVICE);
    }

    private static void addA046(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A046", Build.DISPLAY);
    }

    private static void addA047(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A047", Build.FINGERPRINT);
    }

    private static void addA048(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A048", Build.HARDWARE);
    }

    private static void addA049(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A049", Build.ID);
    }

    private static void addA050(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A050", Build.MANUFACTURER);
    }

    private static void addA051(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A051", Build.PRODUCT);
    }

    private static void addA052(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A052", Build.getRadioVersion());
    }

    @SuppressLint("HardwareIds")
    private static void addA053(ParamAccumulator accumulator) {
        if (Build.VERSION.SDK_INT < 26) {
            accumulator.addToDeviceData("A053", Build.SERIAL);
            return;
        }

        if (Build.VERSION.SDK_INT >= 29) {
            accumulator.addToUnavailableParams("A053", RE04);
        } else if (!hasPermission(READ_PHONE_STATE, accumulator.getContext())) {
            accumulator.addToUnavailableParams("A053", RE03);
        } else {
            accumulator.addToDeviceData("A053", Build.UNKNOWN);
        }
    }

    private static void addA054(ParamAccumulator accumulator) {
        if (Build.VERSION.SDK_INT >= 21) {
            accumulator.addToDeviceData("A054", Arrays.toString(Build.SUPPORTED_32_BIT_ABIS));
        } else {
            accumulator.addToUnavailableParams("A054", RE04);
        }
    }

    private static void addA055(ParamAccumulator accumulator) {
        if (Build.VERSION.SDK_INT >= 21) {
            accumulator.addToDeviceData("A055", Arrays.toString(Build.SUPPORTED_64_BIT_ABIS));
        } else {
            accumulator.addToUnavailableParams("A055", RE04);
        }
    }

    private static void addA056(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A056", Build.TAGS);
    }

    private static void addA057(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A057", String.valueOf(Build.TIME));
    }

    private static void addA058(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A058", Build.TYPE);
    }

    private static void addA059(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A059", Build.USER);
    }
}