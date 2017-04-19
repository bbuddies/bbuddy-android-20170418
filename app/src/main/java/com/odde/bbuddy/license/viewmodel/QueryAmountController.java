package com.odde.bbuddy.license.viewmodel;


import android.text.format.DateUtils;
import android.util.Log;

import com.odde.bbuddy.common.Consumer;
import com.odde.bbuddy.di.scope.ActivityScope;

import org.robobinding.annotation.PresentationModel;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.inject.Inject;

import static com.odde.bbuddy.license.viewmodel.QueryDate.transferToDate;

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
//		QueryDate date = new QueryDate(startDate, endDate);
//		date.isDateValid(date.getStartDate());
//		date.isDateValid(date.getEndDate());
//
//
//		Date startDate = date.transferToDate(date.getStartDate());
//
//		Calendar c = toCalendar(startDate);
//		Log.d("nan", "day: " + c.get(Calendar.DAY_OF_MONTH));


//		date.calculateAmount(new Date(2017, 2, 10), new Date(2017, 2, 14), );
//		Log.d("nan", "amount: " + amount);
//		calculateAmount();

//		calculateAmount(transferToDate(startDate), transferToDate(endDate), new Consumer<Integer>(){
//
//		});
	}

	public static Calendar toCalendar(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
}
