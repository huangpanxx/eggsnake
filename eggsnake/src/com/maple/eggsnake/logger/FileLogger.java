package com.maple.eggsnake.logger;

import java.io.File;
import java.io.FileOutputStream;

public class FileLogger extends LoggerBase {

	@Override
	public void writeMessage(String msg) {
		try {
			File f = new File("EggSnake.log");
			FileOutputStream fs = new FileOutputStream(f);
			fs.write(msg.getBytes("utf-8"));
			fs.close();
		} catch (Exception e) {
			System.out
					.println(String.format("FileLogger:$1%s", e.getMessage()));
		}
	}
}
