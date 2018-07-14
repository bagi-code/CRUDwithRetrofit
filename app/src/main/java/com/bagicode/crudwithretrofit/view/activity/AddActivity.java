package com.bagicode.crudwithretrofit.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bagicode.crudwithretrofit.R;
import com.bagicode.crudwithretrofit.api.BaseApiService;
import com.bagicode.crudwithretrofit.api.UtilsAPI;
import com.bagicode.crudwithretrofit.modul.MessageResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {

    private BaseApiService mApiService;

    private EditText et_masuk, et_keluar, et_ketmasuk, et_ketkeluar, et_tgl;
    private Button btn_save, btn_edit, btn_delete;

    private String intent_id= "", intent_tgl= "",
            intent_masuk = "", intent_keluar = "",
            intent_ketmasuk = "", intent_ketkeluar = "",
            intent_action = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mApiService = UtilsAPI.getApiService();

        intent_id= getIntent().getStringExtra("intent_id");
        intent_tgl= getIntent().getStringExtra("intent_tgl");
        intent_masuk= getIntent().getStringExtra("intent_masuk");
        intent_keluar= getIntent().getStringExtra("intent_keluar");
        intent_ketmasuk= getIntent().getStringExtra("intent_ketmasuk");
        intent_ketkeluar= getIntent().getStringExtra("intent_ketkeluar");
        intent_action= getIntent().getStringExtra("intent_action");

        et_masuk = findViewById(R.id.et_masuk);
        et_keluar = findViewById(R.id.et_keluar);
        et_ketmasuk = findViewById(R.id.et_ketmasuk);
        et_ketkeluar = findViewById(R.id.et_ketkeluar);
        et_tgl = findViewById(R.id.et_tgl);
        btn_save = findViewById(R.id.btn_save);
        btn_edit = findViewById(R.id.btn_edit);
        btn_delete = findViewById(R.id.btn_delete);

        if (intent_action.equals("edit")) {
            et_masuk.setText(intent_masuk);
            et_keluar.setText(intent_keluar);
            et_ketmasuk.setText(intent_ketmasuk);
            et_ketkeluar.setText(intent_ketkeluar);
            et_tgl.setText(intent_tgl);

//            btn_save.setText("Edit");
            btn_save.setVisibility(View.GONE);
            et_tgl.setVisibility(View.VISIBLE);
        } else {
            btn_edit.setVisibility(View.GONE);
            btn_delete.setVisibility(View.GONE);
            et_tgl.setVisibility(View.GONE);

        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String smasuk = et_masuk.getText().toString();
                String skeluar = et_keluar.getText().toString();
                String sketmasuk = et_ketmasuk.getText().toString();
                String sketkeluar = et_ketkeluar.getText().toString();


                if (smasuk.equals("")) {
                    et_masuk.setError("Silahkan isi value masuk");
                } else if (skeluar.equals("")) {
                    et_keluar.setError("Silahkan isi value keluar");
                } else if (sketmasuk.equals("")) {
                    et_ketmasuk.setError("Silahkan isi value keterangan keluar");
                } else if (sketkeluar.equals("")) {
                    et_ketkeluar.setError("Silahkan isi value keterangan masuk");
                } else {
                    dataAdd(smasuk, skeluar, sketmasuk, sketkeluar);
                }

            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String smasuk = et_masuk.getText().toString();
                String skeluar = et_keluar.getText().toString();
                String sketmasuk = et_ketmasuk.getText().toString();
                String sketkeluar = et_ketkeluar.getText().toString();
                String stgl = et_tgl.getText().toString();

                if (smasuk.equals("")) {
                    et_masuk.setError("Silahkan isi value masuk");
                } else if (skeluar.equals("")) {
                    et_keluar.setError("Silahkan isi value keluar");
                } else if (sketmasuk.equals("")) {
                    et_ketmasuk.setError("Silahkan isi value keterangan keluar");
                } else if (sketkeluar.equals("")) {
                    et_ketkeluar.setError("Silahkan isi value keterangan masuk");
                } else if (stgl.equals("")) {
                    et_tgl.setError("Silahkan isi value keterangan tanggal");
                } else {
                    editData(smasuk, skeluar, sketmasuk, sketkeluar, intent_id, stgl);
                }

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData(intent_id);

            }
        });
    }

    private void dataAdd(String masuk, String keluar, String ket_masuk, String ket_keluar) {

        mApiService.addData(
                "insert",
                "BCA",
                masuk,
                keluar,
                ket_masuk,
                ket_keluar)
                .enqueue(new Callback<MessageResponse>() {
                    @Override
                    public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getCode().equals("200")) {
                                Toast.makeText(AddActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                finish();

                            } else {
                                Toast.makeText(AddActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(AddActivity.this, "Please try again, server is down", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onFailure(Call<MessageResponse> call, Throwable t) {
                        Toast.makeText(AddActivity.this, "Please try again, server is down onfail", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void editData(String masuk, String keluar, String ket_masuk, String ket_keluar, String id, String tgl) {

        mApiService.editData(
                "update",
                "BCA",
                masuk,
                keluar,
                ket_masuk,
                ket_keluar,
                id,
                tgl)
                .enqueue(new Callback<MessageResponse>() {
                    @Override
                    public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getCode().equals("200")) {
                                Toast.makeText(AddActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                finish();

                            } else {
                                Toast.makeText(AddActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(AddActivity.this, "Please try again, server is down", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onFailure(Call<MessageResponse> call, Throwable t) {
                        Toast.makeText(AddActivity.this, "Please try again, server is down onfail", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void deleteData(String id) {

        mApiService.deleteData(
                "delete",
                "BCA",
                id)
                .enqueue(new Callback<MessageResponse>() {
                    @Override
                    public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getCode().equals("200")) {
                                Toast.makeText(AddActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                finish();

                            } else {
                                Toast.makeText(AddActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(AddActivity.this, "Please try again, server is down", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onFailure(Call<MessageResponse> call, Throwable t) {
                        Toast.makeText(AddActivity.this, "Please try again, server is down onfail", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
