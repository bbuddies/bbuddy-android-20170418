package com.odde.bbuddy.license.viewModel;

import com.odde.bbuddy.common.Consumer;
import com.odde.bbuddy.di.scope.ActivityScope;
import com.odde.bbuddy.license.api.AddLicenseApi;
import com.odde.bbuddy.license.view.AddLicenseView;

import org.robobinding.annotation.PresentationModel;

import javax.inject.Inject;

@PresentationModel
@ActivityScope
public class AddLicense {
	private String month;
	private String amount;

	private AddLicenseApi addLicenseApi;
	private AddLicenseView addLicenseView;

	class ErrorMessage {
		static final String EMPTY_MONTH = "Please fill correct date EX:2017-02";
		static final String EMPTY_AMOUNT = "License amount should not be empty!";
		static final String WRONG_AMOUNT = "License amount should be greater than zero!";
	}

	@Inject
	public AddLicense(AddLicenseApi addLicenseApi, AddLicenseView addLicenseView) {
		this.addLicenseApi = addLicenseApi;
		this.addLicenseView = addLicenseView;
	}


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

	public void setAddLicenseView(AddLicenseView addLicenseView) {
		this.addLicenseView = addLicenseView;
	}

	public void add() {
		if (!isValidMonth(month)) {
			addLicenseView.showError(ErrorMessage.EMPTY_MONTH);
			return;
		}

		if (amount.isEmpty()) {
			addLicenseView.showError(ErrorMessage.EMPTY_AMOUNT);
			return;
		}

		if(!isAmountGreaterThanZero(amount)) {
			addLicenseView.showError(ErrorMessage.WRONG_AMOUNT);
			return;
		}

		addLicenseApi.addLicense(new License(month, Integer.parseInt(amount)), new Consumer<License>() {
			@Override
			public void accept(License license) {
				addLicenseView.completed();
			}
		});
	}

	private boolean isAmountGreaterThanZero(String amount) {
		return Integer.parseInt(amount) > 0;
	}

	private boolean isValidMonth(String date) {
		if (date.isEmpty()) {
			return false;
		}

		String[] afterSplit = date.split("-");
		if (afterSplit.length != 2) {
			return false;
		}

		int year = parseInt(afterSplit[0]);
		int month = parseInt(afterSplit[1]);

		return year > 0 && month > 0 && month <= 12;
	}

	private int parseInt(String input) {
		int result;
		try {
			result = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result = 0;
		}

		return result;
	}


}
