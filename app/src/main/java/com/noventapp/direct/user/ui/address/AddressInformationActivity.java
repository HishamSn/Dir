package com.noventapp.direct.user.ui.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.address.AddressRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.utils.DialogUtil;
import com.noventapp.direct.user.utils.SessionUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressInformationActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.btn_back)
    AppCompatButton btnBack;
    @BindView(R.id.et_addressName)
    AppCompatEditText etAddressName;
    @BindView(R.id.et_buildingNum)
    AppCompatEditText etBuildingNum;
    @BindView(R.id.et_floorNum)
    AppCompatEditText etFloorNum;
    @BindView(R.id.et_appartmentNum)
    AppCompatEditText etAppartmentNum;
    @BindView(R.id.btn_addAddress)
    AppCompatButton btnAddAddress;
    double lat, lng;
    private int addressId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_information);
        ButterKnife.bind(this);
        toolbarTitle.setText(R.string.address_info);
        getIntentData();
    }

    private void getIntentData() {
        lat = getIntent().getExtras().getDouble("lat");
        lng = getIntent().getExtras().getDouble("lng");
        addressId = getIntent().getExtras().getInt("address_id");
        if (addressId != 0) {
            getAddressInfo(addressId);
        }
    }

    @OnClick({R.id.btn_back, R.id.btn_addAddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btn_addAddress:
                if (addressId != 0) {
                    updateAddressDao(etAddressName.getText().toString(), etBuildingNum.getText().toString()
                            , etFloorNum.getText().toString(), etAppartmentNum.getText().toString(),
                            lat, lng, SessionUtils.getInstance().getUser().getId(), addressId);
                } else {
                    createAddressDao(etAddressName.getText().toString(), etBuildingNum.getText().toString()
                            , etFloorNum.getText().toString(), etAppartmentNum.getText().toString(),
                            lat, lng, SessionUtils.getInstance().getUser().getId());
                }

                break;
        }
    }

    private void createAddressDao(String addressName, String buildingNum, String floorNum,
                                  String apartmentNum, Double lat, Double lng, Integer customerId) {
        AddressRemoteDao.getInstance().createAddress(addressName, buildingNum, floorNum,
                apartmentNum, lat, lng, customerId).enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    Toast.makeText(this, result.getResult().getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MyAddressActivity.class));
                    break;
                case HttpStatus.BAD_REQUEST:
                    DialogUtil.errorMessage(this, result.getResult().getMessage());
                    break;

                case HttpStatus.NETWORK_ERROR:
                    break;
                case HttpStatus.SERVER_ERROR:
                    break;

            }
        });
    }

    private void updateAddressDao(String addressName, String buildingNum, String floorNum,
                                  String apartmentNum, Double lat, Double lng, Integer customerId, Integer addressId) {
        AddressRemoteDao.getInstance().updateAddress(addressId, addressName, buildingNum, floorNum,
                apartmentNum, lat, lng, customerId).enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    Toast.makeText(this, result.getResult().getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MyAddressActivity.class));
                    break;
                case HttpStatus.BAD_REQUEST:
                    DialogUtil.errorMessage(this, result.getResult().getMessage());
                    break;

                case HttpStatus.NETWORK_ERROR:
                    break;
                case HttpStatus.SERVER_ERROR:
                    break;

            }

        });
    }

    private void getAddressInfo(Integer addressId) {
        AddressRemoteDao.getInstance().getAddressInfo(addressId).enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    etAddressName.setText(result.getResult().getData().getAddressName());
                    etAppartmentNum.setText(result.getResult().getData().getApartmentNumber() + "");
                    etBuildingNum.setText(result.getResult().getData().getBuildingNumber() + "");
                    etFloorNum.setText(result.getResult().getData().getFloorNumber() + "");
                    break;
                case HttpStatus.BAD_REQUEST:
                    DialogUtil.errorMessage(this, result.getResult().getMessage());
                    break;
                case HttpStatus.NETWORK_ERROR:
                    break;
                case HttpStatus.SERVER_ERROR:
                    break;

            }
        });
    }
}
