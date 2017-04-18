package com.odde.bbuddy.account.viewmodel;

import com.odde.bbuddy.account.api.AddLicenseApi;
import com.odde.bbuddy.di.scope.ActivityScope;

import org.robobinding.annotation.PresentationModel;

import javax.inject.Inject;

@PresentationModel
@ActivityScope
public class AddLicense {
	private String month;
	private String amount;

	private AddLicenseApi addLicenseApi;

	@Inject
	public AddLicense(AddLicenseApi addLicenseApi) {
		this.addLicenseApi = addLicenseApi;
	}

	public String getMonth() {
		return month;
	}

	public String getAmount() {
		return amount;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void add() {
		License license = new License();
		license.setMonth(month);
		license.setAmount(Integer.parseInt(amount));

		addLicenseApi.addLicense(license);
	}
}
