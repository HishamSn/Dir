package com.noventapp.direct.user.ui.address.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.ui.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class DistrictAdapter extends BaseAdapter<DistrictAdapter.ViewHolder> {
    List<String> districtList = new ArrayList<>();
    Context context;

    public DistrictAdapter(Context context, List<String> districtList) {
        this.districtList = districtList;
        this.context = context;
    }


    @NonNull
    @Override
    public DistrictAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_district, parent, false);
        return new DistrictAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDistrict.setText(districtList.get(position));

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
