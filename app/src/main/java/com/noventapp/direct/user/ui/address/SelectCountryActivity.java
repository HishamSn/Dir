package com.noventapp.direct.user.ui.address;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.ui.address.adapter.CountryAdapter;
import com.noventapp.direct.user.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectCountryActivity extends BaseActivity {

    @BindView(R.id.rv_country)
    RecyclerView rvCountry;
    @BindView(R.id.et_search)
    AppCompatEditText etSearch;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.iv_back)
    AppCompatImageView ivBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);
        ButterKnife.bind(this);
        setUpRecyclerView();
        toolbarTitle.setText(R.string.select_country);


    }

    private void setUpRecyclerView() {
        rvCountry.setLayoutManager(new LinearLayoutManager(this));
        rvCountry.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvCountry.setAdapter(new CountryAdapter());
    }

    @OnClick(R.id.et_search)
    public void onViewClicked() {
    }
}
