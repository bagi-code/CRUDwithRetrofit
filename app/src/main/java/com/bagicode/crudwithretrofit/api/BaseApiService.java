package com.bagicode.crudwithretrofit.api;

import com.bagicode.crudwithretrofit.modul.ReadDataResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaseApiService {

    @FormUrlEncoded
    @POST("AKfycbxB5RVPFfecAjzOwfcc7ZX2TGYwcKV8_A6qR4eD/exec")
    Call<ReadDataResponse> readData (@Field("action") String action,
                                     @Field("tabelName") String tabelName);

}
