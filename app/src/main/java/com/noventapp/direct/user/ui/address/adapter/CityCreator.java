package com.noventapp.direct.user.ui.address.adapter;

import android.content.Context;

import com.noventapp.direct.user.model.CityModel;

import java.util.ArrayList;
import java.util.List;

public class CityCreator {

    static CityCreator creator;
    List<CityModel> cityList;

    public CityCreator(Context context) {
        cityList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CityModel cityModel = new CityModel("city" + i);
            cityList.add(cityModel);

        }


    }

    public static CityCreator get(Context context) {
        if (creator == null) {
            creator = new CityCreator(context);
        }
        return creator;
    }


    public List<CityModel> getAll() {
        return cityList;
    }
}
