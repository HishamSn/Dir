package com.noventapp.direct.user.ui.splash;

import android.content.Intent;
import android.os.*;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.ui.base.BaseActivity;
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
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }, secondsDelayed * 1000);
    }

}
