package com.odde.bbuddy.license.viewModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class License {

	private String month;
	private int amount;

	public License() {

	}

	public License(String month, int amount) {
		this.month = month;
		this.amount = amount;
	}

}
