package com.bagicode.crudwithretrofit.api;

public class UtilsAPI {

    public static final String BASE_ROOT_URL = "https://script.google.com/macros/s/";

    public static BaseApiService getApiService() {
        return RetrofitClient.getClient(BASE_ROOT_URL).create(BaseApiService.class);
    }

}
