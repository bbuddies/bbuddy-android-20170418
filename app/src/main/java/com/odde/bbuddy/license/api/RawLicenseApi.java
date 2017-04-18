package com.odde.bbuddy.license.api;


import com.odde.bbuddy.license.viewmodel.License;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RawLicenseApi {

	@POST("/licenses")
	Call<License> addLicense(@Body License license);
}
