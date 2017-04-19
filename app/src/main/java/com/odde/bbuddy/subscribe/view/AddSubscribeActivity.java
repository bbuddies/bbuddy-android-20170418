package com.odde.bbuddy.subscribe.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.odde.bbuddy.R;
import com.odde.bbuddy.subscribe.viewmodel.EditableSubscribe;

import org.robobinding.ViewBinder;

import javax.inject.Inject;

import static com.odde.bbuddy.di.component.ActivityComponentFactory.createActivityComponentBy;

/**
 * Created by aaronchu on 2017/4/19.
 */

public class AddSubscribeActivity extends AppCompatActivity implements AddSubscribeCallbacks {

	@Inject
	EditableSubscribe mSubscribeModule;

	@Inject
	ViewBinder mViewBinder;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createActivityComponentBy(this).inject(this);
		mSubscribeModule.setCallbacks(this);
		setContentView(mViewBinder.inflateAndBind(R.layout.activity_add_subscribe, mSubscribeModule));
	}

	@Override
	public void showResult(String result) {
		TextView totalAmount = (TextView) findViewById(R.id.total_amount_label);
		totalAmount.setText(result);
	}

}
