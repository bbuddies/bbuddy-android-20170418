package com.odde.bbuddy.license.viewmodel;


import android.util.Log;

import com.odde.bbuddy.di.scope.ActivityScope;

import org.robobinding.annotation.PresentationModel;

import javax.inject.Inject;

@PresentationModel
@ActivityScope
public class QueryAmountController {

	private String startDate;
	private String endDate;

	@Inject
	public QueryAmountController() {

	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void getAmount() {
		Log.d("nan", "getAmount() " + startDate + "/" + endDate);
		QueryDate date = new QueryDate(startDate, endDate);
		date.isDateValid(date.getStartDate());
		date.isDateValid(date.getEndDate());
	}
}
