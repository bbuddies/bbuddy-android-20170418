package com.odde.bbuddy.subscribe.viewmodel;

import android.util.Log;

import com.odde.bbuddy.common.Consumer;
import com.odde.bbuddy.di.scope.ActivityScope;
import com.odde.bbuddy.subscribe.api.SubscribeApi;
import com.odde.bbuddy.subscribe.view.AddSubscribeActivity;

import org.robobinding.annotation.PresentationModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aaronchu on 2017/4/19.
 */

@PresentationModel
@ActivityScope
public class EditableSubscribe {

	private SubscribeApi mSubscribeApi;
	private String userStartDate;
	private String userEndDate;
	private AddSubscribeActivity mCallbacks;

	@Inject
	public EditableSubscribe(SubscribeApi subscribeApi) {
		mSubscribeApi = subscribeApi;
	}

	public void calculate() {
		mSubscribeApi.getLicensePlan(new SubscriptionPlanCallbacks() {
			@Override
			public void onResponse(Call<List<LicensePlan>> call, Response<List<LicensePlan>> response) {
				Log.d("Aaron", "onResponse:: call: " + call + ", response: " + response);
				for (LicensePlan licensePlan : response.body()) {
					Log.w("Aaron", licensePlan.toString());
				}
				try {
					mCallbacks.showResult(calculateSubscriptionPlan(response.body()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Call<List<LicensePlan>> call, Throwable t) {
				Log.d("Aaron", "onFailure:: call: " + call + ", t: " + t);
			}
		});
	}

	private String calculateSubscriptionPlan(List<LicensePlan> licensePlans) throws ParseException {
		Map<String, Integer> yearMonthAmountMap = new HashMap<>(licensePlans.size());
		for (LicensePlan licensePlan : licensePlans) {
			yearMonthAmountMap.put(licensePlan.getMonth(), licensePlan.getAmount());
		}

//		"2017-03-06" "2017-05-09"
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		Date startDate = dateFormat.parse(userStartDate);
		Date endDate = dateFormat.parse(userEndDate);
		startDate.getDay()
		Log.d("Aaron", "startDate: " + startDate.toString());
		Log.d("Aaron", "endDate: " + endDate.toString());
		if (isTheSameYearAndMonth(startDate, endDate)) {
			return String.format(Locale.getDefault(), "%.02f", calculateAmountWithinTheSameMonth(yearMonthAmountMap, startDate, endDate));
		}
		return String.format(Locale.getDefault(), "%.02f", calculateAmountWithoutWithinTheSameMonth(yearMonthAmountMap, startDate, endDate, dateFormat));
	}

	private float calculateAmountWithoutWithinTheSameMonth(Map<String, Integer> yearMonthAmountMap, Date startDate, Date endDate, SimpleDateFormat dateFormat) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		// Start month
		calendar.setTime(startDate);
		calculateStartMonthAmount(yearMonthAmountMap, calendar, dateFormat);
		// End month
		calendar.setTime(endDate);
		calculateEndMonthAmount(yearMonthAmountMap, calendar, dateFormat);
		// Middle month
		float totalAmount = 0f;
		calendar.setTime(startDate);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.add(Calendar.MONTH, 1);
		while (calendar.getTime().compareTo(endDate) < 0) {
			totalAmount += calculateMiddleMonthAmount(yearMonthAmountMap, calendar, dateFormat);
			calendar.add(Calendar.MONTH, 1);
		}
		return totalAmount;
	}

	private boolean isTheSameYearAndMonth(Date startDate, Date endDate) {
		if (startDate.getYear() == endDate.getYear() && startDate.getMonth() == endDate.getMonth()) {
			return true;
		}
		return false;
	}

	public float calculateAmountWithinTheSameMonth(Map<String, Integer> yearMonthAmountMap, Date startDate, Date endDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM", Locale.getDefault());
		String yearMonth = dateFormat.format(startDate);
		int monthAmount = yearMonthAmountMap.get(yearMonth) != null ? yearMonthAmountMap.get(yearMonth) : 0;
		long days = (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000L);
		Log.e("Aaron", "days: " + days);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		Log.e("Aaron", "max days:" + calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Log.e("Aaron", "monthAmount: " + monthAmount);
		return 1f * days / calendar.getActualMaximum(Calendar.DAY_OF_MONTH) * monthAmount;
	}

	private float calculateMiddleMonthAmount(Map<String, Integer> yearMonthAmountMap, Calendar calendar, SimpleDateFormat dateFormat) throws ParseException {
		String currentDate = dateFormat.format(calendar.getTime());
		String yearMonth = currentDate.substring(0, currentDate.lastIndexOf("-"));
		return yearMonthAmountMap.get(yearMonth) != null ? yearMonthAmountMap.get(yearMonth) : 0;
	}

	private float calculateStartMonthAmount(Map<String, Integer> yearMonthAmountMap, Calendar calendar, SimpleDateFormat dateFormat) throws ParseException {
		String currentDate = dateFormat.format(calendar.getTime());
		String yearMonth = currentDate.substring(0, currentDate.lastIndexOf("-"));
		int monthAmount = yearMonthAmountMap.get(yearMonth) != null ? yearMonthAmountMap.get(yearMonth) : 0;
		int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return 1f * (maxDays - calendar.get(Calendar.DAY_OF_MONTH) + 1) / maxDays * monthAmount;
	}

	private float calculateEndMonthAmount(Map<String, Integer> yearMonthAmountMap, Calendar calendar, SimpleDateFormat dateFormat) throws ParseException {
		String currentDate = dateFormat.format(calendar.getTime());
		String yearMonth = currentDate.substring(0, currentDate.lastIndexOf("-"));
		int monthAmount = yearMonthAmountMap.get(yearMonth) != null ? yearMonthAmountMap.get(yearMonth) : 0;
		int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return 1f * calendar.get(Calendar.DAY_OF_MONTH) / maxDays * monthAmount;
	}

	public String getUserStartDate() {
		return userStartDate;
	}

	public void setUserStartDate(String userStartDate) {
		this.userStartDate = userStartDate;
	}

	public String getUserEndDate() {
		return userEndDate;
	}

	public void setUserEndDate(String userEndDate) {
		this.userEndDate = userEndDate;
	}


	public void setCallbacks(AddSubscribeActivity callbacks) {
		this.mCallbacks = callbacks;
	}

	public interface SubscriptionPlanCallbacks {
		void onResponse(Call<List<LicensePlan>> call, retrofit2.Response<List<LicensePlan>> response);

		void onFailure(Call<List<LicensePlan>> call, Throwable t);
	}

}
