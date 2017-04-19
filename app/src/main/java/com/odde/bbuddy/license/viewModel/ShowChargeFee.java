package com.odde.bbuddy.license.viewModel;

import com.odde.bbuddy.di.scope.ActivityScope;
import com.odde.bbuddy.license.api.GetLicensesApi;

import org.robobinding.annotation.PresentationModel;

import javax.inject.Inject;

@PresentationModel
@ActivityScope
public class ShowChargeFee {

	private String startDate;
	private String endDate;

	private String totalAmount;

	GetLicensesApi getLicensesApi;

	@Inject
	public ShowChargeFee(GetLicensesApi getLicensesApi) {
		this.getLicensesApi = getLicensesApi;
	}

	public void charge() {
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

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
}
