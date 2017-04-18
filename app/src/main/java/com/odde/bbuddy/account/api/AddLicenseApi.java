package com.odde.bbuddy.account.api;

import com.odde.bbuddy.account.viewmodel.License;
import com.odde.bbuddy.common.ApiFactory;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddLicenseApi {

	ApiFactory apiFactory;

	@Inject
	public AddLicenseApi(ApiFactory apiFactory) {
		this.apiFactory = apiFactory;
	}

	public void addLicense(License license) {
		apiFactory.create(RawAddLicenseApi.class).addAccount(license).enqueue(new Callback<License>() {
			@Override
			public void onResponse(Call<License> call, Response<License> response) {

			}

			@Override
			public void onFailure(Call<License> call, Throwable t) {

			}
		});
	}

}
