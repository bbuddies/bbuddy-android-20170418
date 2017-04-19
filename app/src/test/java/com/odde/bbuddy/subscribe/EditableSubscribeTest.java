package com.odde.bbuddy.subscribe;

import com.odde.bbuddy.subscribe.api.SubscribeApi;
import com.odde.bbuddy.subscribe.viewmodel.EditableSubscribe;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.mockito.Mockito.mock;

/**
 * Created by aaronchu on 2017/4/19.
 */

public class EditableSubscribeTest {
	SubscribeApi mockSubscribeApi = mock(SubscribeApi.class);
	EditableSubscribe editableSubscribe = new EditableSubscribe(mockSubscribeApi);

	@Test
	public void testCalculateAmountWithinTheSameMonth() throws ParseException {
//		Map<String, Integer> yearMonthAmountMap = new HashMap<>(12);
//		yearMonthAmountMap.put("2017-01", 200);
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//		Date startDate = dateFormat.parse("2017-01-03");
//		Date endDate = dateFormat.parse("2017-01-30");
//
//		editableSubscribe.calculateAmountWithinTheSameMonth(yearMonthAmountMap, startDate, endDate);
	}


}
