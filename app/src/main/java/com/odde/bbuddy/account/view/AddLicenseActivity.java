package com.odde.bbuddy.account.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.odde.bbuddy.License.AddLicense;
import com.odde.bbuddy.R;

import org.robobinding.ViewBinder;

import javax.inject.Inject;

import static com.odde.bbuddy.di.component.ActivityComponentFactory.createActivityComponentBy;

public class AddLicenseActivity extends AppCompatActivity {

	@Inject
	ViewBinder viewBinder;

	@Inject
	AddLicense license;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		createActivityComponentBy(this).inject(this);

		setContentView(viewBinder.inflateAndBind(R.layout.activity_addlicense, license));
	}

}
