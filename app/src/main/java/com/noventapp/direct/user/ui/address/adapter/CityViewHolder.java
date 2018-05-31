package com.noventapp.direct.user.ui.address.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.noventapp.direct.user.R;

class CityViewHolder extends ParentViewHolder {
    public AppCompatTextView tvCity;
    public AppCompatImageView ivDown;

    public CityViewHolder(View itemView) {
        super(itemView);
        tvCity = itemView.findViewById(R.id.tv_city);
        ivDown = itemView.findViewById(R.id.iv_down);
    }
}
