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
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.ui.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mobsandgeeks.saripaar.Validator.ValidationListener;

public class SignUpActivity extends BaseActivity implements
        ValidationListener {

    @BindView(R.id.btn_back)
    AppCompatButton btnBack;
    @BindView(R.id.et_firstName)
    TextInputEditText etFirstName;
    @BindView(R.id.et_lastName)
    TextInputEditText etLastName;
    @BindView(R.id.et_phone)
    TextInputEditText etPhone;
    @Email(messageResId = R.string.wrong_email)
    @BindView(R.id.et_email)
    TextInputEditText etEmail;
    @Length(min = 8, messageResId = R.string.wrong_password)
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.btn_continue)
    AppCompatButton btnContinue;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;
    Validator validator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);

    }


    @Override
    public void onValidationSucceeded() {

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

    @OnClick({R.id.btn_back, R.id.btn_continue, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                break;
            case R.id.btn_continue:
                validator.validate();
                break;
            case R.id.btn_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
