package com.bagicode.crudwithretrofit.modul;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReadDataResponse {

    @SerializedName("records")
    @Expose
    private List<Record> records = null;

    public ReadDataResponse(List<Record> records) {
        this.records = records;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
