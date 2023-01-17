package com.iainuk.mysdk.device;

import static android.content.Context.AUDIO_SERVICE;
import static android.provider.Settings.Global.*;

import static com.iainuk.mysdk.device.DPNAEnum.RE04;

import android.content.ContentResolver;
import android.media.AudioManager;
import android.os.Build;
import android.provider.Settings;

final class GlobalSettingsParamCollector extends ParamCollector {
    static void addGlobalSettingsParams(ParamAccumulator accumulator) {
        ContentResolver resolver = accumulator.getContext().getContentResolver();

        addA070(accumulator, resolver);
        addA072(accumulator, resolver);
        addA084(accumulator, resolver);
        addA085(accumulator, resolver);
        addA086(accumulator, resolver);
        addA087(accumulator, resolver);
        addA088(accumulator, resolver);
        addA089(accumulator, resolver);
        addA090(accumulator, resolver);
        addA091(accumulator, resolver);
        addA092(accumulator, resolver);
        addA093(accumulator, resolver);
        addA094(accumulator, resolver);
        addA095(accumulator, resolver);
        addA096(accumulator, resolver);
        addA097(accumulator, resolver);
        addA098(accumulator, resolver);
        addA152(accumulator, resolver);
    }

    private static void addA070(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A070",
                Settings.Global.getString(resolver, DATA_ROAMING));
    }

    private static void addA072(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A072",
                Settings.Global.getString(resolver, DEVICE_PROVISIONED));
    }

    private static void addA084(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A084",
                Settings.Global.getString(resolver, ADB_ENABLED));
    }

    private static void addA085(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A085",
                Settings.Global.getString(resolver, AIRPLANE_MODE_RADIOS));
    }

    private static void addA086(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A086",
                Settings.Global.getString(resolver, ALWAYS_FINISH_ACTIVITIES));
    }

    private static void addA087(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A087",
                Settings.Global.getString(resolver, ANIMATOR_DURATION_SCALE));
    }

    private static void addA088(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A088",
                Settings.Global.getString(resolver, AUTO_TIME));
    }

    private static void addA089(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A089",
                Settings.Global.getString(resolver, AUTO_TIME_ZONE));
    }

    private static void addA090(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A090",
                Settings.Global.getString(resolver, DEVELOPMENT_SETTINGS_ENABLED));
    }

    private static void addA091(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A091",
                Settings.Global.getString(resolver, HTTP_PROXY));
    }

    private static void addA092(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A092",
                Settings.Global.getString(resolver, NETWORK_PREFERENCE));
    }

    private static void addA093(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A093",
                Settings.Global.getString(resolver, STAY_ON_WHILE_PLUGGED_IN));
    }

    private static void addA094(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A094",
                Settings.Global.getString(resolver, TRANSITION_ANIMATION_SCALE));
    }

    private static void addA095(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A095",
                Settings.Global.getString(resolver, USB_MASS_STORAGE_ENABLED));
    }

    private static void addA096(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A096",
                Settings.Global.getString(resolver, USE_GOOGLE_MAIL));
    }

    private static void addA097(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A097",
                Settings.Global.getString(resolver, WAIT_FOR_DEBUGGER));
    }

    private static void addA098(ParamAccumulator accumulator, ContentResolver resolver) {
        if (Build.VERSION.SDK_INT < 26) {
            accumulator.addToDeviceData("A098",
                    Settings.Global.getString(resolver, WIFI_NETWORKS_AVAILABLE_NOTIFICATION_ON));
        } else {
            accumulator.addToUnavailableParams("A098", RE04);
        }
    }

    private static void addA152(ParamAccumulator accumulator, ContentResolver resolver) {
        if (Build.VERSION.SDK_INT >= 33) {
            AudioManager audioManager = (AudioManager)
                    accumulator.getContext().getSystemService(AUDIO_SERVICE);

            accumulator.addToDeviceData("A152",
                    String.valueOf(audioManager.isRampingRingerEnabled()));
        } else if (Build.VERSION.SDK_INT >= 29) {
            accumulator.addToDeviceData("A152",
                    Settings.Global.getString(resolver, APPLY_RAMPING_RINGER));
        } else {
            accumulator.addToUnavailableParams("A152", RE04);
        }
    }
}