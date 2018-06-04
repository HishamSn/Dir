package com.noventapp.direct.user.ui.area;

import android.content.Context;

import com.noventapp.direct.user.model.CityModel;

import java.util.ArrayList;
import java.util.List;

public class CityCreator {

    static CityCreator creator;
    List<CityModel> cityList;
    List<CityModel> cityModels;

    public CityCreator(Context context, ArrayList<CityModel> cityListSample) {
        cityList = new ArrayList<>();
        cityModels = cityListSample;
        for (int i = 0; i < cityModels.size(); i++) {
            CityModel cityModel = new CityModel(cityListSample.get(i).getCityName());
            cityList.add(cityModel);

        }


    }

    public static CityCreator get(Context context, ArrayList<CityModel> cityListSample) {
        if (creator == null) {
            creator = new CityCreator(context, cityListSample);
        }
        return creator;
    }


    public List<CityModel> getAll() {
        return cityList;
    }
}
