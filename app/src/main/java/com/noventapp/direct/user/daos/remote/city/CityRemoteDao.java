package com.noventapp.direct.user.daos.remote.city;

import com.noventapp.direct.user.constants.ApiConstants;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.data.network.HttpHelper;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.CityModel;

import java.util.List;

import retrofit2.http.GET;

public class CityRemoteDao implements ICityRemoteDao {

    private static CityRemoteDao instance;
    private CityClient cityClient;

    public CityRemoteDao() {
        cityClient = HttpHelper.getInstance().create(CityClient.class);
    }

    public static synchronized CityRemoteDao getInstance() {
        if (instance == null) {
            instance = new CityRemoteDao();
        }
        return instance;
    }

    @Override
    public HttpCall<BaseGenericWrapper<CityModel>> get() {
        return null;
    }

    @Override
    public HttpCall<BaseGenericWrapper<List<CityModel>>> getList() {
        return cityClient.getCityList();
    }


    private interface CityClient {
        @GET(ApiConstants.GET_CITES)
        HttpCall<BaseGenericWrapper<List<CityModel>>> getCityList();
    }
}
