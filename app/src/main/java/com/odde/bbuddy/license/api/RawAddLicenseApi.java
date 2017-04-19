package com.odde.bbuddy.license.api;

import com.odde.bbuddy.license.viewModel.License;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RawAddLicenseApi {

	@POST("/licenses")
	Call<License> addAccount(@Body License account);
}
