package com.odde.bbuddy.license.viewModel;

import com.odde.bbuddy.WrappedAPIResponse;
import com.odde.bbuddy.common.Consumer;
import com.odde.bbuddy.di.scope.ActivityScope;
import com.odde.bbuddy.license.api.GetLicensesApi;

import org.robobinding.annotation.PresentationModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

@PresentationModel
@ActivityScope
public class ShowChargeFee {

	private String startDate;
	private String endDate;

	private String fee;

	GetLicensesApi getLicensesApi;

	@Inject
	public ShowChargeFee(GetLicensesApi getLicensesApi) {
		this.getLicensesApi = getLicensesApi;
	}

	public void charge() {

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
