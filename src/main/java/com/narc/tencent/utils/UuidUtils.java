package com.narc.tencent.utils;

import java.util.UUID;

/**
 * UUID工具类
 * @author : Narcssus
 * @date : 2020/11/26 22:20
 */
public class UuidUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
