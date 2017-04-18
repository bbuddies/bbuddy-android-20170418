package com.odde.bbuddy.license;

import android.util.Log;

import com.odde.bbuddy.di.scope.ActivityScope;

import org.robobinding.annotation.PresentationModel;

import javax.inject.Inject;

/**
 * Created by aaronchu on 2017/4/18.
 */
@PresentationModel
@ActivityScope
class EditableLicense {

	private String month;
	private String amount;

	@Inject
	public EditableLicense() {
	}

	public void add() {
		LicenseApi licenseApi = new LicenseApi(month, amount);
		licenseApi.addLicense();
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

}
