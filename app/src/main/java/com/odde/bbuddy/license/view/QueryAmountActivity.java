package com.odde.bbuddy.license.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;

import com.odde.bbuddy.R;
import com.odde.bbuddy.license.viewmodel.QueryAmountController;

import org.robobinding.ViewBinder;

import java.util.Calendar;

import javax.inject.Inject;

import static com.odde.bbuddy.di.component.ActivityComponentFactory.createActivityComponentBy;


public class QueryAmountActivity extends AppCompatActivity {

	@Inject
	QueryAmountController controller;

	@Inject
	ViewBinder viewBinder;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		createActivityComponentBy(this).inject(this);

		setContentView(viewBinder.inflateAndBind(R.layout.activity_query_amount, controller));
	}
}
