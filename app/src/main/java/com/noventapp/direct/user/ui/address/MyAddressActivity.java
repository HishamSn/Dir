package com.noventapp.direct.user.ui.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.noventapp.direct.user.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAddressActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.btn_back)
    AppCompatButton btnBack;
    @BindView(R.id.rv_address)
    RecyclerView rvAddress;
    @BindView(R.id.btn_addAddress)
    AppCompatButton btnAddAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        ButterKnife.bind(this);
        toolbarTitle.setText(R.string.my_addresses);
        setRecyclerView();
    }

    private void setRecyclerView() {
        rvAddress.setVisibility(View.VISIBLE);
        rvAddress.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvAddress.setAdapter(new AddressAdapter());
    }

    @OnClick({R.id.btn_back, R.id.btn_addAddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btn_addAddress:
                startActivity(new Intent(this, AddressMapActivity.class));
                break;
        }
    }
}
