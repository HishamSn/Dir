package com.noventapp.direct.user.ui.details.info;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.model.AmenitiesModel;
import com.noventapp.direct.user.ui.base.BaseAdapter;

import java.util.List;

public class InfoAdapter extends BaseAdapter<InfoAdapter.ViewHolder> {
    private static final int ROW_SERVICE = R.layout.row_service;
    List<AmenitiesModel> serviceList;


    public InfoAdapter(List<AmenitiesModel> serviceList) {
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,
                parent, false);
        return new InfoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvService.setText(serviceList.get(position).getBaseAmenitiesName());
        try {
            loadImage(holder, serviceList.get(position).getIcon());
        } catch (Exception e) {

        }


    }

    private void loadImage(ViewHolder holder, String url) throws Exception {
        Glide.with(holder.ivService).load(url).into(holder.ivService);
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    @Override
    public int getItemViewType(int position) {

        return ROW_SERVICE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView ivService;
        AppCompatTextView tvService;

        public ViewHolder(View itemView) {
            super(itemView);
            ivService = itemView.findViewById(R.id.iv_service);
            tvService = itemView.findViewById(R.id.tv_service);
        }
    }
}
