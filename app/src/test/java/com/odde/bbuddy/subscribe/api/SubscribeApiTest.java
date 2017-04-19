package com.odde.bbuddy.subscribe.api;

import com.odde.bbuddy.account.viewmodel.Account;
import com.odde.bbuddy.subscribe.viewmodel.EditableSubscribe;
import com.odde.bbuddy.subscribe.viewmodel.LicensePlan;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Aaron on 2017/4/19.
 */

public class SubscribeApiTest {

    RawSubscribeApi mockRawSubscribeApi = mock(RawSubscribeApi.class);

    @Test
    public void should_get_license_plan_list_success() {
        Call<List<LicensePlan>> stubCallback = mock(Call.class);
        when(mockRawSubscribeApi.getLicensePlan()).thenReturn(stubCallback);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback callback = invocation.getArgument(0);
                callback.onFailure(null, null);
                return null;
            }
        }).when(stubCallback).enqueue(any(Callback.class));
    }

}
