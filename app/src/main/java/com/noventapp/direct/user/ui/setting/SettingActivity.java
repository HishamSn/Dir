package com.noventapp.direct.user.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.setting.SettingRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.model.UserModel;
import com.noventapp.direct.user.ui.base.BaseActivity;
import com.noventapp.direct.user.utils.DialogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class SettingActivity extends BaseActivity implements Validator.ValidationListener, View.OnFocusChangeListener {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.iv_profile)
    ImageView ivProfile;
    @BindView(R.id.et_username)
    AppCompatEditText etUsername;
    @Length(min = 2, messageResId = R.string.msg_first_name)
    @BindView(R.id.et_first_name)
    AppCompatEditText etFirstName;
    @Length(min = 2, messageResId = R.string.msg_last_name)
    @BindView(R.id.et_last_name)
    AppCompatEditText etLastName;
    @BindView(R.id.et_phone)
    AppCompatEditText etPhone;
    @Email(messageResId = R.string.wrong_email)
    @BindView(R.id.tv_email_label)
    AppCompatTextView tvEmailLabel;
    @BindView(R.id.et_email)
    AppCompatEditText etEmail;
    @BindView(R.id.btn_edit)
    AppCompatButton btnEdit;
    Validator validator;

    private String tempUserName;
    private SweetAlertDialog dialogProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        toolbarTitle.setText(R.string.setting);
        dialogProgress = DialogUtil.progress(this);
        dialogProgress.show();
        getUserInfoDao();
        validator = new Validator(this);
        validator.setValidationListener(this);
        etUsername.setOnFocusChangeListener(this);

    }

    @OnClick({R.id.btn_back, R.id.btn_save, R.id.btn_change_password, R.id.btn_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();

                break;
            case R.id.btn_change_password:
                startActivity(new Intent(this, ChangePasswordActivity.class));
                break;
            case R.id.btn_save:
                validator.validate();

                break;
            case R.id.btn_edit:
                setUpUserNameEnable();

                break;
        }
    }

    private void setUpUserNameEnable() {

        if (etUsername.isEnabled()) {
            etUsername.setEnabled(false);
            etUsername.setText(tempUserName);
            btnEdit.setText(getString(R.string.edit));

        } else {
//            validateUserNameSuccess();
            tempUserName = etUsername.getText().toString();
            etUsername.setEnabled(true);
            btnEdit.setText(getString(R.string.cancel));
        }
    }

    private void updateUserInfoDao(String firstName, String lastName,
                                   String email, String phone, String userName) {
        SettingRemoteDao.getInstance().
                updateUserInfo(firstName, lastName, email, phone, userName).
                enqueue(result -> {
                    dialogProgress.dismiss();

                    switch (result.getCode()) {

                        case HttpStatus.SUCCESS:

                            break;

                        case HttpStatus.BAD_REQUEST:
                            break;

                        case HttpStatus.SERVER_ERROR:

                            break;

                        case HttpStatus.NETWORK_ERROR:

                            break;

                        default:

                            break;
                    }
                });
    }

    private void checkUserName(String userName) {
        SettingRemoteDao.getInstance().checkUserName(userName)
                .enqueue(result -> {

                    switch (result.getStatus()) {
                        case HttpStatus.SUCCESS:


                            Snackbar snack = Snackbar.make(findViewById(android.R.id.content),
                                    "Success", Snackbar.LENGTH_LONG);
                            View view = snack.getView();
                            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                            params.gravity = Gravity.TOP;
                            view.setLayoutParams(params);
                            snack.show();

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


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.et_username:
                if (!hasFocus && etUsername.isEnabled()) {
                    validateUserNameSuccess();
                }
                break;

        }
    }

    private void validateUserNameSuccess() {
        if (etUsername.getText().toString().length() < 8) {
            Toast.makeText(this, getString(R.string.msg_user_name), Toast.LENGTH_LONG).show();
        } else {
            checkUserName(etUsername.getText().toString());
        }
    }


    private void getUserInfoDao() {
        SettingRemoteDao.getInstance().getUserInfo()
                .enqueue(result -> {
                    dialogProgress.dismiss();
                    switch (result.getStatus()) {
                        case HttpStatus.SUCCESS:
                            if (result.getResult().getCode() == 203) {
                                DialogUtil.errorMessage(this,
                                        result.getResult().getMessage(), true);
                            } else {
                                getUserInfo(result.getResult().getData());
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

    private void getUserInfo(UserModel data) {
        etUsername.setText(data.getUsername());
        etFirstName.setText(data.getFirstName());
        etLastName.setText(data.getLastName());
        etEmail.setText(data.getEmail());
        etPhone.setText(data.getPhoneNumber());
    }

    @Override
    public void onValidationSucceeded() {
        dialogProgress.show();
        updateUserInfoDao(etFirstName.getText().toString(), etLastName.getText().toString(),
                etEmail.getText().toString(), etPhone.getText().toString(),
                etUsername.getText().toString());
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

