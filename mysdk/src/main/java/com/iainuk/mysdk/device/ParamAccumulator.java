package com.iainuk.mysdk.device;

import static com.iainuk.mysdk.device.DPNAEnum.RE04;

import android.content.Context;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.HashSet;

public class ParamAccumulator {
    private String sdkAppId;
    private final Context context;
    private final HashSet<String> blacklist;
    private final HashMap<String, String> deviceData;
    private final HashMap<String, String> deviceParamNotAvailable;

    public ParamAccumulator(Context context, HashSet<String> blacklist,
                            HashMap<String, String> deviceData,
                            HashMap<String, String> deviceParamNotAvailable) {
        this.context = context;
        this.blacklist = blacklist;
        this.deviceData = deviceData;
        this.deviceParamNotAvailable = deviceParamNotAvailable;
    }

    public String getSdkAppId() {
        return sdkAppId;
    }

    public void setSdkAppId(String sdkAppId) {
        this.sdkAppId = sdkAppId;
    }

    public Context getContext() {
        return context;
    }

    public HashMap<String, String> getDeviceData() {
        return deviceData;
    }

    public HashMap<String, String> getDeviceParamNotAvailable() {
        return deviceParamNotAvailable;
    }

    public void addToDeviceData(String deviceParameter, String result) {
        if (blacklist.contains(deviceParameter)) {
            return;
        }

        if (TextUtils.isEmpty(result)) {
            addToUnavailableParams(deviceParameter, RE04);
        } else {
            deviceData.put(deviceParameter, result);
        }
    }

    public void addToUnavailableParams(String deviceParameter, DPNAEnum reason) {
        deviceParamNotAvailable.put(deviceParameter, reason.name());
    }
}
