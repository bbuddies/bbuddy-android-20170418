package com.odde.bbuddy.license.viewmodel;


import com.odde.bbuddy.license.api.LicenseApi;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.mock;
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
}
