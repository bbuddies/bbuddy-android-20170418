package com.odde.bbuddy.license.api;

import android.util.Log;

import com.odde.bbuddy.account.api.RawAccountsApi;
import com.odde.bbuddy.account.viewmodel.Account;
import com.odde.bbuddy.common.JsonBackend;
import com.odde.bbuddy.common.JsonMapper;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by aaronchu on 2017/4/18.
 */

public class LicenseApi {

	private final RawLicenseApi mRawLicenseApi;

	public LicenseApi(RawLicenseApi rawLicenseApi) {
		mRawLicenseApi = rawLicenseApi;
	}

	public void addLicense(License license) {
		mRawLicenseApi.addLicense(license)
				.enqueue(new Callback<Account>() {
					@Override
					public void onResponse(Call<Account> call, retrofit2.Response<Account> response) {
						Log.d("Aaron", "onResponse:: call: " + call + ", response: " + response);
					}
					@Override
					public void onFailure(Call<Account> call, Throwable t) {
						Log.d("Aaron", "onFailure:: call: " + call + ", t: " + t);
					}
				});
	}

}
