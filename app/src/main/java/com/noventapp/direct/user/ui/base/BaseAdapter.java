package com.noventapp.direct.user.ui.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Hisham Snaimeh on 5/7/2018.
 * hish.sn.dev@gmail.com
 */

public abstract class BaseAdapter<E extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<E> {

    private int page;
    private boolean isLoading;
    private OnLoadMoreListener onLoadMoreListener;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (onLoadMoreListener != null) {
                    LinearLayoutManager layoutManager =
                            (LinearLayoutManager) recyclerView.getLayoutManager();
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    if (isLoading) {
                        return;
                    }
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        isLoading = true;
                        onLoadMoreListener.onLoadMore(page);
                        page++;
                    }
                }
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void finishLoading() {
        isLoading = false;
    }

    public void clearPage() {
        page = 0;
    }

    @FunctionalInterface
    public interface OnLoadMoreListener {
        void onLoadMore(int page);
    }
}
