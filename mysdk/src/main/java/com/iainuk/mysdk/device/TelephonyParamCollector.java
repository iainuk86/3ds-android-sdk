package com.iainuk.mysdk.device;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.content.Context.TELEPHONY_SUBSCRIPTION_SERVICE;
import static android.content.pm.PackageManager.FEATURE_TELEPHONY;
import static android.telephony.SubscriptionManager.DEFAULT_SUBSCRIPTION_ID;
import static com.iainuk.mysdk.device.DPNAEnum.RE03;
import static com.iainuk.mysdk.device.DPNAEnum.RE04;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;

import java.util.List;

final class TelephonyParamCollector extends ParamCollector {
    static void addTelephonyManagerFields(ParamAccumulator accumulator) {
        if (!accumulator.getContext().getPackageManager().hasSystemFeature(FEATURE_TELEPHONY)) {
            setAllParamsAsUnavailable(accumulator);
            return;
        }

        TelephonyManager telephonyManager = (TelephonyManager)
                accumulator.getContext().getSystemService(Context.TELEPHONY_SERVICE);

        boolean canReadPhoneState = hasPermission(READ_PHONE_STATE, accumulator.getContext());
        addParamsThatNeedPermission(accumulator, telephonyManager, canReadPhoneState);

        addA006(accumulator, telephonyManager);
        addA007(accumulator, telephonyManager);
        addA008(accumulator, telephonyManager);
        addA009(accumulator, telephonyManager);
        addA010(accumulator, telephonyManager);
        addA012(accumulator, telephonyManager);
        addA013(accumulator, telephonyManager);
        addA014(accumulator, telephonyManager);
        addA015(accumulator, telephonyManager);
        addA016(accumulator, telephonyManager);
        addA018(accumulator, telephonyManager);
        addA021(accumulator, telephonyManager);
        addA022(accumulator, telephonyManager);
        addA023(accumulator, telephonyManager);
        addA024(accumulator, telephonyManager);
        addA026(accumulator, telephonyManager);
        addA027(accumulator, telephonyManager);
        addA138(accumulator, telephonyManager);
        addA139(accumulator, telephonyManager);
        addA140(accumulator, telephonyManager);
        addA141(accumulator, telephonyManager);
        addA142(accumulator, telephonyManager);
        addA144(accumulator, telephonyManager);
        addA145(accumulator, telephonyManager);
    }

    private static void addParamsThatNeedPermission(ParamAccumulator accumulator,
                                                    TelephonyManager telephonyManager,
                                                    boolean canReadPhoneState) {
        addA001(accumulator, telephonyManager, canReadPhoneState);
        addA002(accumulator, telephonyManager, canReadPhoneState);
        addA003(accumulator, telephonyManager, canReadPhoneState);
        addA004(accumulator, telephonyManager, canReadPhoneState);
        addA005(accumulator, telephonyManager, canReadPhoneState);
        addA011(accumulator, telephonyManager, canReadPhoneState);
        addA017(accumulator, telephonyManager, canReadPhoneState);
        addA019(accumulator, telephonyManager, canReadPhoneState);
        addA020(accumulator, telephonyManager, canReadPhoneState);
        addA025(accumulator, telephonyManager, canReadPhoneState);
        addA143(accumulator, telephonyManager, canReadPhoneState);
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    private static void addA001(ParamAccumulator accumulator, TelephonyManager telephonyManager,
                                boolean canReadPhoneState) {
        if (Build.VERSION.SDK_INT >= 29) {
            accumulator.addToUnavailableParams("A001", RE04);
            return;
        }

        if (!canReadPhoneState) {
            accumulator.addToUnavailableParams("A001", RE03);
            return;
        }

        String deviceId = Build.VERSION.SDK_INT < 26
                ? telephonyManager.getDeviceId()
                : Settings.Secure.getString(
                        accumulator.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        accumulator.addToDeviceData("A001", deviceId);
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    private static void addA002(ParamAccumulator accumulator, TelephonyManager telephonyManager,
                                boolean canReadPhoneState) {
        if (Build.VERSION.SDK_INT >= 29) {
            accumulator.addToUnavailableParams("A002", RE04);
            return;
        }

        if (!canReadPhoneState) {
            accumulator.addToUnavailableParams("A002", RE03);
            return;
        }

        accumulator.addToDeviceData("A002", telephonyManager.getSubscriberId());
    }

    @SuppressLint("MissingPermission")
    private static void addA003(ParamAccumulator accumulator, TelephonyManager telephonyManager,
                                boolean canReadPhoneState) {
        if (!canReadPhoneState) {
            accumulator.addToUnavailableParams("A003", RE03);
        } else {
            accumulator.addToDeviceData("A003", telephonyManager.getDeviceSoftwareVersion());
        }
    }

    @SuppressLint("MissingPermission")
    private static void addA004(ParamAccumulator accumulator, TelephonyManager telephonyManager,
                                boolean canReadPhoneState) {
        if (!canReadPhoneState) {
            accumulator.addToUnavailableParams("A004", RE03);
        } else {
            accumulator.addToDeviceData("A004", telephonyManager.getGroupIdLevel1());
        }
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    private static void addA005(ParamAccumulator accumulator, TelephonyManager telephonyManager,
                                boolean canReadPhoneState) {
        if (!hasLine1Permissions(accumulator.getContext(), canReadPhoneState)) {
            accumulator.addToUnavailableParams("A005", RE03);
            return;
        }

        if (Build.VERSION.SDK_INT >= 33) {
            try {
                SubscriptionManager subscriptionManager = (SubscriptionManager)
                        accumulator.getContext().getSystemService(TELEPHONY_SUBSCRIPTION_SERVICE);
                accumulator.addToDeviceData("A005",
                        subscriptionManager.getPhoneNumber(DEFAULT_SUBSCRIPTION_ID));
            } catch (Exception e) {
                accumulator.addToUnavailableParams("A005", RE04);
            }
            return;
        }

        accumulator.addToDeviceData("A005", telephonyManager.getLine1Number());
    }

    private static void addA006(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        try {
            accumulator.addToDeviceData("A006", telephonyManager.getMmsUAProfUrl());
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A006", RE04);
        }
    }

    private static void addA007(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        try {
            accumulator.addToDeviceData("A007", telephonyManager.getMmsUserAgent());
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A007", RE04);
        }
    }

    private static void addA008(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        try {
            accumulator.addToDeviceData("A008", telephonyManager.getNetworkCountryIso());
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A008", RE04);
        }
    }

    private static void addA009(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        try {
            accumulator.addToDeviceData("A009", telephonyManager.getNetworkOperator());
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A009", RE04);
        }
    }

    private static void addA010(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        try {
            accumulator.addToDeviceData("A010", telephonyManager.getNetworkOperatorName());
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A010", RE04);
        }
    }

    @SuppressLint("MissingPermission")
    private static void addA011(ParamAccumulator accumulator, TelephonyManager telephonyManager,
                                boolean canReadPhoneState) {
        if (!canReadPhoneState) {
            accumulator.addToUnavailableParams("A011", RE03);
            return;
        }

        if (Build.VERSION.SDK_INT >= 24) {
            try {
                accumulator.addToDeviceData("A011",
                        String.valueOf(telephonyManager.getDataNetworkType()));
            } catch (Exception e) {
                accumulator.addToUnavailableParams("A011", RE04);
            }
        } else {
            accumulator.addToDeviceData("A011",
                    String.valueOf(telephonyManager.getNetworkType()));
        }
    }

    private static void addA012(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        if (Build.VERSION.SDK_INT < 23) {
            accumulator.addToUnavailableParams("A012", RE04);
        } else if (Build.VERSION.SDK_INT < 30) {
            accumulator.addToDeviceData("A012", String.valueOf(telephonyManager.getPhoneCount()));
        } else {
            accumulator.addToDeviceData("A012",
                    String.valueOf(telephonyManager.getActiveModemCount()));
        }
    }

    private static void addA013(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        accumulator.addToDeviceData("A013", String.valueOf(telephonyManager.getPhoneType()));
    }

    private static void addA014(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        try {
            accumulator.addToDeviceData("A014", telephonyManager.getSimCountryIso());
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A014", RE04);
        }
    }

    private static void addA015(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        try {
            accumulator.addToDeviceData("A015", telephonyManager.getSimOperator());
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A015", RE04);
        }
    }

    private static void addA016(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        try {
            accumulator.addToDeviceData("A016", telephonyManager.getSimOperatorName());
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A016", RE04);
        }
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    private static void addA017(ParamAccumulator accumulator, TelephonyManager telephonyManager,
                                boolean canReadPhoneState) {
        if (Build.VERSION.SDK_INT >= 29) {
            accumulator.addToUnavailableParams("A017", RE04);
            return;
        }

        if (!canReadPhoneState) {
            accumulator.addToUnavailableParams("A017", RE03);
            return;
        }

        try {
            accumulator.addToDeviceData("A017", telephonyManager.getSimSerialNumber());
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A017", RE04);
        }
    }

    private static void addA018(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        try {
            accumulator.addToDeviceData("A018", String.valueOf(telephonyManager.getSimState()));
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A018", RE04);
        }
    }

    @SuppressLint("MissingPermission")
    private static void addA019(ParamAccumulator accumulator, TelephonyManager telephonyManager,
                                boolean canReadPhoneState) {
        if (!canReadPhoneState) {
            accumulator.addToUnavailableParams("A019", RE03);
            return;
        }

        try {
            accumulator.addToDeviceData("A019", telephonyManager.getVoiceMailAlphaTag());
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A019", RE04);
        }
    }

    @SuppressLint("MissingPermission")
    private static void addA020(ParamAccumulator accumulator, TelephonyManager telephonyManager,
                                boolean canReadPhoneState) {
        if (!canReadPhoneState) {
            accumulator.addToUnavailableParams("A020", RE03);
            return;
        }

        try {
            accumulator.addToDeviceData("A020", telephonyManager.getVoiceMailNumber());
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A020", RE04);
        }
    }

    private static void addA021(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        try {
            accumulator.addToDeviceData("A021", String.valueOf(telephonyManager.hasIccCard()));
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A021", RE04);
        }
    }

    private static void addA022(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        if (Build.VERSION.SDK_INT < 23) {
            accumulator.addToUnavailableParams("A022", RE04);
            return;
        }

        try {
            accumulator.addToDeviceData("A022",
                    String.valueOf(telephonyManager.isHearingAidCompatibilitySupported()));
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A022", RE04);
        }
    }

    private static void addA023(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        try {
            accumulator.addToDeviceData("A023", String.valueOf(telephonyManager.isNetworkRoaming()));
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A023", RE04);
        }
    }

    private static void addA024(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        if (Build.VERSION.SDK_INT < 21) {
            accumulator.addToUnavailableParams("A024", RE04);
            return;
        }

        try {
            accumulator.addToDeviceData("A024", String.valueOf(telephonyManager.isSmsCapable()));
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A024", RE04);
        }
    }

    @SuppressLint("MissingPermission")
    private static void addA025(ParamAccumulator accumulator, TelephonyManager telephonyManager,
                                boolean canReadPhoneState) {
        if (Build.VERSION.SDK_INT < 23) {
            accumulator.addToUnavailableParams("A025", RE04);
        } else if (Build.VERSION.SDK_INT < 28) {
            accumulator.addToDeviceData("A025",
                    String.valueOf(telephonyManager.isTtyModeSupported()));
        } else if (!canReadPhoneState) {
            accumulator.addToUnavailableParams("A025", RE03);
        } else {
            TelecomManager tm = (TelecomManager)
                    accumulator.getContext().getSystemService(Context.TELECOM_SERVICE);
            accumulator.addToDeviceData("A025", String.valueOf(tm.isTtySupported()));
        }
    }

    private static void addA026(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        if (Build.VERSION.SDK_INT < 22) {
            accumulator.addToUnavailableParams("A026", RE04);
            return;
        }

        try {
            accumulator.addToDeviceData("A026", String.valueOf(telephonyManager.isVoiceCapable()));
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A026", RE04);
        }
    }

    private static void addA027(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        if (Build.VERSION.SDK_INT < 23) {
            accumulator.addToUnavailableParams("A027", RE04);
        } else {
            accumulator.addToDeviceData("A027", String.valueOf(telephonyManager.isWorldPhone()));
        }
    }

    private static void addA138(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        if (Build.VERSION.SDK_INT < 28) {
            accumulator.addToUnavailableParams("A138", RE04);
        } else {
            accumulator.addToDeviceData("A138", String.valueOf(telephonyManager.getSimCarrierId()));
        }
    }

    private static void addA139(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        if (Build.VERSION.SDK_INT < 28) {
            accumulator.addToUnavailableParams("A139", RE04);
            return;
        }

        try {
            accumulator.addToDeviceData("A139",
                    String.valueOf(telephonyManager.getSimCarrierIdName()));
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A139", RE04);
        }
    }

    private static void addA140(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        if (Build.VERSION.SDK_INT < 29) {
            accumulator.addToUnavailableParams("A140", RE04);
            return;
        }

        try {
            accumulator.addToDeviceData("A140", telephonyManager.getManufacturerCode());
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A140", RE04);
        }
    }

    private static void addA141(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        if (Build.VERSION.SDK_INT < 29) {
            accumulator.addToUnavailableParams("A141", RE04);
            return;
        }

        try {
            accumulator.addToDeviceData("A141",
                    String.valueOf(telephonyManager.getSimSpecificCarrierId()));
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A141", RE04);
        }
    }

    private static void addA142(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        if (Build.VERSION.SDK_INT < 29) {
            accumulator.addToUnavailableParams("A142", RE04);
            return;
        }

        try {
            accumulator.addToDeviceData("A142",
                    String.valueOf(telephonyManager.getSimSpecificCarrierIdName()));
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A142", RE04);
        }
    }

    @SuppressLint("MissingPermission")
    private static void addA143(ParamAccumulator accumulator, TelephonyManager telephonyManager,
                                boolean canReadPhoneState) {
        if (Build.VERSION.SDK_INT < 29) {
            accumulator.addToUnavailableParams("A143", RE04);
            return;
        }

        if (!canReadPhoneState) {
            accumulator.addToUnavailableParams("A143", RE03);
            return;
        }

        try {
            accumulator.addToDeviceData("A143",
                    String.valueOf(telephonyManager.isMultiSimSupported()));
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A143", RE04);
        }
    }

    private static void addA144(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        if (Build.VERSION.SDK_INT < 30) {
            accumulator.addToUnavailableParams("A144", RE04);
            return;
        }

        try {
            accumulator.addToDeviceData("A144", telephonyManager.getNetworkCountryIso(0));
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A144", RE04);
        }
    }

    private static void addA145(ParamAccumulator accumulator, TelephonyManager telephonyManager) {
        if (Build.VERSION.SDK_INT < 30) {
            accumulator.addToUnavailableParams("A145", RE04);
            return;
        }

        try {
            accumulator.addToDeviceData("A145",
                    String.valueOf(telephonyManager.getSubscriptionId()));
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A145", RE04);
        }
    }

    private static boolean hasLine1Permissions(Context context, boolean canReadPhoneState) {
        if (Build.VERSION.SDK_INT >= 33) {
            return hasPermission(READ_PHONE_NUMBERS, context);
        } else if (Build.VERSION.SDK_INT >= 30) {
            return hasAnyPermission(List.of(READ_SMS, READ_PHONE_NUMBERS), context);
        } else {
            return canReadPhoneState;
        }
    }

    private static void setAllParamsAsUnavailable(ParamAccumulator accumulator) {
        for (TelephonyParamIdentifiers id : TelephonyParamIdentifiers.values()) {
            accumulator.addToUnavailableParams(id.name(), RE04);
        }
    }

    private enum TelephonyParamIdentifiers {
        A001, A002, A003, A004, A005, A006, A007, A008, A009, A010, A011, A012, A013, A014, A015,
        A016, A017, A018, A019, A020, A021, A022, A023, A024, A025, A026, A027, A138, A139, A140,
        A141, A142, A143, A144, A145
    }
}