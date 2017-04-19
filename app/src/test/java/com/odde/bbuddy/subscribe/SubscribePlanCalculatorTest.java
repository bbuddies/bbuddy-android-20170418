package com.odde.bbuddy.subscribe;

import com.odde.bbuddy.subscribe.viewmodel.SubscribePlanCalculator;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static junit.framework.Assert.assertEquals;


/**
 * Created by Aaron on 2017/4/19.
 */

public class SubscribePlanCalculatorTest {

    Map<String, Integer> amountMap = new HashMap<>(12);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Before
    public void init() {
        amountMap.put("2016-12", 150);
        amountMap.put("2017-01", 200);
        amountMap.put("2017-02", 100);
        amountMap.put("2017-05", 500);
    }

    @Test
    public void calculate() throws ParseException {
        assertAmountEqualsTo(180.65f, date(2017, 1, 3), date(2017, 1, 30));
        assertAmountEqualsTo(187.1f, date(2017, 1, 3), date(2017, 1, 31));
        assertAmountEqualsTo(194.24f, date(2017, 1, 3), date(2017, 2, 2));
        assertAmountEqualsTo(496.77f, date(2017, 1, 3), date(2017, 5, 13));
        assertAmountEqualsTo(122.58f, date(2016, 12, 20), date(2017, 1, 10));
        assertAmountEqualsTo(659.68f, date(2016, 10, 20), date(2017, 5, 13));
    }

    private void assertAmountEqualsTo(float expected, Date startDate, Date endDate) {
        SubscribePlanCalculator calculator = new SubscribePlanCalculator(amountMap, startDate, endDate);
        assertEquals(expected, calculator.calculate());
    }

    private Date date(int year, int month, int day) throws ParseException {
        return dateFormat.parse(String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month, day));
    }

}
