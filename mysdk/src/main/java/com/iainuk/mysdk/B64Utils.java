package com.iainuk.mysdk;

import android.util.Base64;

public class B64Utils {
    public static byte[] urlEncode(byte[] bytes) {
        return Base64.encode(bytes, Base64.URL_SAFE | Base64.NO_PADDING | Base64.NO_WRAP);
    }
}
