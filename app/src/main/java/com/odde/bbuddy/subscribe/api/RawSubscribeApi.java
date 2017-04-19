package com.odde.bbuddy.subscribe.api;

import com.odde.bbuddy.subscribe.viewmodel.LicensePlan;
import com.odde.bbuddy.subscribe.viewmodel.SubscriptionPlan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by aaronchu on 2017/4/19.
 */

public interface RawSubscribeApi {

	@GET("/licenses")
	Call<List<LicensePlan>> getLicensePlan();

	@GET("/fee")
	Call<SubscriptionPlan> getSubscriptionPlan(String startDate, String endDate);

}
