package com.odde.bbuddy.license.api;


import com.odde.bbuddy.common.ApiFactory;
import com.odde.bbuddy.common.Consumer;
import com.odde.bbuddy.license.viewmodel.License;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LicenseApi {

	ApiFactory factory;

	@Inject
	public LicenseApi(ApiFactory factory) {
		this.factory = factory;
	}

	public void addLicense(License license) {
		factory.create(RawLicenseApi.class).addLicense(license).enqueue(new Callback<License>() {
			@Override
			public void onResponse(Call<License> call, Response<License> response) {

			}

			@Override
			public void onFailure(Call<License> call, Throwable t) {

			}
		});
	}

	public void processAllLicenses(final Consumer<List<License>> consumer) {
		factory.create(RawLicenseApi.class).allLicense().enqueue(new Callback<List<License>>() {
			@Override
			public void onResponse(Call<List<License>> call, Response<List<License>> response) {
				consumer.accept(response.body());
			}

			@Override
			public void onFailure(Call<List<License>> call, Throwable t) {

			}
		});
	}
}
