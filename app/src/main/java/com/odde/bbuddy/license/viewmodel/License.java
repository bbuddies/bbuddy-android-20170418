package com.odde.bbuddy.license.viewmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class License {

	private String month;
	private String amount;

	public License() {

	}

	public License(String month, String amount) {
		this.month = month;
		this.amount = amount;
	}

	public boolean isAmountZeroOrEmpty() {
		int number = 0;
		try {
			number = Integer.parseInt(amount);
		} catch(NumberFormatException e) {

		}
		return number == 0;
	}

}