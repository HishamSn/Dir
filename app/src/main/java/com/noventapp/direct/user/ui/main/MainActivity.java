package com.noventapp.direct.user.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.noventapp.direct.user.R;
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
    private DividerItemDecoration dividerDecorationVertical;
    private DividerItemDecoration dividerDecorationHorizantal;
    Context context = this;
    NavigationView navigationView;
    private Integer areaId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setNavigation(
                findViewById(R.id.nvMain),
                findViewById(R.id.toolbar),
                findViewById(R.id.dlMain)
        );

        navigationView = findViewById(R.id.nvMain);


        areaId = getIntent().getExtras().getInt("AREA_ID");


        setUpRecyclerView();


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

}
