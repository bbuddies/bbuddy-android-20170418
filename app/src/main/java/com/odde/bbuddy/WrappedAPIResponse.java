package com.odde.bbuddy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WrappedAPIResponse<T> {

	private String status;
	private ArrayList<String> message;
	private T data;
}
