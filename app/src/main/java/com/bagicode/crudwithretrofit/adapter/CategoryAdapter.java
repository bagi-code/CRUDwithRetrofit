package com.bagicode.crudwithretrofit.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bagicode.crudwithretrofit.R;
import com.bagicode.crudwithretrofit.modul.Record;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private List<Record> moviesList;
    private Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView keterangan, tanggal;

        public MyViewHolder(View view) {
            super(view);
            keterangan = view.findViewById(R.id.keterangan);
            tanggal = view.findViewById(R.id.tanggal);
        }
    }

    public CategoryAdapter(List<Record> moviesList, Activity activity) {
        this.moviesList = moviesList;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Record movie = moviesList.get(position);


        holder.keterangan.setText(movie.getKETERANGANMASUK());
        holder.tanggal.setText(movie.getTGL());

//        holder.ll_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent goDetail = new Intent(activity, WisataDetailActivity.class);
//                goDetail.putExtra("intent_title", movie.getTitle());
//                goDetail.putExtra("intent_desk", movie.getDesk());
//                goDetail.putExtra("intent_url", movie.getGambar());
//                activity.startActivity(goDetail);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}
