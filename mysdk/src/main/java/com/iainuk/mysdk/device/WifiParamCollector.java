package com.iainuk.mysdk.device;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.content.Context.CONNECTIVITY_SERVICE;
import static com.iainuk.mysdk.device.DPNAEnum.RE03;
import static com.iainuk.mysdk.device.DPNAEnum.RE04;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

import java.util.List;

final class WifiParamCollector extends ParamCollector {
    static void addWifiManagerFields(ParamAccumulator accumulator) {
        WifiManager wifiManager = (WifiManager) accumulator.getContext()
                .getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        // To obtain WifiInfo, ACCESS_NETWORK_STATE is needed for API level 31 and above
        // For level 30 and under, ACCESS_WIFI_STATE is needed
        boolean hasWifiInfoPermissions = hasWifiInfoPermissions(accumulator);
        WifiInfo wifiInfo = getWifiInfo(accumulator, wifiManager, hasWifiInfoPermissions);
        addWifiInfoParams(accumulator, wifiInfo, hasWifiInfoPermissions);

        addA032(accumulator, wifiManager);
        addA033(accumulator, wifiManager);
        addA034(accumulator, wifiManager);
        addA035(accumulator, wifiManager);
        addA036(accumulator, wifiManager);
        addA037(accumulator, wifiManager);
        addA038(accumulator, wifiManager);
        addA146(accumulator, wifiManager);
    }

    private static void addWifiInfoParams(ParamAccumulator accumulator, WifiInfo wifiInfo,
                                          boolean hasWifiInfoPermissions) {
        addA028(accumulator, wifiInfo, hasWifiInfoPermissions);
        addA029(accumulator, wifiInfo, hasWifiInfoPermissions);
        addA030(accumulator, wifiInfo, hasWifiInfoPermissions);
        addA031(accumulator, wifiInfo, hasWifiInfoPermissions);
        addA147(accumulator, wifiInfo, hasWifiInfoPermissions);
        addA148(accumulator, wifiInfo, hasWifiInfoPermissions);
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    private static void addA028(ParamAccumulator accumulator, WifiInfo wifiInfo,
                                boolean hasWifiInfoPermissions) {
        if (!hasWifiInfoPermissions) {
            accumulator.addToUnavailableParams("A028", RE03);
            return;
        }

        // 02:00:00:00:00:00 if doesn't have both LOCAL_MAC_ADDRESS and ACCESS_FINE_LOCATION
        if (Build.VERSION.SDK_INT < 33 && wifiInfo != null) {
            accumulator.addToDeviceData("A028", wifiInfo.getMacAddress());
        } else {
            accumulator.addToUnavailableParams("A028", RE04);
        }
    }

    private static void addA029(ParamAccumulator accumulator, WifiInfo wifiInfo,
                                boolean hasWifiInfoPermissions) {
        if (!hasWifiInfoPermissions) {
            accumulator.addToUnavailableParams("A029", RE03);
            return;
        }

        if (wifiInfo != null) {
            accumulator.addToDeviceData("A029", wifiInfo.getBSSID());
        } else {
            accumulator.addToUnavailableParams("A029", RE04);
        }
    }

    private static void addA030(ParamAccumulator accumulator, WifiInfo wifiInfo,
                                boolean hasWifiInfoPermissions) {
        if (!hasWifiInfoPermissions) {
            accumulator.addToUnavailableParams("A030", RE03);
            return;
        }

        if (wifiInfo != null) {
            accumulator.addToDeviceData("A030", wifiInfo.getSSID());
        } else {
            accumulator.addToUnavailableParams("A030", RE04);
        }
    }

    private static void addA031(ParamAccumulator accumulator, WifiInfo wifiInfo,
                                boolean hasWifiInfoPermissions) {
        if (!hasWifiInfoPermissions) {
            accumulator.addToUnavailableParams("A031", RE03);
            return;
        }

        if (wifiInfo != null) {
            accumulator.addToDeviceData("A031", String.valueOf(wifiInfo.getNetworkId()));
        } else {
            accumulator.addToUnavailableParams("A031", RE04);
        }
    }

    private static void addA032(ParamAccumulator accumulator, WifiManager wifiManager) {
        accumulator.addToDeviceData("A032",
                String.valueOf(wifiManager.is5GHzBandSupported()));
    }

    private static void addA033(ParamAccumulator accumulator, WifiManager wifiManager) {
        if (Build.VERSION.SDK_INT < 29) {
            accumulator.addToDeviceData("A033",
                    String.valueOf(wifiManager.isDeviceToApRttSupported()));
        } else {
            accumulator.addToDeviceData("A033",
                    String.valueOf(accumulator.getContext().getPackageManager()
                            .hasSystemFeature(PackageManager.FEATURE_WIFI_RTT)));
        }
    }

    private static void addA034(ParamAccumulator accumulator, WifiManager wifiManager) {
        accumulator.addToDeviceData("A034",
                String.valueOf(wifiManager.isEnhancedPowerReportingSupported()));
    }

    private static void addA035(ParamAccumulator accumulator, WifiManager wifiManager) {
        accumulator.addToDeviceData("A035", String.valueOf(wifiManager.isP2pSupported()));
    }

    private static void addA036(ParamAccumulator accumulator, WifiManager wifiManager) {
        accumulator.addToDeviceData("A036",
                String.valueOf(wifiManager.isPreferredNetworkOffloadSupported()));
    }

    private static void addA037(ParamAccumulator accumulator, WifiManager wifiManager) {
        if (Build.VERSION.SDK_INT < 29) {
            accumulator.addToDeviceData("A037",
                    String.valueOf(wifiManager.isScanAlwaysAvailable()));
        } else {
            accumulator.addToUnavailableParams("A037", RE04);
        }
    }

    private static void addA038(ParamAccumulator accumulator, WifiManager wifiManager) {
        accumulator.addToDeviceData("A038", String.valueOf(wifiManager.isTdlsSupported()));
    }

    private static void addA146(ParamAccumulator accumulator, WifiManager wifiManager) {
        if (Build.VERSION.SDK_INT >= 30) {
            accumulator.addToDeviceData("A146",
                    String.valueOf(wifiManager.is6GHzBandSupported()));
        } else {
            accumulator.addToUnavailableParams("A146", RE04);
        }
    }

    private static void addA147(ParamAccumulator accumulator, WifiInfo wifiInfo,
                                boolean hasWifiInfoPermissions) {
        if (!hasWifiInfoPermissions) {
            accumulator.addToUnavailableParams("A147", RE03);
            return;
        }

        if (Build.VERSION.SDK_INT >= 29 && wifiInfo != null) {
            accumulator.addToDeviceData("A147", wifiInfo.getPasspointFqdn());
        } else {
            accumulator.addToUnavailableParams("A147", RE04);
        }
    }

    private static void addA148(ParamAccumulator accumulator, WifiInfo wifiInfo,
                                boolean hasWifiInfoPermissions) {
        if (!hasWifiInfoPermissions) {
            accumulator.addToUnavailableParams("A148", RE03);
            return;
        }

        if (Build.VERSION.SDK_INT >= 29 && wifiInfo != null) {
            accumulator.addToDeviceData("A148", wifiInfo.getPasspointProviderFriendlyName());
        } else {
            accumulator.addToUnavailableParams("A148", RE04);
        }
    }

    private static boolean hasWifiInfoPermissions(ParamAccumulator accumulator) {
        if (Build.VERSION.SDK_INT < 31) {
            return hasAllPermissions(List.of(ACCESS_WIFI_STATE, ACCESS_FINE_LOCATION),
                    accumulator.getContext());
        } else {
            return hasPermission(ACCESS_NETWORK_STATE, accumulator.getContext());
        }
    }

    private static WifiInfo getWifiInfo(ParamAccumulator accumulator, WifiManager wifiManager,
                                        boolean hasWifiInfoPermissions) {
        if (!hasWifiInfoPermissions) {
            return null;
        }

        if (Build.VERSION.SDK_INT < 31) {
            return wifiManager.getConnectionInfo();
        }

        ConnectivityManager connectivityManager = (ConnectivityManager)
                accumulator.getContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkCapabilities capabilities = connectivityManager
                .getNetworkCapabilities(connectivityManager.getActiveNetwork());

        return capabilities != null ? (WifiInfo) capabilities.getTransportInfo() : null;
    }
}