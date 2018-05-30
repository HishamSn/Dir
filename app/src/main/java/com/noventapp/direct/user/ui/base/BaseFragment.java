package com.noventapp.direct.user.ui.base;

import android.content.Context;
import android.support.v4.app.Fragment;

/*
 * Created by Hisham Snaimeh on 5/6/2018.
 */

public class BaseFragment extends Fragment {


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (!NetworkDetector.isConnected(context)) {
//            NotificationUtil.showError(context, R.string.no_internet);
//        }
    }

}
