package com.noventapp.direct.user.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.auth.UserRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.ui.base.BaseActivity;
import com.noventapp.direct.user.ui.main.MainActivity;
import com.noventapp.direct.user.utils.DialogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements Validator.ValidationListener {

    @BindView(R.id.etEmail)
    TextInputEditText etEmail;
    @Length(min = 8, messageResId = R.string.wrong_password)
    @BindView(R.id.etPassword)
    TextInputEditText etPassword;
    @BindView(R.id.btn_forgetPass)
    AppCompatButton btnForgetPass;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;
    @BindView(R.id.btn_signUp)
    AppCompatButton btnSignUp;
    Validator validator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);


    }


    @OnClick({R.id.btn_forgetPass, R.id.btn_login, R.id.btn_signUp, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_forgetPass:
                break;
            case R.id.btn_login:
                validator.validate();
                break;
            case R.id.btn_signUp:
                startActivity(new Intent(this, SignUpActivity.class));
                break;

            case R.id.btn_back:
                onBackPressed();
                break;
        }
    }

    private void userDao(String email, String password) {
        UserRemoteDao.getInstance().login(email, password).enqueue(result -> {
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


    @Override
    public void onValidationSucceeded() {
        userDao(etEmail.getText().toString(), etPassword.getText().toString());
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof TextInputEditText) {
                ((TextInputLayout) view.getParent().getParent()).setErrorEnabled(true);
                ((TextInputLayout) view.getParent().getParent()).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }

    }
}
