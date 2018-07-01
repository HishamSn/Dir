package com.noventapp.direct.user.ui.lang;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.constants.AppConstants;
import com.noventapp.direct.user.ui.base.BaseActivity;
import com.noventapp.direct.user.ui.country.SelectCountryActivity;
import com.noventapp.direct.user.ui.main.MainActivity;
import com.noventapp.direct.user.utils.LocalHelper;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseLanguageActivity extends BaseActivity {

    CoordinatorLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);
        ButterKnife.bind(this);
        constraintLayout = findViewById(R.id.cl_parent_lang);
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
//        Intent intent = getPackageManager()
//                .getLaunchIntentForPackage(getPackageName());
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
                if (getIntent().getExtras() == null) {
                    startActivity(new Intent(this, SelectCountryActivity.class));

                } else if (getIntent().getExtras().getString("came_from").equals("nav_menu")) {
                    startActivity(new Intent(this, MainActivity.class));
                }
                finish();


                break;
            case R.id.btn_lang_arabic:
                showSnackbar();
//               .. PLZ DELETE showSnackbar Hisham
//                if (LocalHelper.isLanguageEn()) {
//                    changeLanguage(AppConstants.AR);
//                }
//                startActivity(new Intent(this, SelectCountryActivity.class));
//                finish();
                break;
        }
    }
}
