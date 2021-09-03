#include <stdio.h>
#include <string.h>
#include <jni.h>
#include <stdlib.h>

jint JNI_OnLoad(JavaVM *vm, void *reserved) {
	JNIEnv *env = NULL;

    if ((*vm)->GetEnv(vm, (void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    }
	return JNI_VERSION_1_6;
}

void JNI_OnUnload(JavaVM *vm, void *reserved) {

}

