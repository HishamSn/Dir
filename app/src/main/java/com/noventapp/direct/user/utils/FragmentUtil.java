package com.noventapp.direct.user.utils;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.noventapp.direct.user.ui.base.BaseFragment;



/*
 * Created by Hisham Snaimeh on 5/6/2018.
 */

public class FragmentUtil {

    public static void showFragmentWithArguments(FragmentManager fm, BaseFragment fragment,
                                                 Bundle args, int container, String tag) {
        fragment.setArguments(args);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(container, fragment);
        if (tag != null && !tag.isEmpty()) {
            transaction.addToBackStack(tag);
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }

    public static void showFragmentWithArguments(FragmentManager fm, BaseFragment fragment, int container, String tag) {
        showFragmentWithArguments(fm, fragment, null, container, tag);
    }

    public static void showFragmentWithArguments(FragmentManager fm, BaseFragment fragment, int container) {
        showFragmentWithArguments(fm, fragment, null, container, null);
    }

    public static void showFragmentWithArguments(FragmentManager fm, BaseFragment fragment, Bundle args, int container) {
        showFragmentWithArguments(fm, fragment, args, container, null);
    }
}
