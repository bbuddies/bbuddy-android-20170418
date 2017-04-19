package com.odde.bbuddy.subscribe.viewmodel;

import android.util.Log;

import com.odde.bbuddy.di.scope.ActivityScope;
import com.odde.bbuddy.subscribe.api.SubscribeApi;
import com.odde.bbuddy.subscribe.view.AddSubscribeActivity;
import com.odde.bbuddy.subscribe.view.AddSubscribeCallbacks;

import org.robobinding.annotation.PresentationModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
    private String userStartDate;
    private String userEndDate;
    private AddSubscribeCallbacks mCallbacks;

    @Inject
    public EditableSubscribe(SubscribeApi subscribeApi) {
        mSubscribeApi = subscribeApi;
    }

    public void calculate() {
        mSubscribeApi.getLicensePlan(new SubscribePlanCallbacks() {
            @Override
            public void onResponse(Call<List<LicensePlan>> call, Response<List<LicensePlan>> response) {
                Log.d("Aaron", "onResponse:: call: " + call + ", response: " + response);
                for (LicensePlan licensePlan : response.body()) {
                    Log.w("Aaron", licensePlan.toString());
                }
                mCallbacks.showResult(calculateSubscribePlan(response.body()));
            }

            @Override
            public void onFailure(Call<List<LicensePlan>> call, Throwable t) {
                Log.d("Aaron", "onFailure:: call: " + call + ", t: " + t);
            }
        });
    }

    private String calculateSubscribePlan(List<LicensePlan> licensePlans) {
        Map<String, Integer> amountMap = new HashMap<>(licensePlans.size());
        for (LicensePlan licensePlan : licensePlans) {
            amountMap.put(licensePlan.getMonth(), licensePlan.getAmount());
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date startDate;
        Date endDate;
        try {
            startDate = dateFormat.parse(userStartDate);
            endDate = dateFormat.parse(userEndDate);
        } catch (ParseException e) {
            return "Input format error, e.g. 2017-01-01";
        }

        SubscribePlanCalculator calculator = new SubscribePlanCalculator(amountMap, startDate, endDate);
        return String.valueOf(calculator.calculate());
    }

    public String getUserStartDate() {
        return userStartDate;
    }

    public void setUserStartDate(String userStartDate) {
        this.userStartDate = userStartDate;
    }

    public String getUserEndDate() {
        return userEndDate;
    }

    public void setUserEndDate(String userEndDate) {
        this.userEndDate = userEndDate;
    }

    public void setCallbacks(AddSubscribeCallbacks callbacks) {
        this.mCallbacks = callbacks;
    }

    public interface SubscribePlanCallbacks {
        void onResponse(Call<List<LicensePlan>> call, retrofit2.Response<List<LicensePlan>> response);

        void onFailure(Call<List<LicensePlan>> call, Throwable t);
    }

}
