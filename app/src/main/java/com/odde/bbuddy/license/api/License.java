package com.odde.bbuddy.license.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by aaronchu on 2017/4/19.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class License {

	private String mMonth;
	private String mAmount;

	public String getMonth() {
		return mMonth;
	}

	public void setMonth(String mMonth) {
		this.mMonth = mMonth;
	}

	public String getAmount() {
		return mAmount;
	}

	public void setAmount(String mAmount) {
		this.mAmount = mAmount;
	}
}
