package com.odde.bbuddy.subscribe.viewmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by aaronchu on 2017/4/19.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class LicensePlan {

	int id;
	String month;
	int amount;

	@Override
	public String toString() {
		return "id: " + id + ", month: " + month + ", amount: " + amount;
	}
}
