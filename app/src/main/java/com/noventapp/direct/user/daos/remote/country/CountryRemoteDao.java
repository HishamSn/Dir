package com.noventapp.direct.user.daos.remote.country;

import com.noventapp.direct.user.constants.ApiConstants;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.data.network.HttpHelper;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.CountryModel;

import java.util.List;

import retrofit2.http.GET;

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
        return countyClient.getCountyList();
    }

    private interface CountyClient {
        @GET(ApiConstants.GET_COUNTRY)
        HttpCall<BaseGenericWrapper<List<CountryModel>>> getCountyList();
    }
}

