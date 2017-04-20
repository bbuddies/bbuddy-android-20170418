package com.odde.bbuddy.license.viewmodel;


import android.util.Log;

import com.odde.bbuddy.common.Consumer;
import com.odde.bbuddy.di.scope.ActivityScope;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Lazy;

import static com.odde.bbuddy.license.viewmodel.QueryDate.transferToDate;

@PresentationModel
@ActivityScope
public class QueryAmountController implements HasPresentationModelChangeSupport {

	private String startDate;
	private String endDate;

	private String total;

	private QueryDate date;

	private final Lazy<PresentationModelChangeSupport> changeSupportLazyLoader;

	@Override
	public PresentationModelChangeSupport getPresentationModelChangeSupport() {
		return changeSupportLazyLoader.get();
	}

	@Inject
	public QueryAmountController(QueryDate date, @Named("total") Lazy<PresentationModelChangeSupport> changeSupportLazyLoader) {
		this.changeSupportLazyLoader = changeSupportLazyLoader;
		this.date = date;
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

	public String getTotal() {
		return total;
	}

	public void setTotal(String t) {
		this.total = t;
	}

	public void getAmount() {
		Log.d("nan", "getAmount() " + startDate + "/" + endDate);

		date.calculateAmount(transferToDate(startDate), transferToDate(endDate), new Consumer<Integer>(){
			@Override
			public void accept(Integer totalAmount) {
				setTotal(totalAmount.toString());
				changeSupportLazyLoader.get().refreshPresentationModel();
				Log.d("nan", "total: " + total);
			}
		});

	}
}
