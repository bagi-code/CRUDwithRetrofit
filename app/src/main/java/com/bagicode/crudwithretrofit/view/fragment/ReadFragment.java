package com.bagicode.crudwithretrofit.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bagicode.crudwithretrofit.R;
import com.bagicode.crudwithretrofit.adapter.CategoryAdapter;
import com.bagicode.crudwithretrofit.api.BaseApiService;
import com.bagicode.crudwithretrofit.api.UtilsAPI;
import com.bagicode.crudwithretrofit.modul.ReadDataResponse;
import com.bagicode.crudwithretrofit.modul.Record;
import com.bagicode.crudwithretrofit.view.activity.AddActivity;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadFragment extends Fragment {

    private List<ReadDataResponse> mCategoryDataList = new ArrayList<>();
    private List<Record> listCategory = new ArrayList<>();
    private RecyclerView rc_list_rating;

    private BaseApiService mApiService;
    private CategoryAdapter CategoryAdapter;

    private ProgressBar pb_load;
    private ShimmerRecyclerView shimmer_recycler_view;

    public ReadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_read, container, false);

        mApiService = UtilsAPI.getApiService();

        rc_list_rating = (RecyclerView) rootView.findViewById(R.id.rc_list_category);
        pb_load = (ProgressBar) rootView.findViewById(R.id.pb_load);
        shimmer_recycler_view = (ShimmerRecyclerView) rootView.findViewById(R.id.shimmer_recycler_view);

        // horizontal
        LinearLayoutManager layoutManagerCategory
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        // ini vertical
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getActivity().getApplicationContext());

        CategoryAdapter = new CategoryAdapter(listCategory, getActivity());
        rc_list_rating.setLayoutManager(mLayoutManager);
        rc_list_rating.setItemAnimator(new DefaultItemAnimator());
        rc_list_rating.setAdapter(CategoryAdapter);

        rootView.findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goAdd = new Intent(getActivity(), AddActivity.class);
                goAdd.putExtra("intent_id", "");
                goAdd.putExtra("intent_tgl", "");
                goAdd.putExtra("intent_masuk", "");
                goAdd.putExtra("intent_keluar", "");
                goAdd.putExtra("intent_ketmasuk", "");
                goAdd.putExtra("intent_ketkeluar", "");
                goAdd.putExtra("intent_action", "insert");
                startActivity(goAdd);

            }
        });

        return rootView;
    }

    private void dataAttachmentCategory() {

        rc_list_rating.setVisibility(View.GONE);

        mCategoryDataList.clear();
        listCategory.clear();
        mApiService.readData(
                "read",
                "BCA" )
                .enqueue(new Callback<ReadDataResponse>() {
                    @Override
                    public void onResponse(Call<ReadDataResponse> call, Response<ReadDataResponse> response) {

                        pb_load.setVisibility(View.GONE);
                        shimmer_recycler_view.hideShimmerAdapter();
                        if (response.isSuccessful()) {
                            try {
                                int total = response.body().getRecords().size();

                                for (int a=0; a<total; a++){
                                    Record modelSeatGroup = new Record(
                                            response.body().getRecords().get(a).getID(),
                                            response.body().getRecords().get(a).getTGL(),
                                            response.body().getRecords().get(a).getMASUK(),
                                            response.body().getRecords().get(a).getKELUAR(),
                                            response.body().getRecords().get(a).getKETERANGANMASUK(),
                                            response.body().getRecords().get(a).getKETERANGANKELUAR());
                                    listCategory.add(modelSeatGroup);

                                }

                                ReadDataResponse item = new ReadDataResponse(
                                        listCategory
                                );
                                mCategoryDataList.add(item);

                                CategoryAdapter = new CategoryAdapter(listCategory, getActivity());
                                rc_list_rating.setAdapter(CategoryAdapter);

                                if (listCategory.isEmpty()) {
                                    rc_list_rating.setVisibility(View.GONE);

                                    Toast.makeText(getActivity(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                                } else {
                                    rc_list_rating.setVisibility(View.VISIBLE);
                                }

                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }

                        } else {
                            Toast.makeText(getActivity(), "Please try again, server is down", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onFailure(Call<ReadDataResponse> call, Throwable t) {
                        pb_load.setVisibility(View.GONE);
                        shimmer_recycler_view.hideShimmerAdapter();
                        Toast.makeText(getActivity(), "Please try again, server is down onfail", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();

        pb_load.setVisibility(View.GONE);
        shimmer_recycler_view.showShimmerAdapter();

        dataAttachmentCategory();
    }
}
