package com.bagicode.crudwithretrofit.modul;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("TGL")
    @Expose
    private String tGL;
    @SerializedName("MASUK")
    @Expose
    private String mASUK;
    @SerializedName("KELUAR")
    @Expose
    private String kELUAR;
    @SerializedName("KETERANGAN_MASUK")
    @Expose
    private String kETERANGANMASUK;
    @SerializedName("KETERANGAN_KELUAR")
    @Expose
    private String kETERANGANKELUAR;

    public Record(String iD, String tGL, String mASUK, String kELUAR, String kETERANGANMASUK, String kETERANGANKELUAR) {
        this.iD = iD;
        this.tGL = tGL;
        this.mASUK = mASUK;
        this.kELUAR = kELUAR;
        this.kETERANGANMASUK = kETERANGANMASUK;
        this.kETERANGANKELUAR = kETERANGANKELUAR;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getTGL() {
        return tGL;
    }

    public void setTGL(String tGL) {
        this.tGL = tGL;
    }

    public String getMASUK() {
        return mASUK;
    }

    public void setMASUK(String mASUK) {
        this.mASUK = mASUK;
    }

    public String getKELUAR() {
        return kELUAR;
    }

    public void setKELUAR(String kELUAR) {
        this.kELUAR = kELUAR;
    }

    public String getKETERANGANMASUK() {
        return kETERANGANMASUK;
    }

    public void setKETERANGANMASUK(String kETERANGANMASUK) {
        this.kETERANGANMASUK = kETERANGANMASUK;
    }

    public String getKETERANGANKELUAR() {
        return kETERANGANKELUAR;
    }

    public void setKETERANGANKELUAR(String kETERANGANKELUAR) {
        this.kETERANGANKELUAR = kETERANGANKELUAR;
    }

}
