#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_zjf_nicework_utils_JniUtil_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jint JNICALL
Java_com_zjf_nicework_utils_JniUtil_sum(
        JNIEnv *env,
        jobject, jint a, jint b) {
    return a + b;
}
