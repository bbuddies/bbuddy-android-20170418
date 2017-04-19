package com.odde.bbuddy.license.viewmodel;


import com.odde.bbuddy.license.api.LicenseApi;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AddLicenseControllerTest {

	@Test
	public void add_should_start_add_license_api() {
		LicenseApi api = mock(LicenseApi.class);
		AddLicenseController controller = new AddLicenseController(api);
		controller.setMonth("2017-04");
		controller.setAmount("9487");
		controller.add();

		ArgumentCaptor<License> captor = forClass(License.class);
		verify(api).addLicense(captor.capture());
		assertThat(captor.getValue().getAmount()).isEqualToIgnoringCase("9487");
		assertThat(captor.getValue().getMonth()).isEqualToIgnoringCase("2017-04");
	}

	@Test
	public void add_should_show_toast_when_amount_empty() {
		LicenseApi api = mock(LicenseApi.class);
		AddLicenseController controller = Mockito.spy(new AddLicenseController(api));

		int timesShowToast = 0;
		Mockito.doNothing().when(controller).showAmountZeroToast();
		++timesShowToast;

		controller.showAmountZeroToast();
		controller.add();


		verify(controller, times(++timesShowToast)).showAmountZeroToast();

		controller.setAmount("0");
		controller.add();

		verify(controller, times(++timesShowToast)).showAmountZeroToast();

		controller.setAmount("100");
		controller.add();

		verify(controller, times(timesShowToast)).showAmountZeroToast();
		ArgumentCaptor<License> captor = forClass(License.class);
		verify(api).addLicense(captor.capture());
	}

	@Test
	public void isAmountZeroEmpty() {
		License license = new License("2017-04", "");

		// default empty
		Assert.assertTrue(license.isAmountZeroOrEmpty());

		license.setAmount("9487");
		Assert.assertFalse(license.isAmountZeroOrEmpty());

		license.setAmount("0");
		Assert.assertTrue(license.isAmountZeroOrEmpty());
	}
}
