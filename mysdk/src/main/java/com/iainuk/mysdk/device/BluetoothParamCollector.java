package com.iainuk.mysdk.device;

import static android.Manifest.permission.BLUETOOTH;
import static android.Manifest.permission.BLUETOOTH_CONNECT;
import static com.iainuk.mysdk.device.DPNAEnum.RE03;
import static com.iainuk.mysdk.device.DPNAEnum.RE04;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

final class BluetoothParamCollector extends ParamCollector {
    static void addBluetoothAdapterFields(ParamAccumulator accumulator) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        addA039(accumulator, bluetoothAdapter);
        addA040(accumulator, bluetoothAdapter);
        addA041(accumulator, bluetoothAdapter);
        addA149(accumulator, bluetoothAdapter);
    }

    @SuppressLint("HardwareIds")
    private static void addA039(ParamAccumulator accumulator, BluetoothAdapter bluetoothAdapter) {
        if (canNotAccessBluetooth(accumulator.getContext())) {
            accumulator.addToUnavailableParams("A039", RE03);
            return;
        }

        accumulator.addToDeviceData("A039", bluetoothAdapter.getAddress());
    }

    @SuppressLint("MissingPermission")
    private static void addA040(ParamAccumulator accumulator, BluetoothAdapter bluetoothAdapter) {
        if (canNotAccessBluetooth(accumulator.getContext())) {
            accumulator.addToUnavailableParams("A040", RE03);
            return;
        }

        Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
        List<String> addresses = new ArrayList<>();
        for (BluetoothDevice device : devices) {
            addresses.add(device.getAddress());
        }
        accumulator.addToDeviceData("A040", TextUtils.join(",", addresses));
    }

    private static void addA041(ParamAccumulator accumulator, BluetoothAdapter bluetoothAdapter) {
        if (Build.VERSION.SDK_INT <= 30 && !hasPermission(BLUETOOTH, accumulator.getContext())) {
            accumulator.addToUnavailableParams("A041", RE03);
            return;
        }

        accumulator.addToDeviceData("A041", String.valueOf(bluetoothAdapter.isEnabled()));
    }

    @SuppressLint("MissingPermission")
    private static void addA149(ParamAccumulator accumulator, BluetoothAdapter bluetoothAdapter) {
        if (Build.VERSION.SDK_INT < 30) {
            accumulator.addToUnavailableParams("A149", RE04);
            return;
        }

        if (canNotAccessBluetooth(accumulator.getContext())) {
            accumulator.addToUnavailableParams("A149", RE03);
            return;
        }

        Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
        List<String> addresses = new ArrayList<>();
        for (BluetoothDevice device : devices) {
            addresses.add(device.getAlias());
        }
        accumulator.addToDeviceData("A149", TextUtils.join(",", addresses));
    }

    private static boolean canNotAccessBluetooth(Context context) {
        if (Build.VERSION.SDK_INT > 30) {
            return !hasPermission(BLUETOOTH_CONNECT, context);
        } else {
            return !hasPermission(BLUETOOTH, context);
        }
    }
}