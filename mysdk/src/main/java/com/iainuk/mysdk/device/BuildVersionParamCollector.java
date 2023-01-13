package com.iainuk.mysdk.device;

import static com.iainuk.mysdk.device.DPNAEnum.RE04;

import android.os.Build;

final class BuildVersionParamCollector extends ParamCollector {
    static void addBuildVersionParams(ParamAccumulator accumulator) {
        addA060(accumulator);
        addA061(accumulator);
        addA062(accumulator);
        addA063(accumulator);
        addA064(accumulator);
    }

    private static void addA060(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A060", Build.VERSION.CODENAME);
    }

    private static void addA061(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A061", Build.VERSION.INCREMENTAL);
    }

    private static void addA062(ParamAccumulator accumulator) {
        if (Build.VERSION.SDK_INT >= 23) {
            accumulator.addToDeviceData("A062", String.valueOf(Build.VERSION.PREVIEW_SDK_INT));
        } else {
            accumulator.addToUnavailableParams("A062", RE04);
        }
    }

    private static void addA063(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A063", String.valueOf(Build.VERSION.SDK_INT));
    }

    private static void addA064(ParamAccumulator accumulator) {
        if (Build.VERSION.SDK_INT >= 23) {
            accumulator.addToDeviceData("A064", Build.VERSION.SECURITY_PATCH);
        } else {
            accumulator.addToUnavailableParams("A064", RE04);
        }
    }
}