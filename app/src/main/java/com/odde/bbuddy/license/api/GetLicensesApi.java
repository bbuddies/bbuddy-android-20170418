package com.odde.bbuddy.license.api;

import android.util.Log;

import com.odde.bbuddy.WrappedAPIResponse;
import com.odde.bbuddy.common.ApiFactory;
import com.odde.bbuddy.license.viewModel.License;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetLicensesApi {

	ApiFactory apiFactory;

	@Inject
	public GetLicensesApi(ApiFactory apiFactory) {
		this.apiFactory = apiFactory;
	}

	public void get() {
		apiFactory.create(RawGetLicensesApi.class).getLicenses().enqueue(new Callback<WrappedAPIResponse<List<License>>>() {
			@Override
			public void onResponse(Call<WrappedAPIResponse<List<License>>> call, Response<WrappedAPIResponse<List<License>>> response) {
			}

			@Override
			public void onFailure(Call<WrappedAPIResponse<List<License>>> call, Throwable t) {
			}
		});
	}

}
