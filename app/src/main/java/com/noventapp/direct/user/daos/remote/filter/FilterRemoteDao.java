package com.noventapp.direct.user.daos.remote.filter;

import com.noventapp.direct.user.constants.ApiConstants;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.data.network.HttpHelper;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.PrimeFilterCategory;

import java.util.List;

import retrofit2.http.GET;

public class FilterRemoteDao implements IFilterRemoteDao {

    private static FilterRemoteDao instance;
    private PrimeFilterClient primeFilterClient;


    public FilterRemoteDao() {
        primeFilterClient = HttpHelper.getInstance().create(PrimeFilterClient.class);
    }

    public static synchronized FilterRemoteDao getInstance() {
        if (instance == null) {
            instance = new FilterRemoteDao();
        }
        return instance;
    }

    @Override
    public HttpCall<BaseGenericWrapper<List<PrimeFilterCategory>>> getPrimeList() {
        return primeFilterClient.getPrimeList();
    }

    private interface PrimeFilterClient {
        @GET(ApiConstants.GET_PRIME_FILTER)
        HttpCall<BaseGenericWrapper<List<PrimeFilterCategory>>> getPrimeList();
    }
}
