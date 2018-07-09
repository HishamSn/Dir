package com.noventapp.direct.user.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.filter.FilterRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.model.AreaModel;
import com.noventapp.direct.user.model.CityModel;
import com.noventapp.direct.user.model.PrimeFilterCategory;
import com.noventapp.direct.user.ui.area.SelectAreaActivity;
import com.noventapp.direct.user.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.rv_categories)
    RecyclerView rvCategories;
    @BindView(R.id.et_search)
    AppCompatEditText etSearch;
    @BindView(R.id.ibtn_filter)
    AppCompatImageButton ibtnFilter;
    @BindView(R.id.btn_cancel)
    AppCompatButton btnCancel;
    List<PrimeFilterCategory> primeFilterCategoryList = new ArrayList<>();
    private DividerItemDecoration dividerDecorationVertical;
    private DividerItemDecoration dividerDecorationHorizantal;
    private Integer areaId;
    private ConstraintLayout clAddress;
    private CityModel cityModel;
    private AreaModel areaModel;
    private Bundle data;
    private View rlFilter;
    private BottomSheetBehavior behavior;
    private boolean mIsCollapsedFromBackPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getBundleData();
        setUpAppBar();
        setSearchBox();
        navigationView = findViewById(R.id.nvMain);
        init();

//        areaId = getIntent().getExtras().getInt("AREA_ID");

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
    }

    private void setSearchBox() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    btnCancel.setVisibility(View.VISIBLE);
                    ibtnFilter.setVisibility(View.GONE);
                    rvCategories.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    dividerDecorationVertical = new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
                    dividerDecorationVertical.setDrawable(MainActivity.this.getResources().getDrawable(R.drawable.shape_divider));
                    rvCategories.addItemDecoration(dividerDecorationVertical);
                    rvCategories.setAdapter(new MainAdapter());

                } else {
                    btnCancel.setVisibility(View.GONE);
                    ibtnFilter.setVisibility(View.VISIBLE);
                    rvCategories.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                    rvCategories.setAdapter(new CategorySearchAdapter(primeFilterCategoryList));


                }

            }
        });
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

    }

    @Override
    public void onBackPressed() {

        if (mIsCollapsedFromBackPress) {
            super.onBackPressed();
        } else {
            mIsCollapsedFromBackPress = true;
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        }

    }

    private void primeFilterDao() {
        FilterRemoteDao.getInstance().getPrimeList().enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    primeFilterCategoryList = result.getResult().getData();
                    rvCategories.setAdapter(new CategorySearchAdapter(primeFilterCategoryList));
                    break;
                case HttpStatus.BAD_REQUEST:
                    break;
                case HttpStatus.NETWORK_ERROR:
                    break;
                case HttpStatus.SERVER_ERROR:
                    break;


            }
        });
    }

    @OnClick({R.id.ibtn_filter, R.id.btn_cancel, R.id.et_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtn_filter:
                break;
            case R.id.btn_cancel:
                btnCancel.setVisibility(View.GONE);
                ibtnFilter.setVisibility(View.VISIBLE);
                etSearch.setText("");
                break;
            case R.id.et_search:
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                primeFilterDao();
                break;
        }
    }
}
