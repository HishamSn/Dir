package com.noventapp.direct.user.ui.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.ui.auth.LoginActivity;
import com.noventapp.direct.user.ui.lang.ChooseLanguageActivity;
import com.noventapp.direct.user.utils.ContextHolder;
import com.noventapp.direct.user.utils.LocalHelper;
import com.noventapp.direct.user.utils.SessionUtils;

import cn.pedant.SweetAlert.SweetAlertDialog;


@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggleBar;
    private NavigationView navigationView;
    private Context context = this;
    View viewHeaderNav;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextHolder.setDefaultContext(this);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocalHelper.onAttach(base));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void setNavigation(NavigationView navigationView, Toolbar toolbar,
                              DrawerLayout drawerLayout) {
        init(navigationView, toolbar, drawerLayout);

        toggleBar = new ActionBarDrawerToggle(this, drawerLayout, toolbar
                , R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggleBar);
        toggleBar.syncState();
        navigationView.getMenu().getItem(0).setChecked(true);
        setNavigationItemSelectedListener(navigationView);


        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        addHeader(navigationView);


        setUpViewHeaderNav();

    }

    private void addHeader(NavigationView navigationView) {
        viewHeaderNav = navigationView.inflateHeaderView(R.layout.nav_header);
    }

    public void setUpViewHeaderNav() {
        btnLogin = viewHeaderNav.findViewById(R.id.btn_login);

        checkIsUserLogin();

        btnLogin.setOnClickListener(v -> {
            if (!SessionUtils.getInstance().isLogin()) {
                startActivity(new Intent(context, LoginActivity.class));
            } else {
                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText(context.getString(R.string.cancel_msg))
                        .setContentText(context.getString(R.string.sure_logout_msg))
                        .setCancelText(context.getString(R.string.no))
                        .setConfirmText(context.getString(R.string.yes))
                        .showCancelButton(true)
                        .setConfirmClickListener(sDialog -> {
                            SessionUtils.getInstance().logout();
                            btnLogin.setText(getString(R.string.login_account));
                            new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText(context.getString(R.string.success))
                                    .setContentText(context.getString(R.string.logout_msg))
                                    .setConfirmClickListener(sweetAlertDialog -> {
                                        sweetAlertDialog.dismiss();
                                    })
                                    .show();
                            sDialog.cancel();

                        })
                        .setCancelClickListener(SweetAlertDialog::cancel)
                        .show();


            }
            drawerLayout.closeDrawers();
        });

        viewHeaderNav.findViewById(R.id.btn_setting)
                .setOnClickListener(v -> {
                    startActivity(new Intent(context, LoginActivity.class));
                    drawerLayout.closeDrawers();
                });
        viewHeaderNav.findViewById(R.id.btn_changeLang)
                .setOnClickListener(v -> {
                    startActivity(new Intent(context, ChooseLanguageActivity.class));
                    drawerLayout.closeDrawers();
                });
    }

    private void checkIsUserLogin() {
        if (!SessionUtils.getInstance().isLogin()) {
            btnLogin.setText(getString(R.string.login_account));
        } else {
            btnLogin.setText(getString(R.string.logout_account));
        }
    }

    private void init(NavigationView navigationView, Toolbar toolbar, DrawerLayout drawerLayout) {
        this.navigationView = navigationView;
        this.drawerLayout = drawerLayout;
        this.toolbar = toolbar;

    }


    private void setNavigationItemSelectedListener(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(item -> {
            selectItemDrawer(item);
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_notfication) {
            return true;
        }

        if (toggleBar.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void selectItemDrawer(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_orders:

                break;
            case R.id.menu_reservation:

                break;
            case R.id.myAddress:

                break;
            case R.id.menu_favorites:

                break;
            case R.id.menu_points:

                break;
            case R.id.menu_report_problem:

                break;


        }
        drawerLayout.closeDrawers();


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }

        super.onBackPressed();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (navigationView != null) {
            checkIsUserLogin();
        }
    }
}
