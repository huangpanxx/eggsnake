package com.maple.eggsnake.logical;

import java.util.Calendar;
import java.util.Date;

public class GateRecordItem {

	public Date dateTime;
	public int score;

	public GateRecordItem(Date dateTime, int score) {
		this.score = score;
		this.dateTime = dateTime;
	}

	public GateRecordItem(int score) {
		this.score = score;
		this.dateTime = Calendar.getInstance().getTime();
	}
}
