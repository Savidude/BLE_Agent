package org.wso2.bleagent.util.dto;

import android.content.ContentResolver;
import android.content.Context;
import android.telephony.TelephonyManager;

import java.util.UUID;

/**
 * Created by wso2123 on 11/9/16.
 */
public class AgentUtil {

    //http://stackoverflow.com/questions/2785485/is-there-a-unique-android-device-id
    public static String generateDeviceId(Context baseContext, ContentResolver contentResolver) {
        final TelephonyManager tm = (TelephonyManager) baseContext.getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = String.valueOf(tm.getDeviceId());
        tmSerial = String.valueOf(tm.getSimSerialNumber());
        androidId = String.valueOf(android.provider.Settings.Secure.getString(contentResolver, android.provider.Settings.Secure.ANDROID_ID));

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        return deviceUuid.toString();
    }
}
