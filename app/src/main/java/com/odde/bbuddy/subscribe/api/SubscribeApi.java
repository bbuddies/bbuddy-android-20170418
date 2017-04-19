package com.odde.bbuddy.subscribe.api;

import com.odde.bbuddy.subscribe.viewmodel.EditableSubscribe;
import com.odde.bbuddy.subscribe.viewmodel.LicensePlan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aaronchu on 2017/4/19.
 */

public class SubscribeApi {

	private RawSubscribeApi mRawSubscribeApi;

	public SubscribeApi(RawSubscribeApi rawSubscribeApi) {
		this.mRawSubscribeApi = rawSubscribeApi;
	}

	public void getLicensePlan(final EditableSubscribe.SubscribePlanCallbacks callbacks) {
		mRawSubscribeApi.getLicensePlan()
				.enqueue(new Callback<List<LicensePlan>>() {
					@Override
					public void onResponse(Call<List<LicensePlan>> call, Response<List<LicensePlan>> response) {
						callbacks.onResponse(call, response);
					}

					@Override
					public void onFailure(Call<List<LicensePlan>> call, Throwable t) {
						callbacks.onFailure(call, t);
					}
				});
	}

}
