package com.odde.bbuddy.license;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.odde.bbuddy.R;

import org.robobinding.ViewBinder;

import javax.inject.Inject;

import static com.odde.bbuddy.di.component.ActivityComponentFactory.createActivityComponentBy;

/**
 * Created by aaronchu on 2017/4/18.
 */

public class AddLicenseActivity extends AppCompatActivity {

	@Inject
	EditableLicense mLicense;

	@Inject
	ViewBinder mViewBinder;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createActivityComponentBy(this).inject(this);
		setContentView(mViewBinder.inflateAndBind(R.layout.activity_add_license, mLicense));
	}

}
