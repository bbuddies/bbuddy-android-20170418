package com.odde.bbuddy;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.odde.bbuddy.license.view.QueryAmountActivity;


public class LicenseActivity extends Fragment {

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tab_license, container, false);
		Button queryButton = (Button) view.findViewById(R.id.query);
		queryButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), QueryAmountActivity.class));
			}
		});
		return view;
	}
}
