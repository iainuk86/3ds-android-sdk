package com.iainuk.mysdk.device;

import static com.iainuk.mysdk.device.BluetoothParamCollector.addBluetoothAdapterFields;
import static com.iainuk.mysdk.device.BuildParamCollector.addBuildParams;
import static com.iainuk.mysdk.device.BuildVersionParamCollector.addBuildVersionParams;
import static com.iainuk.mysdk.device.CommonParamCollector.addCommonFields;
import static com.iainuk.mysdk.device.DisplayMetricParamCollector.addDisplayMetricParams;
import static com.iainuk.mysdk.device.GlobalSettingsParamCollector.addGlobalSettingsParams;
import static com.iainuk.mysdk.device.PackageParamCollector.addPackageManagerParams;
import static com.iainuk.mysdk.device.SecureSettingsParamCollector.addSecureSettingsParams;
import static com.iainuk.mysdk.device.SystemSettingsParamCollector.addSystemSettingsParams;
import static com.iainuk.mysdk.device.TelephonyParamCollector.addTelephonyManagerFields;
import static com.iainuk.mysdk.device.WifiParamCollector.addWifiManagerFields;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.webkit.WebSettings;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class DeviceInfoCollector {
    public static JSONObject collectDeviceInfo(Context context, HashSet<String> blacklist) {
        ParamAccumulator accumulator = new ParamAccumulator(context, blacklist,
                new HashMap<>(), new HashMap<>());

        addCommonFields(accumulator);
        addTelephonyManagerFields(accumulator);
        addWifiManagerFields(accumulator);
        addBluetoothAdapterFields(accumulator);
        addBuildParams(accumulator);
        addBuildVersionParams(accumulator);
        addSecureSettingsParams(accumulator);
        addGlobalSettingsParams(accumulator);
        addSystemSettingsParams(accumulator);
        addPackageManagerParams(accumulator);
        addDisplayMetricParams(accumulator);
        addA129(accumulator);
        addA130(accumulator);
        addA136(accumulator);
        addA137(accumulator);

        return new JSONObject();
    }

    /////////////////////////////////////////////////////
    // Param types that do not warrant their own class //
    /////////////////////////////////////////////////////

    private static void addA129(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A129", Environment.getExternalStorageState());
    }

    private static void addA130(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A130", Arrays.toString(Locale.getAvailableLocales()));
    }

    private static void addA136(ParamAccumulator accumulator) {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        accumulator.addToDeviceData("A136", String.valueOf(statFs.getTotalBytes()));
    }

    private static void addA137(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("A137",
                WebSettings.getDefaultUserAgent(accumulator.getContext()));
    }
}