package com.maple.eggsnake.logger;

import java.io.File;
import java.io.FileOutputStream;

public class FileLogger implements Loggable {

	@Override
	public void log(String patten, Object... args) {
		this.log(0, patten, args);
	}

	@Override
	public void log(int level, String patten, Object... args) {
		try {
			String msg = String.format(patten, args);
			String out = String.format("Level %1$d\t:%2$s", level, msg);
			File f = new File("EggSnake.log");
			FileOutputStream fs = new FileOutputStream(f);
			fs.write(out.getBytes("utf-8"));
			fs.close();
		} catch (Exception e) {
			System.out
					.println(String.format("FileLogger:$1%s", e.getMessage()));
		}

	}

}
