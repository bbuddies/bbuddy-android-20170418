package com.odde.bbuddy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by aaronchu on 2017/4/20.
 */

public class Clock {

	private final TimeProvider mTimeProvider;

	public Clock() {
		this(new SystemNowTimeProvider());
	}

	public Clock(TimeProvider timeProvider) {
		mTimeProvider = timeProvider;
	}

	public String getCurrentTimeString() throws ParseException {
		String pattern = "yyyy-MM-dd hh:mm:ss.SSS";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
		return dateFormat.format(mTimeProvider.getNow());
	}

}
