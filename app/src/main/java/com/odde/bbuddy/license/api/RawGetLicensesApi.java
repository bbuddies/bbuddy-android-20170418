package com.odde.bbuddy.license.api;

import com.odde.bbuddy.WrappedAPIResponse;
import com.odde.bbuddy.license.viewModel.License;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RawGetLicensesApi {

	@GET("/licenses")
	Call<WrappedAPIResponse<List<License>>> getLicenses();
}
