package com.noventapp.direct.user.ui.area.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.model.DistrictModel;
import com.noventapp.direct.user.ui.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class DistrictAdapter extends BaseAdapter<DistrictAdapter.ViewHolder> {
    List<DistrictModel> districtList = new ArrayList<>();


    public DistrictAdapter(List<DistrictModel> districtList) {
        this.districtList = districtList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DistrictAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_district, parent, false);
        return new DistrictAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDistrict.setText(districtList.get(position).getDistrictName());

    }

    @Override
    public int getItemCount() {
        return districtList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView tvDistrict;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDistrict = itemView.findViewById(R.id.tv_district);
        }
    }
}
