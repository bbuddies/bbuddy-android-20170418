package com.odde.bbuddy.subscribe.viewmodel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aaron on 2017/4/19.
 */

public class SubscribePlanCalculator {

    private Date mStartDate;
    private Date mEndDate;
    private Map<String, Integer> mAmountMap;
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM", Locale.getDefault());

    public SubscribePlanCalculator(Map<String, Integer> amountMap, Date startDate, Date endDate) {
        mAmountMap = amountMap;
        mStartDate = startDate;
        mEndDate = endDate;
    }

    public float calculate() {
        float totalAmount = 0f;
        Date currentEndDate;
        Date currentStartDate = mStartDate;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mStartDate);
        while (!calendar.getTime().after(mEndDate)) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            currentEndDate = calendar.getTime();
            if (currentEndDate.after(mEndDate)) {
                currentEndDate = mEndDate;
            }
            totalAmount += calculateAmount(calendar, currentStartDate, currentEndDate);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            currentStartDate = calendar.getTime();
        }
        return Math.round(totalAmount * 100) / 100f;
    }

    private float calculateAmount(Calendar calendar, Date startDate, Date endDate) {
        String yearMonth = mDateFormat.format(startDate);
        int monthAmount = mAmountMap.get(yearMonth) != null ? mAmountMap.get(yearMonth) : 0;
        long days = TimeUnit.MILLISECONDS.toDays(endDate.getTime() - startDate.getTime()) + 1;
        return 1f * days / calendar.getActualMaximum(Calendar.DAY_OF_MONTH) * monthAmount;
    }

}
