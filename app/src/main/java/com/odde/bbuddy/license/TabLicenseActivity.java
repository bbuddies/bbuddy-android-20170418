package com.odde.bbuddy.license;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.odde.bbuddy.R;

/**
 * Created by aaronchu on 2017/4/18.
 */

public class TabLicenseActivity extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		//Returning the layout file after inflating
		//Change R.layout.tab1 in you classes
		return inflater.inflate(R.layout.tab_license, container, false);
	}

}
