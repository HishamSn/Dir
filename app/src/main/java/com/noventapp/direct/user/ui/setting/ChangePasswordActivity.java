package com.noventapp.direct.user.ui.setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.TextView;

import com.noventapp.direct.user.R;

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
    @BindView(R.id.et_newPass)
    AppCompatEditText etNewPass;
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
                break;
        }
    }
}
