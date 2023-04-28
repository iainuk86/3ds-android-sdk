package com.iainuk.mysdk.device;

import android.util.DisplayMetrics;

final class DisplayMetricParamCollector extends ParamCollector {
    static void addDisplayMetricParams(ParamAccumulator accumulator) {
        DisplayMetrics metrics = accumulator.getContext().getResources().getDisplayMetrics();

        addA131(accumulator, metrics);
        addA132(accumulator, metrics);
        addA133(accumulator, metrics);
        addA134(accumulator, metrics);
        addA135(accumulator, metrics);
    }

    private static void addA131(ParamAccumulator accumulator, DisplayMetrics metrics) {
        accumulator.addToDeviceData("A131", String.valueOf(metrics.density));
    }

    private static void addA132(ParamAccumulator accumulator, DisplayMetrics metrics) {
        accumulator.addToDeviceData("A132", String.valueOf(metrics.densityDpi));
    }

    private static void addA133(ParamAccumulator accumulator, DisplayMetrics metrics) {
        accumulator.addToDeviceData("A133", String.valueOf(metrics.scaledDensity));
    }

    private static void addA134(ParamAccumulator accumulator, DisplayMetrics metrics) {
        accumulator.addToDeviceData("A134", String.valueOf(metrics.xdpi));
    }

    private static void addA135(ParamAccumulator accumulator, DisplayMetrics metrics) {
        accumulator.addToDeviceData("A135", String.valueOf(metrics.ydpi));
    }
}