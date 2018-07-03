package com.open.coinnews.utils;

import java.util.zip.CRC32;


public class DeviceIdUtil {

    public static long getCRC32(String deviceId) {
        CRC32 crc32 = new CRC32();
        crc32.update(deviceId.getBytes());
        long tmp = crc32.getValue();
        return tmp;
    }

//    public static String getRC4Code(){
//        return RC4
//    }

}