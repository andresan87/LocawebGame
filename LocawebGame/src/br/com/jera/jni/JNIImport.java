package br.com.jera.jni;

public class JNIImport {
	static {
		System.loadLibrary("data");
	}
	public static native String getu();
	public static native String getl();
	public static native String getp();
	public static native String getc(String name, String score);
}
