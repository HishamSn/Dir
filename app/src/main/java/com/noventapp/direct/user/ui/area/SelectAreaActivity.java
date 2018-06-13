package com.noventapp.direct.user.ui.area;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.city.CityRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.model.CityModel;
import com.noventapp.direct.user.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectAreaActivity extends BaseActivity {


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
    @BindView(R.id.btn_back)
    AppCompatButton btnBack;

    private AreaExpandableAdapter areaExpandableAdapter;
    private List<CityModel> cityModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        ButterKnife.bind(this);
        init();
        toolbarTitle.setText(R.string.select_area);

        cityDao();
        setUpSearchBox();
//        expandAll();
    }

    private void init() {
        cityModelList = new ArrayList<>();
    }

    private void setUpExpandableListView() {
        if (areaExpandableAdapter == null) {
            areaExpandableAdapter = new AreaExpandableAdapter(this, cityModelList);
            elvCity.setAdapter(areaExpandableAdapter);
        } else {
            areaExpandableAdapter.notifyDataSetChanged();
        }
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
                if (s.length() > 0) {
                    btnClear.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    btnClear.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }
                if (s.toString().isEmpty()) {
                    collapseAll();
                } else {
                    expandAll();
                }
                areaExpandableAdapter.filterData(s.toString());

            }
        });
    }


    @OnClick({R.id.btn_back, R.id.btn_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btn_clear:
                etSearch.setText("");
                btnSearch.setVisibility(View.VISIBLE);
                btnClear.setVisibility(View.GONE);
                break;
        }
    }

    private void cityDao() {
        CityRemoteDao.getInstance().getList(1).enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    cityModelList.clear();
                    cityModelList.addAll(result.getResult().getData());
                    setUpExpandableListView();
                    break;
            }
        });
    }
}
