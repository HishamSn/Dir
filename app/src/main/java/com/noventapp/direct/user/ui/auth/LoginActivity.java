package com.noventapp.direct.user.ui.auth;

import android.content.Intent;
import android.graphics.Color;
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
import com.noventapp.direct.user.model.UserModel;
import com.noventapp.direct.user.ui.base.BaseActivity;
import com.noventapp.direct.user.ui.main.MainActivity;
import com.noventapp.direct.user.utils.DialogUtil;
import com.noventapp.direct.user.utils.JWTUtils;
import com.noventapp.direct.user.utils.MoshiUtil;
import com.noventapp.direct.user.utils.SessionUtils;
import com.squareup.moshi.JsonAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.noventapp.direct.user.utils.JWTUtils.getJson;

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
    UserModel userModel;
    SweetAlertDialog pDialog;

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
                    String payloadToken;
                    try {
                        payloadToken = (JWTUtils.decodeJWT(result.getResult().getAccessToken())[1]);
                        JsonAdapter<UserModel> userJsonAdapter = MoshiUtil.getInstance().adapter(UserModel.class);
                        userModel = userJsonAdapter.fromJson(getJson(payloadToken));
                        userModel.setToken(result.getResult().getAccessToken());
                        SessionUtils.getInstance().login(userModel);
                        pDialog.dismiss();

                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } catch (Exception e) {
                        DialogUtil.errorMessage(this, getString(R.string.unexpected_error),
                                e.getMessage().toString());
                    }

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

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#9c27b0"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
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
