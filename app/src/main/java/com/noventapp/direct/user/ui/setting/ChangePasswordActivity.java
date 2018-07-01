package com.noventapp.direct.user.ui.setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.setting.SettingRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.utils.SessionUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.btn_back)
    AppCompatButton btnBack;
    @BindView(R.id.et_currentPass)
    AppCompatEditText etCurrentPass;
    @Length(min = 8, messageResId = R.string.wrong_password)
    @Password
    @BindView(R.id.et_newPass)
    AppCompatEditText etNewPass;
    @ConfirmPassword(messageResId = R.string.repassword_wrong)
    @BindView(R.id.et_rePassword)
    AppCompatEditText etRePassword;
    @BindView(R.id.btn_save)
    AppCompatButton btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        toolbarTitle.setText(R.string.create_pass);
    }

    @OnClick({R.id.btn_back, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btn_save:
                changePasswordDao(SessionUtils.getInstance().getUser().getId(),
                        etCurrentPass.getText().toString(), etNewPass.getText().toString());
                break;
        }
    }

    private void changePasswordDao(Integer id, String oldPassword, String newPassord) {
        SettingRemoteDao.getInstance().changePassword(id, oldPassword, newPassord).enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    break;
                case HttpStatus.BAD_REQUEST:
                    break;
                case HttpStatus.NETWORK_ERROR:
                    break;
                case HttpStatus.SERVER_ERROR:
                    break;
            }
        });
    }
}
