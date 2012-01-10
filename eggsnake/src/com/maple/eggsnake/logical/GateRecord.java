package com.maple.eggsnake.logical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.google.gson.Gson;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.util.FileHelper;

public class GateRecord {

	public final static int ITEMCAPACITY = 5;

	private static String getSaveDir(int index) {
		return String.format("gate%1$d", index);
	}

	/**
	 * 获取关卡记录 传入关卡序号
	 * 
	 * @param index
	 * @return
	 */
	public static GateRecord fetchGateRecord(int index) {
		try {
			String data = FileHelper.readAll(GateRecord.getSaveDir(index));
			Gson gson = new Gson();
			return gson.fromJson(data, GateRecord.class);
		} catch (Exception e) {
			return new GateRecord(index);
		}
	}

	/**
	 * 保存关卡 传入关卡
	 * 
	 * @param record
	 * @throws Exception
	 */
	public static void saveGateRecord(GateRecord record) throws Exception {
		String path = GateRecord.getSaveDir(record.getGate());
		Gson gson = new Gson();
		String data = gson.toJson(record);
		FileHelper.writeAll(path, data, false);
		if (record.getGate() > GateRecord.getMaxGateNumber()) {
			GateRecord.setMaxGateNumber(record.getGate());
		}
	}

	/**
	 * 获取保存过的最大的关卡号
	 * 
	 * @return
	 */
	public static int getMaxGateNumber() {
		try {
			return Integer.parseInt(FileHelper.readAll("gateNumber"));
		} catch (Exception e) {
			DefaultLogger.getDefaultLogger().logWithSignature("GateRecord",
					e.getMessage());
			return -1;
		}
	}

	/**
	 * 获取关卡近期记录
	 * 
	 * @param index
	 * @return
	 */
	public GateRecordItem getRecordItem(int index) {
		this.checkSort();
		return this.items.get(index);
	}

	/**
	 * 获取关卡近期记录数量
	 * 
	 * @return
	 */
	public int getSize() {
		return this.items.size();
	}

	/**
	 * 获取关卡号
	 * 
	 * @return
	 */
	public int getGate() {
		return gate;
	}

	/**
	 * 保存关卡
	 * 
	 * @throws Exception
	 */
	public void save() throws Exception {
		GateRecord.saveGateRecord(this);
	}

	/**
	 * 添加关卡记录
	 * 
	 * @param item
	 */
	public void addRecordItem(GateRecordItem item) {
		this.checkSort();
		while (this.getSize() >= GateRecord.ITEMCAPACITY) {
			this.items.remove(0);
		}
		this.items.add(item);
	}

	private static void setMaxGateNumber(int size) {
		try {
			FileHelper.writeAll("gateNumber", Integer.toString(size), false);
		} catch (Exception e) {
			DefaultLogger.getDefaultLogger().logWithSignature("GateRecord",
					e.getMessage());
		}
	}

	private void setGate(int index) {
		this.gate = index;
	}

	private GateRecord() {
		this.setGate(0);
		this.items = new ArrayList<GateRecordItem>();
	}

	private GateRecord(int gate) {
		this.setGate(gate);
		this.items = new ArrayList<GateRecordItem>();
	}

	private int gate;
	private ArrayList<GateRecordItem> items;

	private boolean sorted = false;

	private void checkSort() {
		if (!this.sorted) {
			if (this.items == null)
				this.items = new ArrayList<GateRecordItem>();
			Collections.sort(items, new Comparator<GateRecordItem>() {
				@Override
				public int compare(GateRecordItem record1,
						GateRecordItem record2) {
					return record1.dateTime.compareTo(record2.dateTime);

				}
			});
		}
	}

}
