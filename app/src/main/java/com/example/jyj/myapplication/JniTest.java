package com.example.jyj.myapplication;
// com.example.jyj.myapplication.JniTest;

import android.util.Log;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class JniTest {
    private static final String TAG = "JniTest";
    //静态方法获取静态变量
    public static String key = "hello";
    public String key2 = "hello ";

    static {
        System.loadLibrary("JniLib");
    }
    public native static String getKeyFromC();

    //非静态方法获取 静态变量
    public native String getKeyFromCC();

    //访问  方法
    public native int accessMethod();

    //UUID  访问静态方法
    public native void accessStaticMethod();

    //访问构造方法
    public native Date accessConstruct();

    //访问子类、父类的方法
    public native void accessNonvirtualMethod();

    //中文显示问题
    public native String accessChinese();

    //传入数组 进行排序
    public native int[] accessIntArray(int[] array);

    //c 创建数组 返回给java
    public native int[] accessReturnArray(int len);

    //
//    //通过获取 key3 变量 来报异常
    public native void accessCatch();

    //缓存策略
    public native void cacheStrategy();



    public int getRandom(int range) {
        Random random = new Random();
        return random.nextInt(range);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
