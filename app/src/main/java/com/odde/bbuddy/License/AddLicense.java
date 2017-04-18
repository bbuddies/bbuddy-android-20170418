package com.odde.bbuddy.License;

import com.odde.bbuddy.di.scope.ActivityScope;

import org.robobinding.annotation.PresentationModel;

import javax.inject.Inject;

@PresentationModel
@ActivityScope
public class AddLicense {
	private String month;
	private String amount;

	public String getMonth() {
		return month;
	}

	public String getAmount() {
		return amount;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Inject
	public AddLicense() {
	}
}
