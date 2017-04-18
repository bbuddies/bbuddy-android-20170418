package com.odde.bbuddy.account.viewmodel;

import com.odde.bbuddy.account.api.AddLicenseApi;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class AddLicenseTest {

	@Test
	public void test_add_license() {
		AddLicenseApi addLicenseApi = Mockito.mock(AddLicenseApi.class);
		AddLicense addLicense = new AddLicense(addLicenseApi);
		addLicense.setMonth("2017-04");
		addLicense.setAmount("100");

		addLicense.add();

		ArgumentCaptor<License> captor = ArgumentCaptor.forClass(License.class);
		Mockito.verify(addLicenseApi).addLicense(captor.capture());
		Assert.assertEquals(addLicense.getMonth(), captor.getValue().getMonth());
		Assert.assertEquals(Integer.parseInt(addLicense.getAmount()), captor.getValue().getAmount());
	}
}

