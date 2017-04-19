package com.odde.bbuddy.license.viewmodel;

import com.odde.bbuddy.di.scope.ActivityScope;
import com.odde.bbuddy.license.api.License;
import com.odde.bbuddy.license.api.LicenseApi;
import com.odde.bbuddy.license.view.AddLicenseCallbacks;

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
	private AddLicenseCallbacks mCallbacks;
	private LicenseApi licenseApi;

	@Inject
	public EditableLicense(LicenseApi licenseApi) {
		this.licenseApi = licenseApi;
	}

	public void add() {
		if (amount.isEmpty() || month.isEmpty()) {
			mCallbacks.showError("amount and month cannot be empty");
			return;
		}
		if (Integer.parseInt(amount) <= 0) {
			mCallbacks.showError("system error, please find PO");
			return;
		}
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

	public void setCallbacks(AddLicenseCallbacks callbacks) {
		this.mCallbacks = callbacks;
	}

}
