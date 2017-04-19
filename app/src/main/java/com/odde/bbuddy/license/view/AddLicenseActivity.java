package com.odde.bbuddy.license.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.odde.bbuddy.R;
import com.odde.bbuddy.license.viewmodel.EditableLicense;

import org.robobinding.ViewBinder;

import javax.inject.Inject;

import static com.odde.bbuddy.di.component.ActivityComponentFactory.createActivityComponentBy;

/**
 * Created by aaronchu on 2017/4/18.
 */

public class AddLicenseActivity extends AppCompatActivity implements AddLicenseCallbacks {

	@Inject
	EditableLicense mLicenseModule;

	@Inject
	ViewBinder mViewBinder;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createActivityComponentBy(this).inject(this);
		mLicenseModule.setCallbacks(this);
		setContentView(mViewBinder.inflateAndBind(R.layout.activity_add_license, mLicenseModule));
	}

	@Override
	public void showError(String errorMsg) {
		EditText amountEditText = (EditText) findViewById(R.id.amount_textfield);
		amountEditText.setError(errorMsg);
	}

}
