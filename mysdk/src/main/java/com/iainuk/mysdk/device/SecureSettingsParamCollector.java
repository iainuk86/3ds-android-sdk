package com.iainuk.mysdk.device;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.REQUEST_INSTALL_PACKAGES;
import static android.content.Context.LOCATION_SERVICE;
import static android.provider.Settings.Secure.*;
import static com.iainuk.mysdk.device.DPNAEnum.RE02;
import static com.iainuk.mysdk.device.DPNAEnum.RE03;
import static com.iainuk.mysdk.device.DPNAEnum.RE04;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;

import java.util.List;

final class SecureSettingsParamCollector extends ParamCollector {
    static void addSecureSettingsParams(ParamAccumulator accumulator) {
        ContentResolver resolver = accumulator.getContext().getContentResolver();

        addA065(accumulator, resolver);
        addA066(accumulator, resolver);
        addA067(accumulator, resolver);
        addA068(accumulator, resolver);
        addA069(accumulator, resolver);
        // A070 is in Global Settings
        addA071(accumulator, resolver);
        // A072 is in Global Settings
        addA073(accumulator, resolver);
        addA074(accumulator, resolver);
        addA075(accumulator, resolver);
        addA076(accumulator);
        addA077(accumulator, resolver);
        addA078(accumulator, resolver);
        addA079(accumulator);
        addA080(accumulator, resolver);
        addA081(accumulator, resolver);
        addA082(accumulator, resolver);
        addA083(accumulator, resolver);
        addA150(accumulator, resolver);
        addA151(accumulator, resolver);
    }

    private static void addA065(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A065",
                Settings.Secure.getString(resolver, ACCESSIBILITY_DISPLAY_INVERSION_ENABLED));
    }

    private static void addA066(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A066",
                Settings.Secure.getString(resolver, ACCESSIBILITY_ENABLED));
    }

    private static void addA067(ParamAccumulator accumulator, ContentResolver resolver) {
        if (Build.VERSION.SDK_INT < 26) {
            accumulator.addToDeviceData("A067",
                    Settings.Secure.getString(resolver, ACCESSIBILITY_SPEAK_PASSWORD));
        } else {
            accumulator.addToUnavailableParams("A067", RE04);
        }
    }

    private static void addA068(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A068",
                Settings.Secure.getString(resolver, ALLOWED_GEOLOCATION_ORIGINS));
    }

    @SuppressLint("HardwareIds")
    private static void addA069(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A069",
                Settings.Secure.getString(resolver, ANDROID_ID));
    }

    private static void addA071(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A071",
                Settings.Secure.getString(resolver, DEFAULT_INPUT_METHOD));
    }

    private static void addA073(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A073",
                Settings.Secure.getString(resolver, ENABLED_ACCESSIBILITY_SERVICES));
    }

    private static void addA074(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A074",
                Settings.Secure.getString(resolver, ENABLED_INPUT_METHODS));
    }

    private static void addA075(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A075",
                Settings.Secure.getString(resolver, INPUT_METHOD_SELECTOR_VISIBILITY));
    }

    private static void addA076(ParamAccumulator accumulator) {
        if (Build.VERSION.SDK_INT >= 29) {
            if (!hasPermission(REQUEST_INSTALL_PACKAGES, accumulator.getContext())) {
                accumulator.addToUnavailableParams("A076", RE03);
                return;
            }

            accumulator.addToDeviceData("A076", String.valueOf(
                    accumulator.getContext().getPackageManager().canRequestPackageInstalls()));
        } else {
            accumulator.addToUnavailableParams("A076", RE04);
        }
    }

    private static void addA077(ParamAccumulator accumulator, ContentResolver resolver) {
        if (Build.VERSION.SDK_INT < 28) {
            accumulator.addToDeviceData("A077",
                    Settings.Secure.getString(resolver, LOCATION_MODE));
            return;
        }

        if (!hasAnyPermission(List.of(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION),
                accumulator.getContext())) {
            accumulator.addToUnavailableParams("A077", RE03);
            return;
        }

        try {
            LocationManager lm = (LocationManager)
                    accumulator.getContext().getSystemService(LOCATION_SERVICE);
            accumulator.addToDeviceData("A077", String.valueOf(lm.isLocationEnabled()));
        } catch (Exception e) {
            accumulator.addToUnavailableParams("A077", RE04);
        }
    }

    private static void addA078(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A078",
                Settings.Secure.getString(resolver, SKIP_FIRST_USE_HINTS));
    }

    private static void addA079(ParamAccumulator accumulator) {
        accumulator.addToUnavailableParams("A079", RE02);
    }

    private static void addA080(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A080",
                Settings.Secure.getString(resolver, TTS_DEFAULT_PITCH));
    }

    private static void addA081(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A081",
                Settings.Secure.getString(resolver, TTS_DEFAULT_RATE));
    }

    private static void addA082(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A082",
                Settings.Secure.getString(resolver, TTS_DEFAULT_SYNTH));
    }

    private static void addA083(ParamAccumulator accumulator, ContentResolver resolver) {
        accumulator.addToDeviceData("A083",
                Settings.Secure.getString(resolver, TTS_ENABLED_PLUGINS));
    }

    private static void addA150(ParamAccumulator accumulator, ContentResolver resolver) {
        if (Build.VERSION.SDK_INT >= 28) {
            accumulator.addToDeviceData("A150",
                    Settings.Secure.getString(resolver, RTT_CALLING_MODE));
        } else {
            accumulator.addToUnavailableParams("A150", RE04);
        }
    }

    private static void addA151(ParamAccumulator accumulator, ContentResolver resolver) {
        if (Build.VERSION.SDK_INT >= 30) {
            accumulator.addToDeviceData("A151",
                    Settings.Secure.getString(resolver, SECURE_FRP_MODE));
        } else {
            accumulator.addToUnavailableParams("A151", RE04);
        }
    }
}