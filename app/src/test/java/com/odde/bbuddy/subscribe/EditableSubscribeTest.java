package com.odde.bbuddy.subscribe;

import com.odde.bbuddy.subscribe.api.SubscribeApi;
import com.odde.bbuddy.subscribe.view.AddSubscribeCallbacks;
import com.odde.bbuddy.subscribe.viewmodel.EditableSubscribe;

import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Aaron on 2017/4/19.
 */

public class EditableSubscribeTest {

    AddSubscribeCallbacks callbacks = mock(AddSubscribeCallbacks.class);
    SubscribeApi mockSubscribeApi = mock(SubscribeApi.class);
    EditableSubscribe mEditableSubscribe = new EditableSubscribe(mockSubscribeApi);

    @Test
    public void should_get_license_plan() {
        mEditableSubscribe.setCallbacks(callbacks);
        mEditableSubscribe.calculate();
        verify(mockSubscribeApi).getLicensePlan(any(EditableSubscribe.SubscribePlanCallbacks.class));
    }

}
