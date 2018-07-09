package com.noventapp.direct.user.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.data.db.DBHelper;
import com.noventapp.direct.user.model.CityAreaModel;
import com.noventapp.direct.user.ui.area.SelectAreaActivity;
import com.noventapp.direct.user.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;



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
    private DividerItemDecoration dividerDecorationVertical;
    private DividerItemDecoration dividerDecorationHorizantal;
    private Integer areaId;
    private ConstraintLayout clAddress;

    private CityAreaModel cityAreaModel;
    private View rlFilter;
    private BottomSheetBehavior behavior;
    private boolean mIsCollapsedFromBackPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getAreaCityDB();
        setUpAppBar();
        navigationView = findViewById(R.id.nvMain);
        init();

        setUpRecyclerView();


        View rlFilter = findViewById(R.id.rl_filter);
        View svMain = findViewById(R.id.sv_main);

        behavior = BottomSheetBehavior.from(rlFilter);
        behavior.setHideable(true);
        behavior.setPeekHeight(0);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED && mIsCollapsedFromBackPress) {
                    mIsCollapsedFromBackPress = false;
                    onBackPressed();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
            }
        });

        svMain.post(() -> {
            rlFilter.getLayoutParams().height = svMain.getMeasuredHeight();
            rlFilter.requestLayout();
        });
        findViewById(R.id.et_search).setOnClickListener(v -> behavior.setState(BottomSheetBehavior.STATE_EXPANDED));
    }

    private void getAreaCityDB() {
        cityAreaModel = DBHelper.getInstance().getFirst(CityAreaModel.class);
    }

    private void setUpAppBar() {
        setNavigation(
                findViewById(R.id.nvMain),
                findViewById(R.id.toolbar),
                findViewById(R.id.dlMain)
        );
        if (cityAreaModel != null) {
            tvNameArea.setText(cityAreaModel.getBaseCityName() + " - " + cityAreaModel.getBaseAreaName());

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

        rvHorizontalMostPopular.setAdapter(new MostPopularAdapter());
        rvHorizontalTopSelling.setAdapter(new TopSellingAdapter());
        rvDirect.setAdapter(new MainAdapter());
        rvTop.setAdapter(new MainAdapter());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        mIsCollapsedFromBackPress = true;
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

    }
}
