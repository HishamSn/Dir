package com.noventapp.direct.user.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mobsandgeeks.saripaar.Validator.ValidationListener;

public class SignUpActivity extends BaseActivity implements
        ValidationListener, View.OnFocusChangeListener {

    @Length(min = 2, messageResId = R.string.msg_first_name)
    @BindView(R.id.et_firstName)
    TextInputEditText etFirstName;
    @Length(min = 2, messageResId = R.string.msg_last_name)
    @BindView(R.id.et_lastName)
    TextInputEditText etLastName;
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
        Intent intent = new Intent(this, VerficationActivity.class);
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
                ((TextInputLayout) view.getParent().getParent()).setErrorEnabled(true);
                ((TextInputLayout) view.getParent().getParent()).setError(message);
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
//
//                            startActivity(new Intent(this, MainActivity.class));
//                            finish();
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

    private void userDaoPhone(String phone) {
        UserRemoteDao.getInstance().checkPhone("+966" + phone)
                .enqueue(result -> {

                    switch (result.getStatus()) {
                        case HttpStatus.SUCCESS:

//                            startActivity(new Intent(this, MainActivity.class));
//                            finish();
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
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.et_email:
                if (!hasFocus) {
                    if (isValidEmail(etEmail.getText().toString())) {
                        userDaoEmail(etEmail.getText().toString());
                    } else {
                        Toast.makeText(context, getString(R.string.wrong_email), Toast.LENGTH_SHORT).show();

                    }
                }

                break;
            case R.id.et_phone:
                if (!hasFocus) {
                    if (etPhone.getText().toString().length() == 9) {
                        userDaoPhone(etPhone.getText().toString());
                    } else {
                        Toast.makeText(context, getString(R.string.wrong_phone), Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }

    public final boolean isValidEmail(CharSequence target) {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}
