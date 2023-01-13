package com.iainuk.mysdk.device;

import static android.provider.Settings.System.*;

import static com.iainuk.mysdk.device.DPNAEnum.RE04;

import android.content.ContentResolver;
import android.os.Build;
import android.provider.Settings;

final class SystemSettingsParamCollector extends ParamCollector {
    static void addSystemSettingsParams(ParamAccumulator accumulator) {
        ContentResolver resolver = accumulator.getContext().getContentResolver();

        addA099(accumulator, resolver);
        addA100(accumulator, resolver);
        addA101(accumulator, resolver);
        addA102(accumulator, resolver);
        addA103(accumulator, resolver);
        addA104(accumulator, resolver);
        addA105(accumulator, resolver);
        addA106(accumulator, resolver);
        addA107(accumulator, resolver);
        addA108(accumulator, resolver);
        addA109(accumulator, resolver);
        addA110(accumulator, resolver);
        addA111(accumulator, resolver);
        addA112(accumulator, resolver);
        addA113(accumulator, resolver);
        addA114(accumulator, resolver);
        addA115(accumulator, resolver);
        addA116(accumulator, resolver);
        addA117(accumulator, resolver);
        addA118(accumulator, resolver);
        addA119(accumulator, resolver);
        addA120(accumulator, resolver);
        addA121(accumulator, resolver);
        addA122(accumulator, resolver);
        addA123(accumulator, resolver);
    }

    private static void addA099(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A099",
                Settings.System.getString(resolver, ACCELEROMETER_ROTATION));
    }

    private static void addA100(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A100",
                Settings.System.getString(resolver, BLUETOOTH_DISCOVERABILITY));
    }

    private static void addA101(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A101",
                Settings.System.getString(resolver, BLUETOOTH_DISCOVERABILITY_TIMEOUT));
    }

    private static void addA102(ParamAccumulator accumulator, ContentResolver resolver) {
        if (Build.VERSION.SDK_INT < 31) {
            accumulator.addToDeviceData("A102",
                    Settings.System.getString(resolver, DATE_FORMAT));
        } else {
            accumulator.addToDeviceData("A102",
                    Settings.System.getString(resolver, TIME_12_24));
        }
    }

    private static void addA103(ParamAccumulator accumulator, ContentResolver resolver) {
        if (Build.VERSION.SDK_INT >= 23) {
            accumulator.addToDeviceData("A103",
                    Settings.System.getString(resolver, DTMF_TONE_TYPE_WHEN_DIALING));
        } else {
            accumulator.addToUnavailableParams("A103", RE04);
        }
    }

    private static void addA104(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A104",
                Settings.System.getString(resolver, DTMF_TONE_WHEN_DIALING));
    }

    private static void addA105(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A105",
                Settings.System.getString(resolver, END_BUTTON_BEHAVIOR));
    }

    private static void addA106(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A106",
                Settings.System.getString(resolver, FONT_SCALE));
    }

    private static void addA107(ParamAccumulator accumulator, ContentResolver resolver) {
        if (Build.VERSION.SDK_INT < 33) {
            accumulator.addToDeviceData("A107",
                    Settings.System.getString(resolver, HAPTIC_FEEDBACK_ENABLED));
        } else {
            accumulator.addToUnavailableParams("A107", RE04);
        }
    }

    private static void addA108(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A108",
                Settings.System.getString(resolver, MODE_RINGER_STREAMS_AFFECTED));
    }

    private static void addA109(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A109",
                Settings.System.getString(resolver, NOTIFICATION_SOUND));
    }

    private static void addA110(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A110",
                Settings.System.getString(resolver, MUTE_STREAMS_AFFECTED));
    }

    private static void addA111(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A111",
                Settings.System.getString(resolver, RINGTONE));
    }

    private static void addA112(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A112",
                Settings.System.getString(resolver, SCREEN_BRIGHTNESS));
    }

    private static void addA113(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A113",
                Settings.System.getString(resolver, SCREEN_BRIGHTNESS_MODE));
    }

    private static void addA114(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A114",
                Settings.System.getString(resolver, SCREEN_OFF_TIMEOUT));
    }

    private static void addA115(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A115",
                Settings.System.getString(resolver, SOUND_EFFECTS_ENABLED));
    }

    private static void addA116(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A116",
                Settings.System.getString(resolver, TEXT_AUTO_CAPS));
    }

    private static void addA117(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A117",
                Settings.System.getString(resolver, TEXT_AUTO_PUNCTUATE));
    }

    private static void addA118(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A118",
                Settings.System.getString(resolver, TEXT_AUTO_REPLACE));
    }

    private static void addA119(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A119",
                Settings.System.getString(resolver, TEXT_SHOW_PASSWORD));
    }

    private static void addA120(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A120",
                Settings.System.getString(resolver, TIME_12_24));
    }

    private static void addA121(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A121",
                Settings.System.getString(resolver, USER_ROTATION));
    }

    private static void addA122(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A122",
                Settings.System.getString(resolver, VIBRATE_ON));
    }

    private static void addA123(ParamAccumulator accumulator, ContentResolver resolver) {
        if (Build.VERSION.SDK_INT < 23 || Build.VERSION.SDK_INT >= 33) {
            accumulator.addToUnavailableParams("A123", RE04);
        } else {
            accumulator.addToDeviceData("A123",
                    Settings.System.getString(resolver, VIBRATE_WHEN_RINGING));
        }
    }
}