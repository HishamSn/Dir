package com.noventapp.direct.user.ui.lang;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.constants.AppConstants;
import com.noventapp.direct.user.ui.base.BaseActivity;
import com.noventapp.direct.user.ui.country.SelectCountryActivity;
import com.noventapp.direct.user.utils.ActivityUtil;
import com.noventapp.direct.user.utils.LocalHelper;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseLanguageActivity extends BaseActivity {

    private CoordinatorLayout constraintLayout;
    private int transitionActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);
        ButterKnife.bind(this);
        constraintLayout = findViewById(R.id.cl_parent);
        transitionActivity = getIntent().getIntExtra("transition_activity", -1);

    }

    public void showSnackbar() {
        Snackbar snackbar = Snackbar.make(constraintLayout, "Error", Snackbar.LENGTH_INDEFINITE)
                .setAction("undo", v -> {
                    Snackbar snackbar1 = Snackbar.make(constraintLayout, "undo successful", Snackbar.LENGTH_SHORT);
                    snackbar1.show();
                });
        snackbar.show();
    }


    private void changeLanguage(String languageCode) {
        LocalHelper.setLocale(this, languageCode);
//        Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
    }

    @OnClick({R.id.btn_lang_eng, R.id.btn_lang_arabic})
    public void onViewClicked(View view) {
        switch (view.getId()) {


            case R.id.btn_lang_eng:
                if (!LocalHelper.isLanguageEn()) {
                    changeLanguage(AppConstants.EN);
                }

                break;
            case R.id.btn_lang_arabic:
                if (LocalHelper.isLanguageEn()) {
                    changeLanguage(AppConstants.AR);
                }
                break;
        }
        goToNextActivity();

    }

    private void goToNextActivity() {
        ActivityUtil.startActivityCode(this, SelectCountryActivity.class, transitionActivity);
        finish();
    }
}
