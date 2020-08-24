//
// Created by KC-14 on 2018/12/15.
//

#include "com_example_jyj_myapplication_JniTest.h"

JNIEXPORT jstring JNICALL Java_com_example_jyj_myapplication_JniTest_getKeyFromC
        (JNIEnv * env, jclass jcls) {
    return (*env)->NewStringUTF(env,"hello world");
};