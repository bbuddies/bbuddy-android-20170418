package com.odde.bbuddy.license;

import android.util.Log;

/**
 * Created by aaronchu on 2017/4/18.
 */

public class LicenseApi {

	private String mMonth;
	private String mAccount;

	public LicenseApi(String month, String amount) {
		mMonth = month;
		mAccount = amount;
	}

	public void addLicense() {
		Log.d("Aaron", "month: " + mMonth + ", amount: " + mAccount);
	}

}
