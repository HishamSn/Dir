package com.noventapp.direct.user.daos.remote.country;

import com.noventapp.direct.user.constants.ApiConstants;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.data.network.HttpHelper;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.CountryModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public class CountryRemoteDao implements IRemoteCountryDao {

    private static CountryRemoteDao instance;
    private CountyClient countyClient;

    public CountryRemoteDao() {
        countyClient = HttpHelper.getInstance().create(CountyClient.class);

    }

    public static synchronized CountryRemoteDao getInstance() {
        if (instance == null) {
            instance = new CountryRemoteDao();
        }
        return instance;
    }

    @Override
    public HttpCall<BaseGenericWrapper<CountryModel>> get() {
        return null;
    }

    @Override
    public HttpCall<BaseGenericWrapper<List<CountryModel>>> getList() {
        return null;
    }

    @Override
    public HttpCall<BaseGenericWrapper<List<CountryModel>>> getCountryList() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 1);
        return countyClient.getCountyList(map);
    }

    private interface CountyClient {
        @GET(ApiConstants.GET_COUNTRY)
        HttpCall<BaseGenericWrapper<List<CountryModel>>> getCountyList(@QueryMap Map<String, Object> map);
    }
}

