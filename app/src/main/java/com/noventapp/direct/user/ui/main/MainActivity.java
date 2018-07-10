package com.noventapp.direct.user.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.NestedScrollView;
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
import com.noventapp.direct.user.data.db.DBHelper;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.model.CityAreaModel;
import com.noventapp.direct.user.model.PrimeFilterCategory;
import com.noventapp.direct.user.ui.area.SelectAreaActivity;
import com.noventapp.direct.user.ui.base.BaseActivity;
import com.noventapp.direct.user.utils.ActivityUtil;

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
    @BindView(R.id.rv_prime_filter_search)
    RecyclerView rvPrimeFilterSearch;
    @BindView(R.id.et_search)
    AppCompatEditText etSearch;
    @BindView(R.id.btn_filter)
    AppCompatImageButton btnFilter;
    @BindView(R.id.btn_cancel)
    AppCompatButton btnCancel;
    @BindView(R.id.rl_filter)
    ConstraintLayout rlFilter;
    @BindView(R.id.sv_main)
    NestedScrollView svMain;
    private List<PrimeFilterCategory> primeFilterCategoryList = new ArrayList<>();
    private DividerItemDecoration dividerDecorationVertical;
    private DividerItemDecoration dividerDecorationHorizantal;
    private ConstraintLayout clAddress;
    private BottomSheetBehavior behavior;
    private CityAreaModel cityAreaModel;
    private CategorySearchAdapter categorySearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getAreaCityDB();
        setUpAppBar();
        setSearchBox();
        init();
        setAdapter();
        setUpRecyclerView();
        setBottomSheetSearch();
        resizeView();

        primeFilterDao();

    }

    private void resizeView() {
        if (rlFilter.getLayoutParams().height <= 0) {
            svMain.post(() -> {
                rlFilter.getLayoutParams().height = svMain.getMeasuredHeight();
                rlFilter.requestLayout();
            });
        }
    }

    private void setBottomSheetSearch() {
        behavior = BottomSheetBehavior.from(rlFilter);
        behavior.setHideable(true);
        behavior.setPeekHeight(0);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    etSearch.setFocusable(true);
                    etSearch.setFocusableInTouchMode(true);
                    etSearch.requestFocus();
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                } else {
                    etSearch.setFocusable(false);
                    etSearch.setFocusableInTouchMode(false);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private void setAdapter() {
        rvPrimeFilterSearch.setAdapter(categorySearchAdapter);
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
                    btnFilter.setVisibility(View.GONE);
                    rvPrimeFilterSearch.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    dividerDecorationVertical = new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
                    dividerDecorationVertical.setDrawable(MainActivity.this.getResources().getDrawable(R.drawable.shape_divider));
                    rvPrimeFilterSearch.addItemDecoration(dividerDecorationVertical);
                    rvPrimeFilterSearch.setAdapter(new MainAdapter());

                } else {
                    btnCancel.setVisibility(View.GONE);
                    btnFilter.setVisibility(View.VISIBLE);
                    rvPrimeFilterSearch.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                    rvPrimeFilterSearch.setAdapter(categorySearchAdapter);
                }

            }
        });
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
            Intent intent = (new Intent(this, SelectAreaActivity.class));
            intent.putExtra("transition_activity", ActivityUtil.COUNTRY_ACTIVITY);
            startActivity(intent);

        });
        categorySearchAdapter = new CategorySearchAdapter(primeFilterCategoryList);
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
        if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            super.onBackPressed();
        } else {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        }

    }

    private void primeFilterDao() {
        FilterRemoteDao.getInstance().getPrimeList().enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    if (result.getResult().getCode() != 203) {
                        primeFilterCategoryList = result.getResult().getData();

                    }
                    break;
                case HttpStatus.BAD_REQUEST:
                    break;
                case HttpStatus.NETWORK_ERROR:
                    break;
                case HttpStatus.SERVER_ERROR:
                    break;


            }
        });

//        rvPrimeFilterSearch.getAdapter().notifyDataSetChanged();
    }

    @OnClick({R.id.btn_filter, R.id.btn_cancel, R.id.et_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_filter:
                break;
            case R.id.btn_cancel:
                btnCancel.setVisibility(View.GONE);
                btnFilter.setVisibility(View.VISIBLE);
                etSearch.setText("");
                break;
            case R.id.et_search:
                resizeView();
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                break;
        }
    }
}
