package com.noventapp.direct.user.daos.remote.contactus;

import com.noventapp.direct.user.daos.remote.IDao;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.ContactUs;
import com.noventapp.direct.user.model.CountryModel;

import java.util.List;

public interface IRemoteContactUsDao extends IDao<CountryModel> {


    HttpCall<BaseGenericWrapper<List<ContactUs>>> getContactUs();
}
