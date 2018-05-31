package com.noventapp.direct.user.ui.address.adapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class CityCreator {

    static CityCreator cityCreator;
    List<String> cityList;

    public CityCreator(Context context) {
        cityList = new ArrayList<>();
//        for (int i=0;i<=100;i++)
//        {
//            cityList.add(new);
//        }

    }

    public static CityCreator get(Context context) {
        if (cityCreator == null) {
            cityCreator = new CityCreator(context);
        }
        return cityCreator;
    }
}
