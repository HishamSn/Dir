package com.noventapp.direct.user.ui.area;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.city.CityRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.data.prefs.PrefsUtils;
import com.noventapp.direct.user.model.CityModel;
import com.noventapp.direct.user.ui.base.BaseActivity;
import com.noventapp.direct.user.ui.country.SelectCountryActivity;
import com.noventapp.direct.user.utils.ActivityUtil;
import com.noventapp.direct.user.utils.DialogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class SelectAreaActivity extends BaseActivity {


    @BindView(R.id.tv_location_help_me)
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
    @BindView(R.id.btn_change_country)
    AppCompatButton btnChangeCountry;
    @BindView(R.id.view_location)
    View viewLocation;
    private SweetAlertDialog dialogProgress;


    private AreaExpandableAdapter areaExpandableAdapter;
    private List<CityModel> cityModelList;
    private int transitionActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_area);
        ButterKnife.bind(this);
        init();
        toolbarTitle.setText(R.string.select_area);
        dialogProgress = DialogUtil.progress(this);
        dialogProgress.show();
        cityDao();
        setUpSearchBox();
        setVisibility();

//        expandAll();
    }

    private void setVisibility() {
        transitionActivity = getIntent().getIntExtra("transition_activity", -1);

        if (transitionActivity == ActivityUtil.COUNTRY_ACTIVITY) {
            btnChangeCountry.setVisibility(View.VISIBLE);
            toolbarTitle.setGravity(Gravity.START);
            toolbarTitle.setPadding(5, 0, 0, 0);
        }
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


    @OnClick({R.id.btn_back, R.id.btn_clear, R.id.btn_change_country, R.id.view_location})
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
            case R.id.btn_change_country:
                startActivity(new Intent(this, SelectCountryActivity.class));
                finish();
                break;
            case R.id.view_location:
                DialogUtil.displayPromptForEnablingGPS(this);
                break;
        }
    }

    private void cityDao() {
        CityRemoteDao.getInstance().getList(PrefsUtils.getInstance().getCountryId()).enqueue(result -> {
            dialogProgress.dismiss();
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    cityModelList.clear();
                    cityModelList.addAll(result.getResult().getData());
                    setUpExpandableListView();
                    break;
                default:
                    DialogUtil.errorMessage(this,
                            result.getError().getTitle(),
                            result.getError().getMessage(), true);

            }
        });
    }
}
