package com.odde.bbuddy.license.viewModel;

import com.odde.bbuddy.common.Consumer;
import com.odde.bbuddy.license.api.GetLicensesApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class CalculateFee {

	private int startDateMonthAmount;
	private int endDateMonthAmount;
	private List<Integer> amountsMonthBetween;
	private GetLicensesApi getLicensesApi;

	@Inject
	public CalculateFee(GetLicensesApi getLicensesApi) {
		this.amountsMonthBetween = new ArrayList<>();
		this.getLicensesApi = getLicensesApi;
	}

	public void calculate(final String startDateString, final String endDateString, final Consumer<Integer> consumer) {
		getLicensesApi.get(new Consumer<List<License>>() {
			@Override
			public void accept(List<License> licenses) {
				classifyAmount(startDateString, endDateString, licenses);
				int fee = getFeeMonthBetween() + getFeeAtStartAndEndMonth(startDateString, endDateString);
				consumer.accept(fee);
			}
		});
	}

	private int getFeeAtStartAndEndMonth(String startDateString, String endDateString) {
		String startStringTrimDay = trimDay(startDateString);
		String endStringTrimDay = trimDay(endDateString);

		Calendar startDateCal = toCalendar(startDateString);
		Calendar endDateCal = toCalendar(endDateString);
		int fee = 0;
		if (startStringTrimDay.equals(endStringTrimDay)) {
			fee += getPartialFeeInMonth(startDateMonthAmount,
					startDateCal.get(Calendar.DATE),
					endDateCal.get(Calendar.DATE),
					startDateCal.getActualMaximum(Calendar.DAY_OF_MONTH));
		} else {
			fee += getPartialFeeInMonth(startDateMonthAmount,
					startDateCal.get(Calendar.DATE),
					startDateCal.getActualMaximum(Calendar.DAY_OF_MONTH),
					startDateCal.getActualMaximum(Calendar.DAY_OF_MONTH));
			fee += getPartialFeeInMonth(endDateMonthAmount,
					1,
					endDateCal.get(Calendar.DATE),
					endDateCal.getActualMaximum(Calendar.DAY_OF_MONTH));
		}

		return fee;
	}

	private int getPartialFeeInMonth(int fee, int startDay, int endDay, int totalDay) {
		return (int) Math.ceil((double) fee * (endDay - startDay + 1) / totalDay);
	}

	protected Calendar toCalendar(String dateString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(dateFormat.parse(dateString));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendar;
	}

	private int getFeeMonthBetween() {
		int fee = 0;
		for (Integer amount : amountsMonthBetween) {
			fee += amount;
		}

		return fee;
	}

	protected void classifyAmount(String startDateString, String endDateString, List<License> allLicenses) {
		String startStringTrimDay = trimDay(startDateString);
		String endStringTrimDay = trimDay(endDateString);

		for (License license : allLicenses) {
			String licenseMonth = license.getMonth();
			if (startStringTrimDay.compareTo(licenseMonth) < 0
					&& licenseMonth.compareTo(endStringTrimDay) < 0) {
				amountsMonthBetween.add(license.getAmount());
			}

			if (startStringTrimDay.compareTo(licenseMonth) == 0) {
				startDateMonthAmount = license.getAmount();
			}

			if (endStringTrimDay.compareTo(licenseMonth) == 0) {
				endDateMonthAmount = license.getAmount();
			}
		}
	}

	private String trimDay(String date) {
		String[] split = date.split("-");
		return split[0] + "-" + split[1];
	}
}
