package com.noventapp.direct.user.ui.area;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.model.CityModel;
import com.noventapp.direct.user.model.DistrictModel;
import com.noventapp.direct.user.ui.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectCityActivity extends BaseActivity {


    @BindView(R.id.btn_back)
    AppCompatButton btnBack;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.et_search)
    AppCompatEditText etSearch;
    @BindView(R.id.btn_clear)
    AppCompatButton btnClear;
    @BindView(R.id.btn_search)
    AppCompatImageView btnSearch;
    @BindView(R.id.elv_city)
    ExpandableListView elvCity;

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    private AreaExpandableAdapter areaExpandableAdapter;
    private ArrayList<CityModel> cityModelList = new ArrayList<CityModel>();
    private ArrayList<CityModel> showTheseParentList = new ArrayList<CityModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        ButterKnife.bind(this);
        toolbarTitle.setText(R.string.select_area);


        fillSampleData();
        setUpExpandableListView();
        setUpSearchBox();
//        expandAll();
    }

    private void setUpExpandableListView() {
        areaExpandableAdapter = new AreaExpandableAdapter(this, cityModelList);
        elvCity.setAdapter(areaExpandableAdapter);
    }


    private void expandAll() {

        for (int i = 0; i < areaExpandableAdapter.getGroupCount(); i++) {
            elvCity.expandGroup(i);
        } //end for (int i = 0; i < count; i++)
    }

    private void collapseAll() {

        for (int i = 0; i < areaExpandableAdapter.getGroupCount(); i++) {
            elvCity.collapseGroup(i);
        }
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
                if (s.toString().isEmpty()) {
                    collapseAll();
                } else {
                    expandAll();
                }
                areaExpandableAdapter.filterData(s.toString());

            }
        });
    }

    private void fillSampleData() {
        ArrayList<DistrictModel> districtModels = new ArrayList<DistrictModel>();
        CityModel cityModel = null;


        for (int i = 0; i < 15; i++) {
            districtModels.add(new DistrictModel("Amman"));
            districtModels.add(new DistrictModel("Zarqa"));
            cityModel = new CityModel("Jordan", districtModels);
            cityModelList.add(cityModel);

            districtModels = new ArrayList<DistrictModel>();
            districtModels.add(new DistrictModel("new york"));
            districtModels.add(new DistrictModel("dleal"));

            cityModel = new CityModel("US", districtModels);
            cityModelList.add(cityModel);
        }
    }


}
