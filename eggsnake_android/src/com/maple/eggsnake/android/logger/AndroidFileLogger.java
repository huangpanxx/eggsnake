/** 
 * @description	: Log to file
 * @author		: 黄攀
 * @created		: 2012-1-3
 */

package com.maple.eggsnake.android.logger;

import java.io.FileOutputStream;

import android.app.Activity;

import com.maple.eggsnake.logger.LoggerBase;

public class AndroidFileLogger extends LoggerBase {
	Activity activity;

	public AndroidFileLogger(Activity activity) {
		this.activity = activity;
		try {
			activity.deleteFile("eggsnake.log");
		} catch (Exception e) {

		}
	}

	@Override
	public void writeMessage(String msg) {
		try {
			FileOutputStream fs = this.activity.openFileOutput("eggsnake.log",
					Activity.MODE_APPEND);
			fs.write(msg.getBytes("utf-8"));
			fs.write("\n".getBytes());
			fs.close();
		} catch (Exception e) {
			System.out
					.println(String.format("FileLogger:$1%s", e.getMessage()));
		}
	}
}
