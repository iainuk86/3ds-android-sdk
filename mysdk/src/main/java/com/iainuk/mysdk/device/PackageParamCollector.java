package com.iainuk.mysdk.device;

import static android.content.pm.PackageManager.GET_META_DATA;
import static com.iainuk.mysdk.device.DPNAEnum.RE04;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class PackageParamCollector extends ParamCollector {
    static void addPackageManagerParams(ParamAccumulator accumulator) {
        PackageManager packageManager = accumulator.getContext().getPackageManager();

        addA124(accumulator, packageManager);
        addA125(accumulator, packageManager);
        addA126(accumulator, packageManager);
        addA127(accumulator, packageManager);
        addA128(accumulator, packageManager);
    }

    private static void addA124(ParamAccumulator accumulator, PackageManager packageManager) {
        accumulator.addToDeviceData("A124", String.valueOf(packageManager.isSafeMode()));
    }

    @SuppressLint("QueryPermissionsNeeded")
    private static void addA125(ParamAccumulator accumulator, PackageManager packageManager) {
        List<ApplicationInfo> installedApps;
        if (Build.VERSION.SDK_INT >= 33) {
            installedApps = packageManager.getInstalledApplications(
                    PackageManager.ApplicationInfoFlags.of(GET_META_DATA));
        } else {
            installedApps = packageManager.getInstalledApplications(GET_META_DATA);
        }

        List<String> installedAppNames = new ArrayList<>();
        for (ApplicationInfo appInfo : installedApps) {
            installedAppNames.add(appInfo.packageName);
        }

        accumulator.addToDeviceData("A125", String.join(",", installedAppNames));
    }

    private static void addA126(ParamAccumulator accumulator, PackageManager packageManager) {
        String packageName = accumulator.getContext().getPackageName();
        if (Build.VERSION.SDK_INT >= 30) {
            try {
                accumulator.addToDeviceData("A126",
                        packageManager.getInstallSourceInfo(packageName).getInstallingPackageName());
            } catch (Exception e) {
                accumulator.addToUnavailableParams("A126", RE04);
            }
        } else {
            accumulator.addToDeviceData("A126",
                    packageManager.getInstallerPackageName(packageName));
        }
    }

    private static void addA127(ParamAccumulator accumulator, PackageManager packageManager) {
        accumulator.addToDeviceData("A127",
                Arrays.toString(packageManager.getSystemAvailableFeatures()));
    }

    private static void addA128(ParamAccumulator accumulator, PackageManager packageManager) {
        accumulator.addToDeviceData("A128",
                Arrays.toString(packageManager.getSystemSharedLibraryNames()));
    }
}