package com.odde.bbuddy.license.viewModel;

import com.odde.bbuddy.common.Consumer;
import com.odde.bbuddy.license.api.GetLicensesApi;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;


public class ShowChargeFeeTest {

	GetLicensesApi stubLicenseApi = mock(GetLicensesApi.class);
	CalculateFee calculateFee = new CalculateFee(stubLicenseApi);

	@Test
	public void fee_startDate_endDate_in_same_month() {
		final List<License> mockLicenses = new ArrayList<>();
		mockLicenses.add(new License("2017-02", 28));

		mockGetLicenseApiResponse(mockLicenses);
		verifyCalculate("2017-02-10", "2017-02-14", 5);
	}

	private void mockGetLicenseApiResponse(final List<License> mockLicenses) {
		doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Consumer consumer = invocation.getArgument(0);
				consumer.accept(mockLicenses);
				return null;
			}
		}).when(stubLicenseApi).get(any(Consumer.class));
	}

	private void verifyCalculate(String startDate, String endDate, final int expectFee) {
		calculateFee.calculate(startDate, endDate, new Consumer<Integer>() {
			@Override
			public void accept(Integer integer) {
				assertThat(integer).isEqualTo(expectFee);
			}
		});
	}
}
