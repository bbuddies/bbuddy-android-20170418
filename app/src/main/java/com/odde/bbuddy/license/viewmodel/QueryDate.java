package com.odde.bbuddy.license.viewmodel;

import android.text.format.DateUtils;
import android.util.Log;

import com.odde.bbuddy.common.Consumer;
import com.odde.bbuddy.license.api.LicenseApi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryDate {

	private String startDate;
	private String endDate;
	private LicenseApi licenseApi;

	public QueryDate(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public QueryDate(LicenseApi licenseApi) {

		this.licenseApi = licenseApi;
	}

	public static boolean isDateValid(String dateStr) {
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

	public static Date transferToDate(String dateStr) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = formatter.parse(dateStr);
			Log.d("nan", "date: " + date.toString());
			return date;
		} catch(ParseException e) {
			Log.d("nan", "Exception: " + e.getLocalizedMessage());
			return null;
		}
	}

	private final int[] AMOUNT_OF_MONTH = {0, 31, 28, 0, 30, 31, 0, 31, 31, 0 ,0 ,0, 0};

	public void calculateAmount(final Date startDate, final Date endDate, final Consumer<Integer> consumer) {
		licenseApi.processAllLicenses(new Consumer<List<License>>() {
			@Override
			public void accept(List<License> licenses) {
				int amount = 0;
				long startMillis = startDate.getTime();
				long endMillis = endDate.getTime();
				Calendar c = Calendar.getInstance();
				HashMap<String, Integer> licenseMap = transferLicense(licenses);
				while (endMillis >= startMillis) {
					c.setTimeInMillis(startMillis);
					int amountOfMonth = licenseMap.get(new SimpleDateFormat("yyyy-MM").format(new Date(startMillis))) == null ? 0 : licenseMap.get(new SimpleDateFormat("yyyy-MM").format(new Date(startMillis)));
					amountOfMonth /= c.getActualMaximum(Calendar.DAY_OF_MONTH);
					amount += amountOfMonth;
					startMillis += DateUtils.DAY_IN_MILLIS;
				}
				consumer.accept(amount);
			}
		});
	}

	public HashMap<String, Integer> transferLicense(List<License> licenses) {
		HashMap<String, Integer> licenseMap = new HashMap<>();

		for (int i=0; i<licenses.size(); ++i) {
			License l = licenses.get(i);
			licenseMap.put(l.getMonth(), Integer.parseInt(l.getAmount()));
		}
		return licenseMap;
	}
}
