package com.iainuk.mysdk.device;

import static com.iainuk.mysdk.device.DPNAEnum.RE04;

import android.content.Context;
import android.text.TextUtils;

import java.util.Map;
import java.util.Set;

public final class ParamAccumulator {
    private String sdkAppId;
    private final Context context;
    private final Set<String> blacklist;
    private final Map<String, String> deviceData;
    private final Map<String, String> unavailableParams;

    public ParamAccumulator(Context context,
                            Set<String> blacklist,
                            Map<String, String> deviceData,
                            Map<String, String> unavailableParams) {
        this.context = context;
        this.blacklist = blacklist;
        this.deviceData = deviceData;
        this.unavailableParams = unavailableParams;
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

    public Map<String, String> getDeviceData() {
        return deviceData;
    }

    public Map<String, String> getUnavailableParams() {
        return unavailableParams;
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
        unavailableParams.put(deviceParameter, reason.name());
    }
}
