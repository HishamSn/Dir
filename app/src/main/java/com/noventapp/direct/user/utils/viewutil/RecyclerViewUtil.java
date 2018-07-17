package com.noventapp.direct.user.utils.viewutil;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.noventapp.direct.user.R;

public class RecyclerViewUtil {


    public static void addItemDecoration(RecyclerView rv, boolean isVerticalDecoration) {
        if (rv == null) {
            return;
        }
        DividerItemDecoration dividerDecoration;
        if (isVerticalDecoration) {
            dividerDecoration = new DividerItemDecoration(rv.getContext(), LinearLayoutManager.VERTICAL);
            dividerDecoration.setDrawable(rv.getContext().getResources().getDrawable(R.drawable.shape_divider));
        } else {

            dividerDecoration = new DividerItemDecoration(rv.getContext(), LinearLayoutManager.HORIZONTAL);
            dividerDecoration.setDrawable(rv.getContext().getResources().getDrawable(R.drawable.shape_divider));

        }
        rv.addItemDecoration(dividerDecoration);


    }
}
