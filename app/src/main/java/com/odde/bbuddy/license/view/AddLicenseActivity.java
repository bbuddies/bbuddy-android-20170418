package com.odde.bbuddy.license.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.odde.bbuddy.R;
import com.odde.bbuddy.license.viewModel.AddLicense;

import org.robobinding.ViewBinder;

import javax.inject.Inject;

import static com.odde.bbuddy.di.component.ActivityComponentFactory.createActivityComponentBy;

public class AddLicenseActivity extends AppCompatActivity implements AddLicenseView{

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

	@Override
	public void showError(String error) {
		Toast.makeText(this, error, Toast.LENGTH_LONG).show();
	}
}
