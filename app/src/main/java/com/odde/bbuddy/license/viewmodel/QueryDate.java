package com.odde.bbuddy.license.viewmodel;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryDate {

	private String startDate;
	private String endDate;

	public QueryDate(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public boolean isDateValid(String dateStr) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = formatter.parse(dateStr);
			Log.d("nan", "date: " + date.toString());
		} catch(ParseException e) {
			Log.d("nan", "Exception: " + e.getLocalizedMessage());
			return false;
		}
		return true;
	}
}
