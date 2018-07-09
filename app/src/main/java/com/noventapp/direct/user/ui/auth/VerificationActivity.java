package com.noventapp.direct.user.ui.auth;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.noventapp.direct.user.constants.AppConstants.TokenEnum.Payload;
import static com.noventapp.direct.user.utils.ActivityUtil.startActivityCode;
import static com.noventapp.direct.user.utils.SnackbarUtil.SnackTypes.FAILED;

public class VerificationActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    AppCompatButton btnBack;
    @BindView(R.id.et_pinCode)
    AppCompatEditText etPinCode;
    @BindView(R.id.btn_continue)
    AppCompatButton btnContinue;
    @BindView(R.id.btn_reSendCode)
    AppCompatButton btnReSendCode;
    private String firstName, lastName, email, phone, password;
    private SweetAlertDialog dialogProgress;
    private UserModel userModel;
    private int transitionActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfication);
        ButterKnife.bind(this);
        getBundle();

    }

    private void getBundle() {
        firstName = getIntent().getExtras().getString("first_name");
        lastName = getIntent().getExtras().getString("last_name");
        email = getIntent().getExtras().getString("email");
        phone = getIntent().getExtras().getString("phone");
        password = getIntent().getExtras().getString("password");
        transitionActivity = getIntent().getIntExtra("transition_activity", 0);
    }

    private void signUpDao(String firstName, String lastName, String email,
                           String password, String phone) {


        UserRemoteDao.getInstance().signUp(firstName, lastName, email,
                password, phone).enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    userDao(email, password);

                    break;

                case HttpStatus.BAD_REQUEST:
                    dialogProgress.dismiss();
                    DialogUtil.errorMessage(this, result.getError().getMessage());
                    break;

                case HttpStatus.SERVER_ERROR:
                    dialogProgress.dismiss();
                    DialogUtil.errorMessage(this, getString(R.string.server_error));
                    break;

                case HttpStatus.NETWORK_ERROR:
                    dialogProgress.dismiss();

                    DialogUtil.errorMessage(this, getString(R.string.network_error));
                    break;

                default:
                    dialogProgress.dismiss();
                    DialogUtil.errorMessage(this, getString(R.string.unexpected_error));
                    break;

            }
        });
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
                    DialogUtil.errorMessage(this, result.getError().getMessage());
                    break;

                case HttpStatus.SERVER_ERROR:
                    DialogUtil.errorMessage(this, getString(R.string.server_error));
                    break;

                case HttpStatus.NETWORK_ERROR:
                    DialogUtil.errorMessage(this, getString(R.string.network_error));
                    break;

                default:
                    SnackbarUtil.showDefaultSnackBar(this, getString(R.string.unexpected_error), false, FAILED);

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
                dialogProgress = DialogUtil.progress(this);
                dialogProgress.show();
                signUpDao(firstName, lastName, email, password, "+966" + phone);
                break;
            case R.id.btn_reSendCode:
                break;
        }
    }
}
