package com.noventapp.direct.user.ui.address;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.model.CityModel;
import com.noventapp.direct.user.model.DistrictModel;
import com.noventapp.direct.user.ui.address.adapter.CityAdapter;
import com.noventapp.direct.user.ui.address.adapter.CityCreator;
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
    private ArrayList<CityModel> cityListSample = new ArrayList<>();
    private CityModel cityModel;


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
        setUpReyclerView();

    }

    private void setUpReyclerView() {
        rvCity.setLayoutManager(new LinearLayoutManager(this));
        rvCity.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        CityAdapter cityAdapter = new CityAdapter(this, setSampleList());
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

    private List<ParentObject> setSampleList() {
        CityCreator cityCreator = CityCreator.get(this);
        List<CityModel> list = cityCreator.getAll();
        List<ParentObject> cityList = new ArrayList<>();
        for (CityModel city : list) {
            List<Object> childList = new ArrayList<>();
            childList.add(new DistrictModel("Amman"));

            city.setChildObjectList(childList);
            cityList.add(city);
        }
        return cityList;


    }
}
