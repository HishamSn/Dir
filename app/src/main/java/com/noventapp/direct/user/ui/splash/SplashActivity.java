package com.noventapp.direct.user.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.data.prefs.PrefsUtils;
import com.noventapp.direct.user.ui.base.BaseActivity;
import com.noventapp.direct.user.ui.lang.ChooseLanguageActivity;
import com.noventapp.direct.user.ui.main.MainActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setHandler(5);

    }

    private void setHandler(int secondsDelayed) {
        new Handler().postDelayed(() -> {
            if (!PrefsUtils.isFirstUse()) {
                startActivity(new Intent(getApplicationContext(), ChooseLanguageActivity.class));
                finish();
            } else {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }

        }, secondsDelayed * 1000);
    }

}
