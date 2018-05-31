package com.noventapp.direct.user.ui.address.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.noventapp.direct.user.R;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends ExpandableRecyclerAdapter<CityViewHolder, DistrictViewHolder> {
    private static final int ROW_CITY = R.layout.row_city;
    Context context;
    LayoutInflater inflater;
    private ArrayList<String> cityList = new ArrayList<>();

    public CityAdapter(Context context, List<ParentObject> cityList) {
        super(context, cityList);
        inflater = LayoutInflater.from(context);
        //  this.cityList = cityList;
        //notifyDataSetChanged();
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
        String obj = (String) o;
        cityViewHolder.tvCity.setText(obj);


    }

    @Override
    public void onBindChildViewHolder(DistrictViewHolder districtViewHolder, int i, Object o) {
        String obj = (String) o;
        districtViewHolder.rvDistrict.setAdapter(new DistrictAdapter(context, cityList));

    }


    @Override
    public int getItemCount() {
        return cityList.size();
    }

    @Override
    public int getItemViewType(int position) {

        return ROW_CITY;
    }


}
