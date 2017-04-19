package com.odde.bbuddy.license.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.odde.bbuddy.R;
import com.odde.bbuddy.license.viewModel.ShowChargeFee;

import org.robobinding.ViewBinder;

import javax.inject.Inject;

import static com.odde.bbuddy.di.component.ActivityComponentFactory.createActivityComponentBy;


public class ShowChargeFeeActivity extends Fragment {

	@Inject
	ShowChargeFee showLicensesTotalAmount;

	@Inject
	ViewBinder viewBinder;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		createActivityComponentBy(getActivity()).inject(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return viewBinder.inflateAndBindWithoutAttachingToRoot(R.layout.activity_fee, showLicensesTotalAmount, container);
	}


}
