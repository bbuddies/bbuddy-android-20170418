package com.odde.bbuddy.util;

import java.text.ParseException;
import java.util.Date;

public interface TimeProvider {
	Date getNow() throws ParseException;
}