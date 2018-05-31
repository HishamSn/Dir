package com.noventapp.direct.user.ui.lang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.constants.AppConstants;
import com.noventapp.direct.user.ui.base.BaseActivity;
import com.noventapp.direct.user.utils.LocalHelper;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseLanguageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);
        ButterKnife.bind(this);
    }


    private void changeLanguage(String languageCode) {
        LocalHelper.setLocale(this, languageCode);
        Intent intent = getPackageManager()
                .getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
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
    }
}
