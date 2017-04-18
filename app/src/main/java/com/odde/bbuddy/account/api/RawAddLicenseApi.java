package com.odde.bbuddy.account.api;

import com.odde.bbuddy.account.viewmodel.License;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RawAddLicenseApi {

	@POST("/licenses")
	Call<License> addAccount(@Body License account);
}
