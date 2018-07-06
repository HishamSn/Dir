package com.noventapp.direct.user.ui.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.address.AddressRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.model.AddressModel;
import com.noventapp.direct.user.utils.DialogUtil;
import com.noventapp.direct.user.utils.SessionUtils;

import java.util.List;

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
    @BindView(R.id.iv_book)
    AppCompatImageView ivBook;
    @BindView(R.id.tv_noAddress)
    AppCompatTextView tvNoAddress;
    ConstraintLayout noAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        noAddress = findViewById(R.id.noAddress);
        ButterKnife.bind(this);
        toolbarTitle.setText(R.string.my_addresses);
        if (SessionUtils.getInstance().getUser() != null) {
            addressDao(SessionUtils.getInstance().getUser().getId());
        } else {
            DialogUtil.errorMessage(this, getString(R.string.unexpected_error), true);
        }


    }

    private void setRecyclerView(List<AddressModel> data) {
        rvAddress.setVisibility(View.VISIBLE);
        rvAddress.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvAddress.setAdapter(new AddressAdapter(data));

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


    private void addressDao(Integer id) {
        AddressRemoteDao.getInstance().getAddressList(id).enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    if (result.getResult().getData().isEmpty()) {
                        noAddress.setVisibility(View.VISIBLE);
                    } else {
                        setRecyclerView(result.getResult().getData());
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
}
