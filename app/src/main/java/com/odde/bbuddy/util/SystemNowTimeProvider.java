package com.odde.bbuddy.util;

import java.util.Date;

public class SystemNowTimeProvider implements TimeProvider {

	@Override
	public Date getNow() {
		return new Date();
	}

}
