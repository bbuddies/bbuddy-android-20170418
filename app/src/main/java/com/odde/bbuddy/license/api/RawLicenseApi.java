package com.odde.bbuddy.license.api;

import com.odde.bbuddy.account.viewmodel.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RawLicenseApi {

    @POST("/licenses")
    Call<Account> addLicense(@Body License license);
}
