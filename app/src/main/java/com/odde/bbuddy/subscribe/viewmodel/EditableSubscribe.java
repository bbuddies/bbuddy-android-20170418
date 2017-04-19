package com.odde.bbuddy.subscribe.viewmodel;

import com.odde.bbuddy.di.scope.ActivityScope;
import com.odde.bbuddy.license.api.License;
import com.odde.bbuddy.license.api.LicenseApi;
import com.odde.bbuddy.subscribe.api.SubscribeApi;

import org.robobinding.annotation.PresentationModel;

import javax.inject.Inject;

/**
 * Created by aaronchu on 2017/4/19.
 */

@PresentationModel
@ActivityScope
public class EditableSubscribe {

	private SubscribeApi mSubscribeApi;
	private String startDate;
	private String endDate;

	@Inject
	public EditableSubscribe(SubscribeApi subscribeApi) {
		mSubscribeApi = subscribeApi;
	}

	public void calculate() {
		mSubscribeApi.getLicenseFee(new Runnable() {
			@Override
			public void run() {
//				startDate
//				endDate
			}
		});
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
