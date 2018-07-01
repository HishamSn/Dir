package com.noventapp.direct.user.daos.remote.contactus;

import com.noventapp.direct.user.constants.ApiConstants;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.data.network.HttpHelper;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.ContactUs;

import java.util.List;

import retrofit2.http.GET;

public class ContactUsRemoteDao implements IRemoteContactUsDao {

    private static ContactUsRemoteDao instance;
    private ContactClient contactClient;

    public ContactUsRemoteDao() {
        contactClient = HttpHelper.getInstance().create(ContactClient.class);

    }

    public static synchronized ContactUsRemoteDao getInstance() {
        if (instance == null) {
            instance = new ContactUsRemoteDao();
        }
        return instance;
    }



    @Override
    public HttpCall<BaseGenericWrapper<List<ContactUs>>> getContactUs() {
        return contactClient.getContactList();
    }

    @Override
    public HttpCall<BaseGenericWrapper<ContactUs>> get() {
        return null;
    }

    @Override
    public HttpCall<BaseGenericWrapper<List<ContactUs>>> getList() {
        return null;
    }


    private interface ContactClient {
        @GET(ApiConstants.GET_CONTACT_US)
        HttpCall<BaseGenericWrapper<List<ContactUs>>> getContactList();
    }
}

