package com.odde.bbuddy.others;

import com.odde.bbuddy.util.Clock;
import com.odde.bbuddy.util.TimeProvider;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by aaronchu on 2017/4/20.
 */

public class ClockTest {

	private final String VERIFIED_DATE = "2017-01-07 11:49:00.323";

	private TimeProvider mockTimeProvider = new TimeProvider() {
		@Override
		public Date getNow() throws ParseException {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
			return dateFormat.parse(VERIFIED_DATE);
		}
	};

	@Test
	public void getCurrentTimeString() throws ParseException {
		Clock clock = new Clock(mockTimeProvider);
		Assert.assertEquals(VERIFIED_DATE, clock.getCurrentTimeString());
	}

}
