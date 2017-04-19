package com.odde.bbuddy.subscribe.viewmodel;

import android.util.Log;

import com.odde.bbuddy.di.scope.ActivityScope;
import com.odde.bbuddy.subscribe.api.SubscribeApi;

import org.robobinding.annotation.PresentationModel;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aaronchu on 2017/4/19.
 */

@PresentationModel
@ActivityScope
public class EditableSubscribe {

	private SubscribeApi mSubscribeApi;
	private String startDate;
	private String endDate;

	@Inject
	public EditableSubscribe(SubscribeApi subscribeApi) {
		mSubscribeApi = subscribeApi;
	}

	public void calculate() {
		mSubscribeApi.getLicensePlan(new SubscriptionPlanCallbacks() {
			@Override
			public void onResponse(Call<List<LicensePlan>> call, Response<List<LicensePlan>> response) {
				Log.d("Aaron", "onResponse:: call: " + call + ", response: " + response);
			}

			@Override
			public void onFailure(Call<List<LicensePlan>> call, Throwable t) {
				Log.d("Aaron", "onFailure:: call: " + call + ", t: " + t);
			}
		});
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public interface SubscriptionPlanCallbacks {
		void onResponse(Call<List<LicensePlan>> call, retrofit2.Response<List<LicensePlan>> response);

		void onFailure(Call<List<LicensePlan>> call, Throwable t);
	}

}
