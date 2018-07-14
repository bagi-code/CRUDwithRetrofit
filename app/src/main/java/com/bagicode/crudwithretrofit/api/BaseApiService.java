package com.bagicode.crudwithretrofit.api;

import com.bagicode.crudwithretrofit.modul.MessageResponse;
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

    @FormUrlEncoded
    @POST("AKfycbxB5RVPFfecAjzOwfcc7ZX2TGYwcKV8_A6qR4eD/exec")
    Call<MessageResponse> addData (@Field("action") String action,
                                   @Field("tabelName") String tabelName,
                                   @Field("masuk") String masuk,
                                   @Field("keluar") String keluar,
                                   @Field("ket_masuk") String ket_masuk,
                                   @Field("ket_keluar") String ket_keluar);

    @FormUrlEncoded
    @POST("AKfycbxB5RVPFfecAjzOwfcc7ZX2TGYwcKV8_A6qR4eD/exec")
    Call<MessageResponse> editData (@Field("action") String action,
                                   @Field("tabelName") String tabelName,
                                   @Field("masuk") String masuk,
                                   @Field("keluar") String keluar,
                                   @Field("ket_masuk") String ket_masuk,
                                   @Field("ket_keluar") String ket_keluar,
                                   @Field("id") String id,
                                   @Field("tgl") String tgl);

    @FormUrlEncoded
    @POST("AKfycbxB5RVPFfecAjzOwfcc7ZX2TGYwcKV8_A6qR4eD/exec")
    Call<MessageResponse> deleteData (@Field("action") String action,
                                   @Field("tabelName") String tabelName,
                                   @Field("id") String id);

}
