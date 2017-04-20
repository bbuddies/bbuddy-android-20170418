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
	final List<License> mockLicenses = new ArrayList<>();

	@Test
	public void fee_startDate_endDate_in_same_month() {
		mockLicenses.add(new License("2017-02", 28));

		mockGetLicenseApiResponse(mockLicenses);
		verifyCalculate("2017-02-15", "2017-02-28", 14);
	}

	@Test
	public void fee_startDate_endDate_in_different_month() {
		mockLicenses.add(new License("2017-02", 28));
		mockLicenses.add(new License("2017-03", 31));

		mockGetLicenseApiResponse(mockLicenses);
		verifyCalculate("2017-02-15", "2017-03-15", 29);
	}

	@Test
	public void fee_not_all_licenses_exist_between_startDate_endDate() {
		mockLicenses.add(new License("2017-02", 28));
		mockLicenses.add(new License("2017-03", 100));
		mockLicenses.add(new License("2017-06", 100));
		mockLicenses.add(new License("2017-07", 31));

		mockGetLicenseApiResponse(mockLicenses);
		verifyCalculate("2017-02-15", "2017-07-15", 229);
	}

	@Test
	public void fee_startDate_month_license_not_exist() {
		mockLicenses.add(new License("2017-03", 100));
		mockLicenses.add(new License("2017-06", 100));
		mockLicenses.add(new License("2017-07", 31));

		mockGetLicenseApiResponse(mockLicenses);
		verifyCalculate("2017-02-10", "2017-07-15", 215);
	}

	@Test
	public void fee_endDate_month_license_not_exist() {

		mockLicenses.add(new License("2017-02", 28));
		mockLicenses.add(new License("2017-03", 100));
		mockLicenses.add(new License("2017-06", 100));

		mockGetLicenseApiResponse(mockLicenses);
		verifyCalculate("2017-02-15", "2017-07-15", 214);
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
