package com.noventapp.direct.user.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import com.noventapp.direct.user.common.MyApplication;

import java.util.Locale;

import static com.noventapp.direct.user.constants.AppConstants.EN;
import static com.noventapp.direct.user.constants.AppConstants.SELECTED_LANGUAGE;


/*
 * Created by Hisham Snaimeh on 5/6/2018.
 */

public class LocalHelper {


        public static Context onAttach(Context context) {
            String lang = getPersistedData(context, Locale.getDefault().getLanguage());
            return setLocale(context, lang);
        }

        public static Context onAttach(Context context, String defaultLanguage) {
            String lang = getPersistedData(context, defaultLanguage);
            return setLocale(context, lang);
        }

        public static String getLanguage(Context context) {
            return getPersistedData(context, Locale.getDefault().getLanguage());
        }



        public static Context setLocale(Context context, String language) {
            persist(context, language);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return updateResources(context, language);
            }

            return updateResourcesLegacy(context, language);
        }

        private static String getPersistedData(Context context, String defaultLanguage) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            return preferences.getString(SELECTED_LANGUAGE, defaultLanguage);
        }

        private static void persist(Context context, String language) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(SELECTED_LANGUAGE, language);
            editor.apply();
        }

        @TargetApi(Build.VERSION_CODES.N)
        private static Context updateResources(Context context, String language) {
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            context.getApplicationContext().getResources().updateConfiguration(config, null);
            Configuration configuration = context.getResources().getConfiguration();
            configuration.setLocale(locale);
            configuration.setLayoutDirection(locale);
            return context.createConfigurationContext(configuration);
        }

        private static Context updateResourcesLegacy(Context context, String language) {
            Locale locale = new Locale(language);
            Locale.setDefault(locale);

            Resources resources = context.getResources();

            Configuration configuration = resources.getConfiguration();
            configuration.locale = locale;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLayoutDirection(locale);
            }

            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
            return context;
        }

    public static boolean isLanguageEn() {
        return PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance()).
                getString(SELECTED_LANGUAGE, EN).equals(EN);
    }


}
