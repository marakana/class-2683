#include <jni.h>

namespace com_cisco_fib {

	// Pure C
	static long fib(long n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		return fib(n - 1) + fib(n - 2);
	}

	// JNI wrapper
	static jlong fibN(JNIEnv *env, jclass clazz, jlong n) {
		return fib(n);
	}

	// Function table
	static JNINativeMethod method_table[] = {
			{ "fibN", "(J)J", (void *) fibN }
	};

}

using namespace com_cisco_fib;

extern "C" jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env;
    if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    } else {
        jclass clazz = env->FindClass("com/cisco/fib/FibLib");
        if (clazz) {
                env->RegisterNatives(clazz, method_table, sizeof(method_table) / sizeof(method_table[0]));
                env->DeleteLocalRef(clazz);
                return JNI_VERSION_1_6;
        } else {
                return -1;
        }
    }
}
