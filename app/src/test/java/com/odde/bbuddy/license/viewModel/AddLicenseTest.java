package com.odde.bbuddy.license.viewModel;

import com.odde.bbuddy.license.api.AddLicenseApi;
import com.odde.bbuddy.license.AddLicenseView;
import com.odde.bbuddy.license.viewModel.AddLicense;
import com.odde.bbuddy.license.viewModel.License;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class AddLicenseTest {

	private AddLicenseApi addLicenseApi;
	private AddLicenseView addLicenseView;
	private AddLicense addLicense;

	@Before
	public void setup() {
		addLicenseApi = Mockito.mock(AddLicenseApi.class);
		addLicenseView = Mockito.mock(AddLicenseView.class);
		addLicense = new AddLicense(addLicenseApi, addLicenseView);
	}

	@Test
	public void test_add_license() {
		addLicense.setMonth("2017-04");
		addLicense.setAmount("100");

		addLicense.add();

		verifyAddLicenseApiParams();
	}

	@Test
	public void test_add_zero_amount() {
		addLicense.setMonth("2017-04");
		addLicense.setAmount("0");

		addLicense.add();

		verifyAddLicenseViewError(AddLicense.ErrorMessage.WRONG_AMOUNT);
	}

	@Test
	public void test_add_empty_amount() {
		addLicense.setMonth("2017-04");
		addLicense.setAmount("");

		addLicense.add();

		verifyAddLicenseViewError(AddLicense.ErrorMessage.WRONG_AMOUNT);
	}

	@Test
	public void test_add_empty_month() {
		addLicense.setMonth("");
		addLicense.setAmount("100");

		addLicense.add();

		verifyAddLicenseViewError(AddLicense.ErrorMessage.WRONG_MONTH);
	}

	private void verifyAddLicenseApiParams() {
		ArgumentCaptor<License> captor = ArgumentCaptor.forClass(License.class);
		Mockito.verify(addLicenseApi).addLicense(captor.capture());
		Assert.assertEquals(addLicense.getMonth(), captor.getValue().getMonth());
		Assert.assertEquals(Integer.parseInt(addLicense.getAmount()), captor.getValue().getAmount());
	}

	private void verifyAddLicenseViewError(String errorMessage) {
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(addLicenseView).showError(captor.capture());
		Assert.assertEquals(errorMessage, captor.getValue());
	}
}

