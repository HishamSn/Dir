package com.noventapp.direct.user.ui.area.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.github.zagum.expandicon.ExpandIconView;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.model.CityModel;
import com.noventapp.direct.user.model.DistrictModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CityAdapter extends ExpandableRecyclerAdapter<CityAdapter.CityViewHolder, CityAdapter.DistrictViewHolder> {
    Context context;
    LayoutInflater inflater;
    private ArrayList<DistrictModel> districtList = new ArrayList<>();


    public CityAdapter(Context context, List<ParentObject> cityList) {
        super(context, cityList);
        inflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public CityViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.row_city, viewGroup, false);
        return new CityViewHolder(view);
    }

    @Override
    public DistrictViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.recycler_district, viewGroup, false);
        return new DistrictViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(CityViewHolder cityViewHolder, int i, Object o) {
        CityModel cityModel = (CityModel) o;
        cityViewHolder.tvCity.setText(cityModel.getCityName());


    }

    @Override
    public void onBindChildViewHolder(DistrictViewHolder districtViewHolder, int i, Object o) {
        DistrictModel obj = (DistrictModel) o;
        districtViewHolder.rvDistrict.setLayoutManager(new LinearLayoutManager(context));
        districtViewHolder.rvDistrict.setAdapter(new DistrictAdapter(Collections.singletonList(obj)));


    }

    class CityViewHolder extends ParentViewHolder {
        public AppCompatTextView tvCity;
        //    public AppCompatImageView ivDown;
        public ExpandIconView ivDown;


        public CityViewHolder(View itemView) {
            super(itemView);
            tvCity = itemView.findViewById(R.id.tv_city);
            ivDown = itemView.findViewById(R.id.iv_down);
        }
    }

    class DistrictViewHolder extends ChildViewHolder {
        public RecyclerView rvDistrict;

        public DistrictViewHolder(View itemView) {
            super(itemView);
            rvDistrict = itemView.findViewById(R.id.rv_district);
        }
    }


}
