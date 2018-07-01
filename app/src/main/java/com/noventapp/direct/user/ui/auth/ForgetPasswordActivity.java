package com.noventapp.direct.user.ui.auth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.auth.UserRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.utils.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    @BindView(R.id.et_email)
    AppCompatEditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_back, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btn_send:
                break;
        }
    }

    private void userDaoEmail(String email) {
        UserRemoteDao.getInstance().checkEmail(email)
                .enqueue(result -> {

                    switch (result.getStatus()) {
                        case HttpStatus.SUCCESS:
                            DialogUtil.errorMessage(this, result.getResult().getMessage());
                            break;

                        case HttpStatus.BAD_REQUEST:
                            // DialogUtil.errorMessage(this, result.getError().getMessage());
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

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            userDaoEmail(etEmail.getText().toString());
        }

    }
}
