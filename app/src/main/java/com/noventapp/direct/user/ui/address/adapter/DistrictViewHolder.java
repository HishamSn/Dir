package com.noventapp.direct.user.ui.address.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.noventapp.direct.user.R;

public class DistrictViewHolder extends ChildViewHolder {
    public RecyclerView rvDistrict;

    public DistrictViewHolder(View itemView) {
        super(itemView);
        rvDistrict = itemView.findViewById(R.id.rv_district);
    }
}
