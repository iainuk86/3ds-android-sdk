package com.iainuk.mysdk.device;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.BLUETOOTH;
import static android.Manifest.permission.BLUETOOTH_CONNECT;
import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.WIFI_SERVICE;
import static com.iainuk.mysdk.device.DPNAEnum.RE03;
import static java.util.Map.entry;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;

import com.iainuk.mysdk.BuildConfig;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

final class CommonParamCollector extends ParamCollector {
    static void addCommonFields(ParamAccumulator accumulator) {
        addC001(accumulator);
        addC002(accumulator);
        addC003(accumulator);
        addC004(accumulator);
        addC005(accumulator);
        addC006(accumulator);
        addC008(accumulator);
        addC009(accumulator);
        addC010(accumulator);
        addC011AndC012(accumulator);
        addC013(accumulator);
        addC014(accumulator);
        addC015(accumulator);
        addC016(accumulator);
    }

    private static void addC001(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("C001", "Android");
    }

    private static void addC002(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("C002",
                (String.format("%s %s", Build.MANUFACTURER, Build.MODEL)));
    }

    private static void addC003(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("C003", String.format("Android %s %s API %s",
                versionCodeNameMap().get(Build.VERSION.SDK_INT),
                Build.VERSION.RELEASE,
                Build.VERSION.SDK_INT));
    }

    private static void addC004(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("C004", Build.VERSION.RELEASE);
    }

    private static void addC005(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("C005", String.format("%s-%s", Locale.getDefault().getLanguage(),
                Locale.getDefault().getCountry()));
    }

    private static void addC006(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("C006",
                String.valueOf(TimeZone.getDefault().getRawOffset() / 60_000));
    }

    private static void addC008(ParamAccumulator accumulator) {
        DisplayMetrics metrics = accumulator.getContext().getResources().getDisplayMetrics();
        accumulator.addToDeviceData("C008",
                String.format("%sx%s", metrics.heightPixels, metrics.widthPixels));
    }

    private static void addC009(ParamAccumulator accumulator) {
        if (Build.VERSION.SDK_INT >= 31) {
            if (!hasPermission(BLUETOOTH_CONNECT, accumulator.getContext())) {
                accumulator.addToUnavailableParams("C009", RE03);
                return;
            }
        } else if (!hasPermission(BLUETOOTH, accumulator.getContext())) {
            accumulator.addToUnavailableParams("C009", RE03);
            return;
        }

        accumulator.addToDeviceData("C009", Settings.Global.getString(
                accumulator.getContext().getContentResolver(), "bluetooth_name"));
    }

    private static void addC010(ParamAccumulator accumulator) {
        if (Build.VERSION.SDK_INT >= 23) {
            accumulator.addToDeviceData("C010", getIpAddressFromApi23(accumulator));
        } else {
            accumulator.addToDeviceData("C010", getIpAddressBeforeApi23(accumulator));
        }
    }

    @SuppressLint("MissingPermission")
    private static void addC011AndC012(ParamAccumulator accumulator) {
        if (!hasAnyPermission(List.of(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
                accumulator.getContext())) {
            accumulator.addToUnavailableParams("C011", RE03);
            accumulator.addToUnavailableParams("C012", RE03);
            return;
        }

        Location location = ((LocationManager) accumulator.getContext()
                .getSystemService(Context.LOCATION_SERVICE))
                .getLastKnownLocation(LocationManager.GPS_PROVIDER);

        accumulator.addToDeviceData("C011", String.valueOf(location.getLatitude()));
        accumulator.addToDeviceData("C012", String.valueOf(location.getLongitude()));
    }

    private static void addC013(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("C013", accumulator.getContext().getPackageName());
    }

    private static void addC014(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("C014", accumulator.getSdkAppId());
    }

    private static void addC015(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("C015", BuildConfig.VERSION_NAME);
    }

    private static void addC016(ParamAccumulator accumulator) {
        accumulator.addToDeviceData("C016", BuildConfig.SDK_REF_NUM);
    }

    private static Map<Integer, String> versionCodeNameMap() {
        return Map.ofEntries(
            entry(19, "KITKAT"),
            entry(20, "KITKAT_WATCH"),
            entry(21, "LOLLIPOP"),
            entry(22, "LOLLIPOP_MR1"),
            entry(23, "M"),
            entry(24, "N"),
            entry(25, "N_MR1"),
            entry(26, "O"),
            entry(27, "O_MR1"),
            entry(28, "P"),
            entry(29, "Q"),
            entry(30, "R"),
            entry(31, "S"),
            entry(32, "S_V2"),
            entry(33, "TIRAMISU"));
    }

    @SuppressLint("NewApi")
    private static String getIpAddressFromApi23(ParamAccumulator accumulator) {
        if (!hasPermission(ACCESS_NETWORK_STATE, accumulator.getContext())) {
            accumulator.addToUnavailableParams("C010", RE03);
            return null;
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) accumulator.getContext()
                .getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        List<LinkAddress> linkAddresses = connectivityManager
                .getLinkProperties(connectivityManager.getActiveNetwork()).getLinkAddresses();

        if (!linkAddresses.isEmpty()) {
            for (LinkAddress address : linkAddresses) {
                if (address.toString().contains(".")) {
                    return address.toString();
                }
            }
        }

        return null;
    }

    private static String getIpAddressBeforeApi23(ParamAccumulator accumulator) {
        if (!hasAllPermissions(List.of(ACCESS_WIFI_STATE, ACCESS_FINE_LOCATION),
                accumulator.getContext())) {
            accumulator.addToUnavailableParams("C010", RE03);
            return null;
        }

        WifiManager wifiManager = (WifiManager)
                accumulator.getContext().getApplicationContext().getSystemService(WIFI_SERVICE);
        int ip = wifiManager.getConnectionInfo().getIpAddress();
        return String.format(Locale.getDefault(), "%d.%d.%d.%d",
                (ip & 0xff),
                (ip >> 8 & 0xff),
                (ip >> 16 & 0xff),
                (ip >> 24 & 0xff));
    }
}