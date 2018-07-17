package com.noventapp.direct.user.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.auth.UserRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.model.UserModel;
import com.noventapp.direct.user.ui.base.BaseActivity;
import com.noventapp.direct.user.ui.main.MainActivity;
import com.noventapp.direct.user.utils.DialogUtil;
import com.noventapp.direct.user.utils.JwtUtils;
import com.noventapp.direct.user.utils.MoshiUtil;
import com.noventapp.direct.user.utils.SessionUtils;
import com.noventapp.direct.user.utils.SnackbarUtil;
import com.squareup.moshi.JsonAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.noventapp.direct.user.constants.AppConstants.TokenEnum.Payload;
import static com.noventapp.direct.user.utils.ActivityUtil.startActivityCode;
import static com.noventapp.direct.user.utils.SnackbarUtil.SnackTypes.FAILED;
import static com.noventapp.direct.user.utils.SnackbarUtil.SnackTypes.WARNING;

public class LoginActivity extends BaseActivity implements Validator.ValidationListener {

    @Email
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
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;

    private UserModel userModel;
    private SweetAlertDialog dialogProgress;
    private int transitionActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initValidator();
        transitionActivity = getIntent().getIntExtra("transition_activity", 0);

    }


    @OnClick({R.id.btn_forgetPass, R.id.btn_login, R.id.btn_signUp, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_forgetPass:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.btn_login:
                validator.validate();
                break;
            case R.id.btn_signUp:
                Intent intent = new Intent(this, SignUpActivity.class);
                intent.putExtra("transition_activity", transitionActivity);
                startActivity(intent);
                break;

            case R.id.btn_back:
                onBackPressed();
                break;
        }
    }

    private void userDao(String email, String password) {
        UserRemoteDao.getInstance().login(email, password).enqueue(result -> {
            dialogProgress.dismiss();

            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    try {
                        String token = result.getResult().getAccessToken();
                        JsonAdapter<UserModel> userJsonAdapter = MoshiUtil.getInstance().adapter(UserModel.class);
                        userModel = userJsonAdapter.fromJson(JwtUtils.decodeJWT(token, Payload));
                        userModel.setToken(token);
                        SessionUtils.getInstance().login(userModel);
                        startActivityCode(this, MainActivity.class, transitionActivity);

                    } catch (Exception e) {
                        DialogUtil.errorMessage(this, getString(R.string.unexpected_error),
                                e.getMessage().toString());
                    }

                    break;


                case HttpStatus.BAD_REQUEST:
                    SnackbarUtil.showDefaultSnackBar(this, result.getError().getMessage(), false, FAILED);
                    break;

                case HttpStatus.SERVER_ERROR:
                    SnackbarUtil.showDefaultSnackBar(this, getString(R.string.server_error), true,
                            false, R.string.try_again, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    validator.validate();
                                }
                            }, WARNING);
                    break;

                case HttpStatus.NETWORK_ERROR:
                    SnackbarUtil.showDefaultSnackBar(this, getString(R.string.network_error),
                            true, false, R.string.try_again, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    validator.validate();
                                }
                            }, WARNING);
                    break;

                default:
                    SnackbarUtil.showDefaultSnackBar(this, getString(R.string.unexpected_error), false, FAILED);
                    break;

            }
        });
    }




    @Override
    public void onValidationSucceeded() {
        userDao(etEmail.getText().toString(), etPassword.getText().toString());
        dialogProgress = DialogUtil.progress(this);
        dialogProgress.show();

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof TextInputEditText) {
                ((TextInputLayout) view.getParent().getParent()).setErrorEnabled(false);
                if (((TextInputLayout) view.getParent().getParent()).getId() == R.id.til_password) {
                    ((TextInputLayout) view.getParent().getParent()).setPasswordVisibilityToggleEnabled(false);
                    setPasswordToggleEnable(etPassword);
//                    SnackbarUtil.showDefaultSnackBar(this, getString(R.string.unexpected_error), false, FAILED);

                }
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }

    }

    private void initValidator() {
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    private void setPasswordToggleEnable(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tilPassword.setPasswordVisibilityToggleEnabled(true);
            }
        });

    }

}
