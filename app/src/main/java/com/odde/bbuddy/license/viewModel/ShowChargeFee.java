package com.odde.bbuddy.license.viewModel;

import android.util.Log;

import com.odde.bbuddy.common.Consumer;
import com.odde.bbuddy.di.scope.ActivityScope;

import org.robobinding.annotation.PresentationModel;

import javax.inject.Inject;

@PresentationModel
@ActivityScope
public class ShowChargeFee {

	private String startDate;
	private String endDate;

	private String fee;

	CalculateFee calculateFee;

	@Inject
	public ShowChargeFee(CalculateFee calculateFee) {
		this.calculateFee = calculateFee;
	}

	public void charge() {
		calculateFee.calculate(startDate, endDate, new Consumer<Integer>() {
			@Override
			public void accept(Integer fee) {
				ShowChargeFee.this.fee = String.valueOf(fee);
				Log.i("Fee", ShowChargeFee.this.fee);
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

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

}
