package com.odde.bbuddy.license.api;

import com.odde.bbuddy.common.Consumer;
import com.odde.bbuddy.license.viewModel.License;
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

	public void addLicense(final License license, final Consumer<License> consumer) {
		apiFactory.create(RawAddLicenseApi.class).addLicense(license).enqueue(new Callback<License>() {
			@Override
			public void onResponse(Call<License> call, Response<License> response) {
				consumer.accept(license);
			}

			@Override
			public void onFailure(Call<License> call, Throwable t) {
				consumer.accept(license);
			}
		});
	}

}
