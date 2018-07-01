package com.noventapp.direct.user.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.auth.UserRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.ui.main.MainActivity;
import com.noventapp.direct.user.utils.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerficationActivity extends AppCompatActivity {

    @BindView(R.id.btn_back)
    AppCompatButton btnBack;
    @BindView(R.id.et_pinCode)
    AppCompatEditText etPinCode;
    @BindView(R.id.btn_continue)
    AppCompatButton btnContinue;
    @BindView(R.id.btn_reSendCode)
    AppCompatButton btnReSendCode;
    private String firstName, lastName, email, phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfication);
        ButterKnife.bind(this);
        firstName = getIntent().getExtras().getString("first_name");
        lastName = getIntent().getExtras().getString("last_name");
        email = getIntent().getExtras().getString("email");
        phone = getIntent().getExtras().getString("phone");
        password = getIntent().getExtras().getString("password");
    }

    private void userDao(String firstName, String lastName, String email,
                         String password, String phone) {


        UserRemoteDao.getInstance().signUp(firstName, lastName, email,
                password, phone).enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                    break;

                case HttpStatus.BAD_REQUEST:
                    DialogUtil.errorMessage(this, result.getError().getMessage());
                    break;

                case HttpStatus.SERVER_ERROR:
                    DialogUtil.errorMessage(this, getString(R.string.server_error));
                    break;

                case HttpStatus.NETWORK_ERROR:
                    DialogUtil.errorMessage(this, getString(R.string.network_error));
                    break;

                default:
                    DialogUtil.errorMessage(this, getString(R.string.unexpected_error));
                    break;

            }
        });
    }

    @OnClick({R.id.btn_back, R.id.btn_continue, R.id.btn_reSendCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btn_continue:
                userDao(firstName, lastName, email, password, "+966" + phone);
                break;
            case R.id.btn_reSendCode:
                break;
        }
    }
}
