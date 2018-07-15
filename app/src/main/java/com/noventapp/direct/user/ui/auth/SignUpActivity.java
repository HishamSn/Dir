package com.noventapp.direct.user.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.auth.UserRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.ui.base.BaseActivity;
import com.noventapp.direct.user.utils.DialogUtil;
import com.noventapp.direct.user.utils.SnackbarUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mobsandgeeks.saripaar.Validator.ValidationListener;
import static com.noventapp.direct.user.utils.SnackbarUtil.SnackTypes.FAILED;
import static com.noventapp.direct.user.utils.SnackbarUtil.SnackTypes.WARNING;

public class SignUpActivity extends BaseActivity implements
        ValidationListener, View.OnFocusChangeListener {

    @Length(min = 2, messageResId = R.string.msg_first_name)
    @BindView(R.id.et_firstName)
    TextInputEditText etFirstName;
    @Length(min = 2, messageResId = R.string.msg_last_name)
    @BindView(R.id.et_lastName)
    TextInputEditText etLastName;
    @Length(min = 9, messageResId = R.string.wrong_phone)
    @BindView(R.id.et_phone)
    TextInputEditText etPhone;
    @Email(messageResId = R.string.wrong_email)
    @BindView(R.id.et_email)
    TextInputEditText etEmail;
    @Length(min = 8, messageResId = R.string.wrong_password)
    @Password
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    Validator validator;
    Context context = this;
    @ConfirmPassword(messageResId = R.string.repassword_wrong)
    @BindView(R.id.et_rePassword)
    TextInputEditText etRePassword;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.til_rePassword)
    TextInputLayout tilRePassword;
    private int transitionActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        etPhone.setOnFocusChangeListener(this);
        etEmail.setOnFocusChangeListener(this);
        transitionActivity = getIntent().getIntExtra("transition_activity", 0);


    }


    @OnClick({R.id.btn_back, R.id.btn_continue, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btn_continue:
                validator.validate();
                break;
//            case R.id.btn_login:
//                onBackPressed();
//                break;
        }
    }


    @Override
    public void onValidationSucceeded() {
        Intent intent = new Intent(this, VerificationActivity.class);
        intent.putExtra("first_name", etFirstName.getText().toString());
        intent.putExtra("last_name", etLastName.getText().toString());
        intent.putExtra("email", etEmail.getText().toString());
        intent.putExtra("phone", etPhone.getText().toString());
        intent.putExtra("password", etPassword.getText().toString());
        intent.putExtra("transition_activity", transitionActivity);
        startActivity(intent);
    }


    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(context);
            if (view instanceof TextInputEditText) {
                ((TextInputLayout) view.getParent().getParent()).setErrorEnabled(false);
                switch (((TextInputLayout) view.getParent().getParent()).getId()) {
                    case R.id.til_email:
                        checkEmailAndPhone(etEmail);
                        break;
                    case R.id.til_phone:
                        checkEmailAndPhone(etPhone);
                        break;
                    case R.id.til_password:
                        ((TextInputLayout) view.getParent().getParent()).setPasswordVisibilityToggleEnabled(false);
                        ((EditText) view).setError(message);
                        setPasswordToggleEnable(etPassword);
                        break;
                    case R.id.til_rePassword:
                        ((TextInputLayout) view.getParent().getParent()).setPasswordVisibilityToggleEnabled(false);
                        ((EditText) view).setError(message);
                        setPasswordToggleEnable(etRePassword);
                        break;
                    default:
                        ((EditText) view).setError(message);
                }

            } else {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void userDaoEmail(String email) {
        UserRemoteDao.getInstance().checkEmail(email)
                .enqueue(result -> {

                    switch (result.getStatus()) {
                        case HttpStatus.SUCCESS:
                            break;

                        case HttpStatus.BAD_REQUEST:
                            SnackbarUtil.showDefaultSnackBar(this, result.getError().getMessage(), false, FAILED);
                            break;

                        case HttpStatus.SERVER_ERROR:
                            SnackbarUtil.showDefaultSnackBar(this, getString(R.string.server_error), true,
                                    false, R.string.try_again, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            userDaoEmail(etEmail.getText().toString());
                                        }
                                    }, WARNING);
                            break;

                        case HttpStatus.NETWORK_ERROR:
                            SnackbarUtil.showDefaultSnackBar(this, getString(R.string.network_error),
                                    true, false, R.string.try_again, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            userDaoEmail(etEmail.getText().toString());
                                        }
                                    }, WARNING);
                            break;

                        default:
                            SnackbarUtil.showDefaultSnackBar(this, getString(R.string.unexpected_error), false, FAILED);
                            break;
                    }

                });
    }

    private void userDaoPhone(String phone) {
        UserRemoteDao.getInstance().checkPhone("+966" + phone)
                .enqueue(result -> {

                    switch (result.getStatus()) {
                        case HttpStatus.SUCCESS:
                            break;

                        case HttpStatus.BAD_REQUEST:
                            SnackbarUtil.showDefaultSnackBar(this, result.getError().getMessage(), false, FAILED);
                            break;

                        case HttpStatus.SERVER_ERROR:
                            SnackbarUtil.showDefaultSnackBar(this, getString(R.string.server_error), true,
                                    false, R.string.try_again, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            userDaoPhone(etPhone.getText().toString());
                                        }
                                    }, WARNING);
                            break;

                        case HttpStatus.NETWORK_ERROR:
                            SnackbarUtil.showDefaultSnackBar(this, getString(R.string.network_error),
                                    true, false, R.string.try_again, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            userDaoPhone(etPhone.getText().toString());
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
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.et_email:
                if (!hasFocus) {
                    checkEmailAndPhone(etEmail);
                }

                break;
            case R.id.et_phone:
                if (!hasFocus) {
                    checkEmailAndPhone(etPhone);
                }

                break;
        }
    }

    public final boolean isValidEmail(CharSequence target) {

        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void checkEmailAndPhone(EditText editText) {
        switch (editText.getId()) {
            case R.id.et_email:
                if (isValidEmail(etEmail.getText().toString())) {
                    userDaoEmail(etEmail.getText().toString());
                } else {

                    editText.setError(getString(R.string.wrong_email));
                }
                break;
            case R.id.et_phone:
                if (etPhone.getText().toString().length() == 9) {
                    userDaoPhone(etPhone.getText().toString());
                } else {
                    editText.setError(getString(R.string.wrong_phone));
                }
                break;
        }
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
                switch (editText.getId()) {
                    case R.id.et_password:
                        tilPassword.setPasswordVisibilityToggleEnabled(true);
                        break;
                    case R.id.et_rePassword:
                        tilRePassword.setPasswordVisibilityToggleEnabled(true);
                        break;
                }

            }
        });

    }

}
