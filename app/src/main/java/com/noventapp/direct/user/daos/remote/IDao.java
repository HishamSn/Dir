package com.noventapp.direct.user.daos.remote;

import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.model.BaseGenericWrapper;

import java.util.List;

public interface IDao<T> {

    HttpCall<BaseGenericWrapper<T>> get();

    HttpCall<BaseGenericWrapper<List<T>>> getList();
}
