package com.odde.bbuddy.license.viewmodel;


import com.odde.bbuddy.common.Consumer;
import com.odde.bbuddy.license.api.LicenseApi;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Date;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QueryDateTest {

	@Test
	public void testCalculateAmount() {
		LicenseApi stubLicenseApi = mock(LicenseApi.class);
		QueryDate date = new QueryDate(stubLicenseApi);

		doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Consumer consumer = invocation.getArgument(0);
				consumer.accept(asList(new License("2017-02", "28")));
				return null;
			}
		}).when(stubLicenseApi).processAllLicenses(any(Consumer.class));

		date.calculateAmount(new Date(2017 - 1900, 2-1, 10), new Date(2017 - 1900, 2-1, 14), new Consumer<Integer>() {
			@Override
			public void accept(Integer integer) {
				assertThat(integer).isEqualTo(5);
			}
		});

	}

}
