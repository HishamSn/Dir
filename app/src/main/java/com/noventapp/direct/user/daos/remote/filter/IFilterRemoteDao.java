package com.noventapp.direct.user.daos.remote.filter;

import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.PrimeFilterCategory;

import java.util.List;

public interface IFilterRemoteDao {
    HttpCall<BaseGenericWrapper<List<PrimeFilterCategory>>> getPrimeList();
}
