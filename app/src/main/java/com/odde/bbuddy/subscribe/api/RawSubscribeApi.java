package com.odde.bbuddy.subscribe.api;

import com.odde.bbuddy.subscribe.viewmodel.LicenseFee;
import com.odde.bbuddy.subscribe.viewmodel.UserPlan;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

/**
 * Created by aaronchu on 2017/4/19.
 */

public interface RawSubscribeApi {

	@GET("/get")
	Call<LicenseFee> getLicenseFee(@Body LicenseFee licenseFee);

	@GET("/userPlan")
	Call<UserPlan> getUserePlan(@Body UserPlan userPlan);

}
