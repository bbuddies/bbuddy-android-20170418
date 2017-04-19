package com.odde.bbuddy.license.viewmodel;


import android.content.Context;
import android.widget.Toast;

import com.odde.bbuddy.R;
import com.odde.bbuddy.di.scope.ActivityScope;
import com.odde.bbuddy.license.api.LicenseApi;

import org.robobinding.annotation.PresentationModel;

import javax.inject.Inject;

@PresentationModel
@ActivityScope
public class AddLicenseController {

	private static final String TAG = AddLicenseController.class.getSimpleName();

	private String month;
	private String amount;
	private LicenseApi api;

	private Context context;

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

	public void setContext(Context context) {
		this.context = context;
	}

	public void add() {
//		if (isAmountZeroOrEmpty()) {
//			view.showAmountZeroToast("");
//		} else {
//			licenseApi.startAPI(runnable);
//
//			api.addLicense(new License(month, amount)), new Runnable(){
//				@Override
//				public void run() {
//					view.shwo
//				}
//			};
//		}



	}

	void showAmountZeroToast() {
		Toast.makeText(context, context.getString(R.string.amount_zero_msg), Toast.LENGTH_LONG).show();
	}

}
