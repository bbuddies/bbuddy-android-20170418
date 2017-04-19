package com.odde.bbuddy.license.viewmodel;

import com.odde.bbuddy.account.viewmodel.Account;
import com.odde.bbuddy.di.scope.ActivityScope;
import com.odde.bbuddy.license.api.License;
import com.odde.bbuddy.license.api.LicenseApi;
import com.odde.bbuddy.license.view.AddLicense;
import com.odde.bbuddy.license.view.AddLicenseActivity;

import org.robobinding.annotation.PresentationModel;

import javax.inject.Inject;

/**
 * Created by aaronchu on 2017/4/18.
 */
@PresentationModel
@ActivityScope
public class EditableLicense {

	private String month;
	private String amount;
	private AddLicense view;
	private LicenseApi licenseApi;

	@Inject
	public EditableLicense(LicenseApi licenseApi) {
		this.licenseApi = licenseApi;
	}

	public void add() {
		License license = new License();
		license.setMonth(month);
		license.setAmount(amount);
		licenseApi.addLicense(license);
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

	public void setView(AddLicense addLicense) {
		this.view = addLicense;
	}
}
