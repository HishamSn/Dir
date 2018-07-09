package com.noventapp.direct.user.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.model.AreaModel;
import com.noventapp.direct.user.model.CityModel;
import com.noventapp.direct.user.ui.area.SelectAreaActivity;
import com.noventapp.direct.user.ui.base.BaseActivity;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



/*
 * Created by Hisham Snaimeh on 5/6/2018.
 */

public class MainActivity extends BaseActivity {


    @BindView(R.id.rv_horizontal_most_popular)
    RecyclerView rvHorizontalMostPopular;
    @BindView(R.id.rv_direct)
    RecyclerView rvDirect;
    @BindView(R.id.rv_horizontal_top_selling)
    RecyclerView rvHorizontalTopSelling;
    @BindView(R.id.rv_top)
    RecyclerView rvTop;
    Context context = this;
    NavigationView navigationView;
    @BindView(R.id.tv_name_area)
    AppCompatTextView tvNameArea;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.SlidingUpPanelLayout_layout_add)
    SlidingUpPanelLayout SlidingUpPanelLayoutLayoutAdd;
    @BindView(R.id.rv_categories)
    RecyclerView rvCategories;
    private DividerItemDecoration dividerDecorationVertical;
    private DividerItemDecoration dividerDecorationHorizantal;
    private Integer areaId;
    private ConstraintLayout clAddress;

    private CityModel cityModel;
    private AreaModel areaModel;
    private Bundle data;
    private boolean isExpanded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sliding);
        ButterKnife.bind(this);
        getBundleData();
        setUpAppBar();
        navigationView = findViewById(R.id.nvMain);
        init();

//        areaId = getIntent().getExtras().getInt("AREA_ID");

        setUpRecyclerView();
    }

    private void getBundleData() {
        data = getIntent().getExtras();
        if (data != null) {
            areaModel = data.getParcelable("area_model");
            cityModel = data.getParcelable("city_model");
        }

    }

    private void setUpAppBar() {
        setNavigation(
                findViewById(R.id.nvMain),
                findViewById(R.id.toolbar),
                findViewById(R.id.dlMain)
        );
        if (data != null) {
            tvNameArea.setText(cityModel.getBaseCityName() + " - " + areaModel.getBaseAreaName());

        }
    }

    private void init() {

        clAddress = findViewById(R.id.cl_address);
        clAddress.setOnClickListener(v -> {
            startActivity(new Intent(this, SelectAreaActivity.class));
        });


    }

    public void setUpNavigationHeader() {
//        View headerView = navigationView.inflateHeaderView(R.layout.nav_header);
//        Button btnLogin = headerView.findViewById(R.id.btn_login);
//        btnLogin.setOnClickListener(v -> startActivity(new Intent(context, LoginActivity.class)));
    }

    private void setUpRecyclerView() {
        dividerDecorationVertical = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        dividerDecorationHorizantal = new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL);
        dividerDecorationVertical.setDrawable(this.getResources().getDrawable(R.drawable.shape_divider));
        dividerDecorationHorizantal.setDrawable(this.getResources().getDrawable(R.drawable.shape_divider));
        rvHorizontalMostPopular.addItemDecoration(dividerDecorationHorizantal);
        rvHorizontalTopSelling.addItemDecoration(dividerDecorationHorizantal);
        rvDirect.addItemDecoration(dividerDecorationVertical);
        rvTop.addItemDecoration(dividerDecorationVertical);


        rvHorizontalMostPopular.setAdapter(new MostPopularAdapter(true));
        rvHorizontalTopSelling.setAdapter(new TopSellingAdapter());
        rvDirect.setAdapter(new MainAdapter());
        rvTop.setAdapter(new MainAdapter());
        rvCategories.setAdapter(new CategorySearchAdapter(true));
    }


    @OnClick(R.id.btn_search)
    public void onViewClicked() {
        // cartAdapter.notifyDataSetChanged();
        SlidingUpPanelLayoutLayoutAdd.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        isExpanded = true;
    }

    @OnClick({R.id.et_search, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_search:
                break;
            case R.id.btn_cancel:
                break;
        }
    }
}
