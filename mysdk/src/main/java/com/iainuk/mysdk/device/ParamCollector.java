package com.iainuk.mysdk.device;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import java.util.List;

public abstract class ParamCollector {
    static boolean hasAllPermissions(List<String> permissions, Context context) {
        for (String permission : permissions) {
            if (!hasPermission(permission, context)) return false;
        }

        return true;
    }

    static boolean hasAnyPermission(List<String> permissions, Context context) {
        for (String permission : permissions) {
            if (hasPermission(permission, context)) return true;
        }

        return false;
    }

    static boolean hasPermission(String permission, Context context) {
        return ActivityCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_GRANTED;
    }
}
