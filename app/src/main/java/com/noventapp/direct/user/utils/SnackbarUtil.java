package com.noventapp.direct.user.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.noventapp.direct.user.R;

public class SnackbarUtil {

    /************************************ ShowSnackbar with message, KeepItDisplayedOnScreen for few seconds*****************************/
    public static void showSnakbarTypeOne(View rootView, String mMessage) {
        Snackbar.make(rootView, mMessage, Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }

    /************************************ ShowSnackbar with message, KeepItDisplayedOnScreen*****************************/
    public static void showSnakbarTypeTwo(View rootView, String mMessage) {

        Snackbar.make(rootView, mMessage, Snackbar.LENGTH_LONG)
                .make(rootView, mMessage, Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null)
                .show();

    }

    /************************************ ShowSnackbar without message, KeepItDisplayedOnScreen, OnClickOfOk restrat the activity*****************************/
    public static void showSnakbarTypeThree(View rootView, final Activity activity) {

        Snackbar
                .make(rootView, "NoInternetConnectivity", Snackbar.LENGTH_INDEFINITE)
                .setAction("TryAgain", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = activity.getIntent();
                        activity.finish();
                        activity.startActivity(intent);
                    }
                })
                .setActionTextColor(Color.CYAN)
                .setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                        super.onShown(snackbar);
                    }
                })
                .show();

    }

    /************************************ ShowSnackbar with message, KeepItDisplayedOnScreen, OnClickOfOk restrat the activity*****************************/
    public static void showSnakbarTypeFour(View rootView, final Activity activity, String mMessage) {

        Snackbar
                .make(rootView, mMessage, Snackbar.LENGTH_INDEFINITE)
                .setAction("TryAgain", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = activity.getIntent();
                        activity.finish();
                        activity.startActivity(intent);
                    }
                })
                .setActionTextColor(Color.CYAN)
                .setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                        super.onShown(snackbar);
                    }
                })
                .show();

    }


    public static Snackbar progress(CoordinatorLayout coordinatorLayout, String msg) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_SHORT);

        return snackbar;
    }

    public static void showDefaultSnackBar(Activity activity, String message, boolean hasAction,
                                           boolean autoDismiss, @StringRes Integer action,
                                           View.OnClickListener listener, SnackTypes snackTypes) {
        View rootView = activity.findViewById(R.id.cl_parent);
        int duration = hasAction ? Snackbar.LENGTH_INDEFINITE : Snackbar.LENGTH_LONG;
        Snackbar snackbar = Snackbar.make(rootView, message, duration);
        if (hasAction) {
            snackbar.setAction(action, listener);
        }
        if (autoDismiss) {
            rootView.postDelayed(snackbar::dismiss, 3000);
        }

        // Changing message text color
        defaultTheme(snackbar, snackTypes);
        snackbar.show();
        snackbar.show();
    }

    private static void defaultTheme(Snackbar snackbar, SnackTypes snackTypes) {
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);

        switch (snackTypes) {
            case INFO:

            case SUCCESS:
                sbView.setBackgroundResource(R.color.success_green);
                break;

            case WARNING:
                snackbar.setActionTextColor(Color.RED);
                sbView.setBackgroundResource(R.color.warning_orange);
                break;
            case FAILED:
                break;

            case DEFAULT:

                break;

        }
        textView.setTextColor(Color.WHITE);
    }

    public static void showDefaultSnackBar(Activity activity, String message, boolean autoDismiss, SnackTypes snackTypes) {

        showDefaultSnackBar(activity, message, false, autoDismiss, null, null, snackTypes);
    }


//    public static void successSnack(View rootView, String message, @DrawableRes Integer icon,
//                                    boolean hasAction, boolean autoDismiss, @StringRes Integer action,
//                                    View.OnClickListener listener) {
//        if (action == null) {
//            action = R.string.retry;
//        }
//        showSnack(rootView, message, R.color.green, icon, hasAction, autoDismiss, action, listener);
//    }
//
//    public static void failedSnack(View rootView, String message, @DrawableRes Integer icon,
//                                   boolean hasAction, boolean autoDismiss, @StringRes Integer action,
//                                   View.OnClickListener listener) {
//        if (action == null) {
//            action = R.string.retry;
//        }
//        showSnack(rootView, message, R.color.red, icon, hasAction, autoDismiss, action, listener);
//    }

//    public static void infoSnack(View rootView, String message, @DrawableRes Integer icon,
//                                 boolean hasAction, boolean autoDismiss, @StringRes Integer action,
//                                 View.OnClickListener listener) {
//        if (action == null) {
//            action = R.string.retry;
//        }
//        showSnack(rootView, message, R.color.holo_orange, icon, hasAction, autoDismiss, action, listener);
//    }

//    private static void showSnack(View rootView, String message, @ColorRes Integer color,
//                                  @DrawableRes Integer icon, boolean hasAction, boolean autoDismiss,
//                                  int action, View.OnClickListener listener) {
//        if (rootView != null) {
//            int duration = hasAction ? Snackbar.LENGTH_INDEFINITE : Snackbar.LENGTH_LONG;
//            Snackbar snackbar = Snackbar.make(rootView, message, duration);
//            Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
//            ViewCompat.setBackgroundTintList(layout, ColorStateList.valueOf(
//                    ContextCompat.getColor(rootView.getContext(), R.color.transparent)
//            ));
//
//            Snackbar.SnackbarLayout.LayoutParams layoutParams = (Snackbar.SnackbarLayout.LayoutParams) layout.getLayoutParams();
//            layoutParams.setMargins(10, 0, 10, 10);
//            layout.requestLayout();
//
//            layout.removeAllViews();
//            layout.setPadding(0, 0, 0, 0);
//            layout.addView(getSnackView(rootView, message, action, color, icon, listener, snackbar));
//            if (autoDismiss) {
//                rootView.postDelayed(snackbar::dismiss, 5000);
//            }
//            snackbar.show();
//        }
//    }

//    private static View getSnackView(View rootView, String message, int action, int color, Integer icon,
//                                     View.OnClickListener listener, Snackbar snackbar) {
//
//        View view = LayoutInflater.from(rootView.getContext()).inflate(R.layout.layout_snack, null);
//        ViewCompat.setBackgroundTintList(view.findViewById(R.id.cl_snack),
//                ColorStateList.valueOf(ContextCompat.getColor(rootView.getContext(), color)));
//
//        AppCompatImageView ivSnackicon = view.findViewById(R.id.iv_snack_icon);
//        if (icon == null) {
//            ivSnackicon.setVisibility(View.GONE);
//        } else {
//            ivSnackicon.setVisibility(View.VISIBLE);
//            ivSnackicon.setImageResource(icon);
//            ivSnackicon.setColorFilter(ContextCompat.getColor(rootView.getContext(), color));
//        }
//
//        TextView tvAction = view.findViewById(R.id.tv_snack_action);
//        TextView tvMessage = view.findViewById(R.id.tv_snack_msg);
//        tvMessage.setText(message);
//
//        if (listener != null) {
//            tvAction.setText(action);
//            tvAction.setVisibility(View.VISIBLE);
//            tvAction.setOnClickListener(v -> {
//                listener.onClick(v);
//                if (snackbar == null) {
//                    return;
//                }
//                snackbar.dismiss();
//            });
//        }
//        return view;
//    }


//    public static void snack(View rootView, String message, @DrawableRes Integer icon,
//                             boolean hasAction, boolean autoDismiss, @StringRes Integer action,
//                             View.OnClickListener listener, SnackTypes types) {
//        if (types == null || rootView == null) {
//            return;
//        }
//        switch (types) {
//            case SUCCESS:
////                successSnack(rootView, message, icon, hasAction, autoDismiss, action, listener);
//                break;
//            case INFO:
////                infoSnack(rootView, message, icon, hasAction, autoDismiss, action, listener);
//                break;
//            case FAILED:
////                failedSnack(rootView, message, icon, hasAction, autoDismiss, action, listener);
//                break;
//            case DEFAULT:
//                showDefaultSnackBar(rootView, message, hasAction, autoDismiss, action, listener);
//                break;
//        }
//    }


    public enum SnackTypes {
        FAILED, WARNING, INFO, SUCCESS, DEFAULT
    }
}





