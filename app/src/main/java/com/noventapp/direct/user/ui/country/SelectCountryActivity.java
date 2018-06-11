package com.noventapp.direct.user.ui.country;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.country.CountryRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.model.CountryModel;
import com.noventapp.direct.user.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.btn_clear)
    AppCompatButton btnClear;
    @BindView(R.id.btn_search)
    AppCompatButton btnSearch;
    private List<CountryModel> countryList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);
        ButterKnife.bind(this);
        setUpRecyclerView();
        toolbarTitle.setText(R.string.select_country);
        getCountryDao();
        setUpSearchBox();


    }


    private void getCountryDao() {
        CountryRemoteDao.getInstance().getList().enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    countryList = result.getResult().getData();
                    rvCountry.setAdapter(new CountryAdapter(countryList));
                    break;
                default:
                    break;

            }
        });
    }

    private void setUpRecyclerView() {
        //  rvCountry.setLayoutManager(new LinearLayoutManager(this));
        rvCountry.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

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

                if (etSearch.length() > 0) {
                    btnClear.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                }

                ArrayList<CountryModel> nameTest = new ArrayList<>();
                for (CountryModel data : countryList) {
                    if (data.getBaseCountyName().toLowerCase().startsWith(s.toString().toLowerCase())) {
                        nameTest.add(data);
                    }
                }

                rvCountry.setAdapter(new CountryAdapter(nameTest));
            }
        });
    }


    @OnClick({R.id.btn_back, R.id.et_search, R.id.btn_clear, R.id.btn_search})
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
}
