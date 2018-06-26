package com.noventapp.direct.user.ui.setting;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.noventapp.direct.user.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_back)
    AppCompatButton btnBack;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.iv_profile)
    ImageView ivProfile;
    @BindView(R.id.tv_username_content)
    AppCompatEditText tvUsernameContent;
    @BindView(R.id.btn_edit)
    AppCompatButton btnEdit;
    @BindView(R.id.et_first_name)
    AppCompatEditText etFirstName;
    @BindView(R.id.et_last_name)
    AppCompatEditText etLastName;
    @BindView(R.id.et_phone)
    AppCompatEditText etPhone;
    @BindView(R.id.tv_email_label)
    AppCompatTextView tvEmailLabel;
    @BindView(R.id.et_email)
    AppCompatEditText etEmail;
    @BindView(R.id.btn_change_password)
    AppCompatButton btnChangePassword;
    @BindView(R.id.btn_save)
    AppCompatButton btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        toolbarTitle.setText(R.string.setting);

    }
}
