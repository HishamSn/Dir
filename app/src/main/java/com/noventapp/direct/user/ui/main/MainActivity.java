package com.noventapp.direct.user.ui.main;

import android.os.Bundle;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.ui.base.BaseActivity;

import butterknife.ButterKnife;



/*
 * Created by Hisham Snaimeh on 5/6/2018.
 */

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

}
