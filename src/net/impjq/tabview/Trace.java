package net.impjq.tabview;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.util.Log;

public class Trace {

	private static final String TAG = "TabView";
	private static final String FILE_NAME = "data/data/net.impjq.tabview/log.txt";
	private static final int MaxBufferSize = 8 * 1024;
	public static boolean DEBUG_ON=true;

	// Debug Info

	public static void d(String sMessage) {
		if (DEBUG_ON) {
			d(TAG, sMessage);
		}
	}

	public static void d(String sTag, String sMessage) {
		if (DEBUG_ON) {
			if (null != sMessage) {
				Log.d(sTag, sMessage);
			}
		}
	}

	// Warning Info
	public static void w(String sTag, String sMessage) {
		if (DEBUG_ON) {
			if (null != sMessage) {
				Log.w(sTag, sMessage);
			}
		}
	}

	// Error Info
	public static void e(String sMessage) {
		if (DEBUG_ON) {
			if (null != sMessage) {
				e(TAG, sMessage);
			}
		}
	}

	public static void e(String sTag, String sMessage) {
		if (DEBUG_ON) {
			if (null != sMessage) {
				Log.e(sTag, sMessage);
			}
		}
	}

	public static void toFile(byte[] traceInfo) {
		if (DEBUG_ON) {
			File file = new File(FILE_NAME);
			try {
				file.createNewFile();
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
						new FileOutputStream(file, true), MaxBufferSize);
				bufferedOutputStream.write(traceInfo);
				traceInfo = null;
				bufferedOutputStream.close();
			} catch (IOException e) {
				Trace.d(e.getMessage());
			}
		}
	}

}
