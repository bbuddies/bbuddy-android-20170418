package com.odde.bbuddy.license.viewmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class License {

	private String month;
	private String amount;

	public License(String month, String amount) {
		this.month = month;
		this.amount = amount;
	}
}
