package com.odde.bbuddy.license.viewmodel;


import com.odde.bbuddy.di.scope.ActivityScope;
import com.odde.bbuddy.license.api.LicenseApi;

import org.robobinding.annotation.PresentationModel;

import javax.inject.Inject;

@PresentationModel
@ActivityScope
public class AddLicenseController {

	private String month;
	private String amount;
	private LicenseApi api;

	@Inject
	public AddLicenseController(LicenseApi api) {
		this.api = api;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void add() {
		api.addLicense(new License(month, amount));
	}

}
