package com.noventapp.direct.user.ui.area;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.model.CityModel;
import com.noventapp.direct.user.model.DistrictModel;
import com.noventapp.direct.user.ui.area.adapter.CityAdapter;
import com.noventapp.direct.user.ui.area.adapter.DistrictAdapter;
import com.noventapp.direct.user.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectCityActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    AppCompatImageView ivBack;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.rv_city)
    RecyclerView rvCity;
    @BindView(R.id.et_search)
    AppCompatEditText etSearch;
    @BindView(R.id.btn_clear)
    AppCompatButton btnClear;
    @BindView(R.id.btn_search)
    AppCompatImageView btnSearch;
    CityAdapter cityAdapter;
    private ArrayList<CityModel> cityListSample = new ArrayList<>();
    private CityModel cityModel;
    private ArrayList<DistrictModel> districtListSample = new ArrayList<>();
    private DistrictModel districtModel;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((CityAdapter) rvCity.getAdapter()).onSaveInstanceState(outState);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        ButterKnife.bind(this);
        setCitySampleList();
        setDistrictSampleList();
        setUpReyclerView();
        setUpSearchBox();

    }

    private void setUpReyclerView() {
        rvCity.setLayoutManager(new LinearLayoutManager(this));
        rvCity.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        cityAdapter = new CityAdapter(this, setSampleList());
        cityAdapter.setParentClickableViewAnimationDefaultDuration();
        cityAdapter.setParentAndIconExpandOnClick(true);

        rvCity.setAdapter(cityAdapter);
    }

    private void setCitySampleList() {
        cityModel = new CityModel("Aqab");
        cityListSample.add(cityModel);
        cityModel = new CityModel("Irbd");
        cityListSample.add(cityModel);
        cityModel = new CityModel("Amman");
        cityListSample.add(cityModel);
    }

    private void setDistrictSampleList() {
        districtListSample.add(new DistrictModel("Daboq"));
        districtListSample.add(new DistrictModel("shafa badran"));
        districtListSample.add(new DistrictModel("tabarbor"));

    }

    private List<ParentObject> setSampleList() {
        CityCreator cityCreator = CityCreator.get(this, cityListSample);
        List<CityModel> getCitesList = cityCreator.getAll();
        List<ParentObject> cityList = new ArrayList<>();
        for (CityModel city : getCitesList) {
            List<Object> districtList = new ArrayList<>();
            districtList.add(new DistrictModel(city.getCityName()));
            city.setChildObjectList(districtList);
            cityList.add(city);
        }
        return cityList;


    }

    private void setUpSearchBox() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (etSearch.length() > 0) {
                    btnClear.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                }

                List<DistrictModel> districtModelList = new ArrayList<>();
                for (DistrictModel data : districtListSample) {
                    if (data.getDistrictName().toLowerCase().contains(s.toString().toLowerCase())) {
                        districtModelList.add(data);
                    }
                }

                //   .setAdapter(new DistrictAdapter(districtModelList));
            }
        });
    }

}
