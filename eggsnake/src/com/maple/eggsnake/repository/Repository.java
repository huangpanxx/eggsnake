/** 
 * @description	: This class provides static methods to persist an object of a class
 * @author		: 黄攀
 * @created		: 2012-1-4
 */

package com.maple.eggsnake.repository;

import java.io.File;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.maple.eggsnake.logger.DefaultLogger;

public class Repository {
	/**
	 * 如果读取失败则返回默认构造函数构造的实例,如果构造失败则抛出异常
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static <T> T fetch(Class<? extends T> cls) throws Exception {
		String className = cls.getName();
		File f = new File(className);
		Serializer sr = new Persister();
		T obj = null;
		try {
			obj = sr.read(cls, f);
		} catch (Exception e) {
			DefaultLogger.getDefaultLogger().log(
					"读取对象:%1$s失败(%2$s),尝试调用默认构造函数", className, e.getMessage());
		}
		if (obj == null) {
			try {
				obj = cls.getConstructor().newInstance();
			} catch (Exception ex) {
				String err = String.format("构造对象%1$s失败", className);
				DefaultLogger.getDefaultLogger().log(err);
				throw new Exception(err);
			}
		}
		return obj;
	}

	/**
	 * 根据类名进行保存
	 * @param obj
	 * @throws Exception
	 */
	public static void save(Object obj) throws Exception {
		String fileName = obj.getClass().getName();
		File f = new File(fileName);
		Serializer sr = new Persister();
		sr.write(obj, f);
	}
}
