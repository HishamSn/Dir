package com.noventapp.direct.user.daos.remote.country;

import com.noventapp.direct.user.daos.remote.IDao;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.CountryModel;

import java.util.List;

public interface IRemoteCountryDao extends IDao<CountryModel> {


    HttpCall<BaseGenericWrapper<List<CountryModel>>> getCountryList();
}
