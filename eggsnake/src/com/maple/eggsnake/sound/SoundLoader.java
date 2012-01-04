/**
 * @description	: load a sound 
 * @author			: 万剑
 * @created		: 2012-1-3
 */
package com.maple.eggsnake.sound;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundLoader {

	public static ArrayList<String> soundList = new ArrayList<String>();
	public static final String rootPath = "data/sounds/";

	/**
	 * 通过音乐的路径加载音乐(只支持WAV,MP3,OGG格式) 用于音效加载
	 * 
	 * @param name
	 * @return
	 */
	public static Sound loadSound(String name) {
		String path = findPath(name);
		return Gdx.audio.newSound(Gdx.files.internal(path));
	};

	/**
	 * 通过音乐的路径加载音乐（只支持WAV，Mp3，OGG格式） 用于背景音乐的加载
	 * 
	 * @param name
	 * @return
	 */
	public static Music loadMusic(String name) {
		String path = findPath(name);
		return Gdx.audio.newMusic(Gdx.files.internal(path));
	}

	/**
	 * 通过音乐名查找文件所在位置(音乐名要注明格式)
	 * 
	 * @param name	文件名
	 *            
	 * @return 文件所在位置
	 */
	public static String findPath(String name) {

		String path = "";
		for (Iterator<String> i = soundList.iterator(); i.hasNext();) {
			path = (String) i.next();
			if (path.endsWith(name))
				return path;
		}

		listFile(new File(rootPath), name);
		path = soundList.get(soundList.size() - 1);
		return path;
	}

	/**
	 * 通过文件名和给定的一个目录索引该目录及子目录下的所有文件 并将文件的路径保存在soundList中
	 * 
	 * @param file 文件目录名	
	 * @param name 文件名
	 * @return
	 */
	public static void listFile(File file, String name) {

		if (file.isFile()) {
			// 是完整的文件夹内文件的路径
			// 找到文件
			if (file.getName().matches(name)) {
				soundList.add(file.getPath());
			}
		} else {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				listFile(files[i], name);
			}
		}
	}

}
