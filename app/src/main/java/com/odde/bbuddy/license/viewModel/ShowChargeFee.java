package com.odde.bbuddy.license.viewModel;

import com.odde.bbuddy.common.Consumer;
import com.odde.bbuddy.di.scope.ActivityScope;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Lazy;

@PresentationModel
@ActivityScope
public class ShowChargeFee implements HasPresentationModelChangeSupport {

	private final Lazy<PresentationModelChangeSupport> changeSupportLazyLoader;

	private String startDate;
	private String endDate;

	private String fee;

	CalculateFee calculateFee;

	@Inject
	public ShowChargeFee(CalculateFee calculateFee, @Named("fee") Lazy<PresentationModelChangeSupport> changeSupportLazyLoader) {
		this.calculateFee = calculateFee;
		this.changeSupportLazyLoader = changeSupportLazyLoader;
	}

	@Override
	public PresentationModelChangeSupport getPresentationModelChangeSupport() {
		return changeSupport();
	}

	private PresentationModelChangeSupport changeSupport() {
		return this.changeSupportLazyLoader.get();
	}

	public void charge() {
		calculateFee.calculate(startDate, endDate, new Consumer<Integer>() {
			@Override
			public void accept(Integer fee) {
				ShowChargeFee.this.fee = String.valueOf(fee);
				changeSupport().refreshPresentationModel();
			}
		});
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

}
