package com.noventapp.direct.user.ui.address.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.model.CityModel;
import com.noventapp.direct.user.model.DistrictModel;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends ExpandableRecyclerAdapter<CityViewHolder, DistrictViewHolder> {
    Context context;
    LayoutInflater inflater;
    private ArrayList<DistrictModel> districtList = new ArrayList<>();

    public CityAdapter(Context context, List<ParentObject> cityList) {
        super(context, cityList);
        inflater = LayoutInflater.from(context);
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
        districtList.add(obj);
        districtViewHolder.rvDistrict.setLayoutManager(new LinearLayoutManager(context));
        districtViewHolder.rvDistrict.setAdapter(new DistrictAdapter(districtList));

    }



}
