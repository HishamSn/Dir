package com.noventapp.direct.user.ui.details.info;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.model.ServiceModel;
import com.noventapp.direct.user.ui.base.BaseAdapter;

import java.util.List;

public class InfoAdapter extends BaseAdapter<InfoAdapter.ViewHolder> {
    List<ServiceModel> serviceList;
    private static final int ROW_SERVICE = R.layout.row_service;


//    public InfoAdapter(List<ServiceModel> serviceList) {
//        this.serviceList = serviceList;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,
                parent, false);
        return new InfoAdapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {

        return ROW_SERVICE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
