package com.noventapp.direct.user.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.client.ClientRemoteDao;
import com.noventapp.direct.user.daos.remote.filter.FilterRemoteDao;
import com.noventapp.direct.user.data.db.DBHelper;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.model.CityAreaModel;
import com.noventapp.direct.user.model.ClientModel;
import com.noventapp.direct.user.model.FeaturedClient;
import com.noventapp.direct.user.model.PrimeFilterCategory;
import com.noventapp.direct.user.ui.area.SelectAreaActivity;
import com.noventapp.direct.user.ui.base.BaseActivity;
import com.noventapp.direct.user.utils.ActivityUtil;
import com.noventapp.direct.user.utils.SnackbarUtil;
import com.noventapp.direct.user.utils.viewutil.RecyclerViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.noventapp.direct.user.utils.SnackbarUtil.SnackTypes.WARNING;



/*
 * Created by Hisham Snaimeh on 5/6/2018.
 */

public class MainActivity extends BaseActivity {


    @BindView(R.id.rv_horizontal_prime_filter)
    RecyclerView rvHorizontalMostPopular;
    @BindView(R.id.rv_direct)
    RecyclerView rvDirect;
    @BindView(R.id.rv_horizontal_featured)
    RecyclerView rvHorizontalFeatured;
    @BindView(R.id.rv_more_client)
    RecyclerView rvMoreClient;
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

    private List<PrimeFilterCategory> primeFilterCategoryList;
    private List<FeaturedClient> featuredClientList;
    private List<ClientModel> topClientModelList;
    private List<ClientModel> moreCLientModelList;
    private BottomSheetBehavior bottomSheetSearch;
    private CityAreaModel cityAreaModel;
    private FeaturedAdapter featuredAdapter;
    private ClientAdapter topClientAdapter;
    private ClientAdapter moreClientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        setAdapter();
        getAreaCityDB();
        setUpAppBar();
        setUpSearchBox();
        setUpRecyclerView();
        setBottomSheetSearch();
        resizeView();
        primeFilterDao();
        featuredClientDao();
//        cityAreaModel.getId();
        clientDao();
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
        bottomSheetSearch = BottomSheetBehavior.from(rlFilter);
        bottomSheetSearch.setHideable(true);
        bottomSheetSearch.setPeekHeight(0);
        bottomSheetSearch.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
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


    private void setUpSearchBox() {
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
                    setVisibilitySearchTyping(View.VISIBLE, View.GONE);
                    rvPrimeFilterSearch.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    rvPrimeFilterSearch.setAdapter(new ClientAdapter());

                } else {
                    setVisibilitySearchTyping(View.GONE, View.VISIBLE);
                    rvPrimeFilterSearch.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                    rvPrimeFilterSearch.setAdapter(new CategorySearchAdapter(primeFilterCategoryList, true));

                }

            }

            private void setVisibilitySearchTyping(int visible, int gone) {
                btnCancel.setVisibility(visible);
                btnFilter.setVisibility(gone);
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
                findViewById(R.id.cl_parent)
        );
        if (cityAreaModel != null) {
            tvNameArea.setText(cityAreaModel.getBaseCityName() + " - " + cityAreaModel.getBaseAreaName());

        }
    }


    private void setAdapter() {
        rvPrimeFilterSearch.setAdapter(new CategorySearchAdapter(primeFilterCategoryList, true));
        rvHorizontalMostPopular.setAdapter(new CategorySearchAdapter(primeFilterCategoryList, false));
        rvHorizontalFeatured.setAdapter(featuredAdapter);
        rvDirect.setAdapter(topClientAdapter);
        rvMoreClient.setAdapter(moreClientAdapter);

    }

    private void init() {
        primeFilterCategoryList = new ArrayList<>();
        featuredClientList = new ArrayList<>();
        topClientModelList = new ArrayList<>();
        moreCLientModelList = new ArrayList<>();
        featuredAdapter = new FeaturedAdapter(featuredClientList);
        topClientAdapter = new ClientAdapter(topClientModelList);
        moreClientAdapter = new ClientAdapter(moreCLientModelList);
    }


    private void setUpRecyclerView() {
        RecyclerViewUtil.addItemDecoration(rvDirect, true);
        RecyclerViewUtil.addItemDecoration(rvMoreClient, true);
        RecyclerViewUtil.addItemDecoration(rvHorizontalMostPopular, false);
        RecyclerViewUtil.addItemDecoration(rvHorizontalFeatured, false);


        rvHorizontalFeatured.setAdapter(featuredAdapter);
        rvDirect.setAdapter(new ClientAdapter());
        rvMoreClient.setAdapter(new ClientAdapter());

    }

    private void featuredClientDao() {

        FilterRemoteDao.getInstance().getFeaturedClient().enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    if (result.getResult().getCode() != 204) {
                        featuredClientList.clear();
                        featuredClientList.addAll(result.getResult().getData());
                        featuredAdapter.notifyDataSetChanged();

                    } else {
                        SnackbarUtil.showDefaultSnackBar(MainActivity.this, getString(R.string.empty_data), false, WARNING);
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
    }


    private void clientDao() {

        ClientRemoteDao.getInstance().getAllClient(3).enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    if (result.getResult().getCode() != 204) {

                        if (result.getResult().getSize() > 10) {
                            moreCLientModelList.clear();
                            topClientModelList.clear();
                            topClientModelList.addAll(result.getResult().getData().subList(0, 10));

                            moreCLientModelList.addAll(result.getResult().getData().subList(11, result.getResult().getSize()));
                            moreClientAdapter.notifyDataSetChanged();
                        } else {
                            topClientModelList.clear();
                            topClientModelList.addAll(result.getResult().getData().subList(0, result.getResult().getSize()));
                        }

                        topClientAdapter.notifyDataSetChanged();

                    } else {
                        SnackbarUtil.showDefaultSnackBar(MainActivity.this, getString(R.string.empty_data), false, WARNING);
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
    }

    private void primeFilterDao() {
        FilterRemoteDao.getInstance().getPrimeList().enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    if (result.getResult().getCode() != 204) {
                        primeFilterCategoryList.clear();
                        primeFilterCategoryList.addAll(result.getResult().getData());
                        rvPrimeFilterSearch.getAdapter().notifyDataSetChanged();
                        rvHorizontalMostPopular.getAdapter().notifyDataSetChanged();
                    } else {
                        SnackbarUtil.showDefaultSnackBar(MainActivity.this, getString(R.string.empty_data), false, WARNING);
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

    }

    @OnClick({R.id.btn_filter, R.id.btn_cancel, R.id.et_search, R.id.cl_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_filter:
                break;
            case R.id.btn_cancel:
                btnCancel.setVisibility(View.GONE);
                btnFilter.setVisibility(View.VISIBLE);
                etSearch.setText("");
//                bottomSheetSearch.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.et_search:
                resizeView();
                bottomSheetSearch.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.cl_address:
                Intent intent = (new Intent(this, SelectAreaActivity.class));
                intent.putExtra("transition_activity", ActivityUtil.COUNTRY_ACTIVITY);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        if (bottomSheetSearch.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            super.onBackPressed();
        } else {
            bottomSheetSearch.setState(BottomSheetBehavior.STATE_COLLAPSED);

        }

    }

}
