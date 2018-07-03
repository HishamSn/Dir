package com.noventapp.direct.user.daos.remote.address;

import com.noventapp.direct.user.constants.ApiConstants;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.data.network.HttpHelper;
import com.noventapp.direct.user.model.AddressModel;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.BaseWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class AddressRemoteDao implements IAddressRemoteDao {

    private static AddressRemoteDao instance;
    private AddressClient addressClient;

    public AddressRemoteDao() {
        addressClient = HttpHelper.getInstance().create(AddressClient.class);

    }

    public static synchronized AddressRemoteDao getInstance() {
        if (instance == null) {
            instance = new AddressRemoteDao();
        }


        return instance;
    }

    @Override
    public HttpCall<BaseWrapper> createAddress(String addressName, String buildingNum
            , String floorNum, String apartmentNum, Double lat, Double lng, Integer customerId) {

        Map<String, Object> map = new HashMap<>();
        map.put("addressName", addressName);
        map.put("buildingNumber", buildingNum);
        map.put("floorNumber", floorNum);
        map.put("apartmentNumber", apartmentNum);
        map.put("latitude", lat);
        map.put("longtitude", lng);
        map.put("customerId", customerId);
        return addressClient.createAddress(map);
    }

    @Override
    public HttpCall<BaseGenericWrapper<List<AddressModel>>> getAddressList(Integer id) {
        return addressClient.getAddressList(id);
    }


    @Override
    public HttpCall<BaseWrapper> deleteAddress(Integer addressId) {
        return addressClient.deleteAddress(addressId);
    }

    @Override
    public HttpCall<BaseWrapper> updateAddress(Integer addressId, String addressName, String buildingNum
            , String floorNum, String apartmentNum, Double lat, Double lng, Integer customerId) {
        Map<String, Object> map = new HashMap<>();
        map.put("addressName", addressName);
        map.put("buildingNumber", buildingNum);
        map.put("floorNumber", floorNum);
        map.put("apartmentNumber", apartmentNum);
        map.put("latitude", lat);
        map.put("longtitude", lng);
        map.put("customerId", customerId);
        return addressClient.updateAddress(addressId, map);
    }

    @Override
    public HttpCall<BaseGenericWrapper<AddressModel>> getAddressInfo(Integer addressId) {
        return addressClient.getAddressInfo(addressId);
    }

    private interface AddressClient {
        @POST(ApiConstants.CREATE_ADDRESS)
        HttpCall<BaseWrapper> createAddress(@Body Map<String, Object> map);

        @PUT(ApiConstants.UPDATE_ADDRESS)
        HttpCall<BaseWrapper> updateAddress(@Path("id") Integer id, @Body Map<String, Object> map);

        @DELETE(ApiConstants.DELETE_ADDRESS)
        HttpCall<BaseWrapper> deleteAddress(@Path("id") Integer id);

        @GET(ApiConstants.GET_ALL_ADDRESS)
        HttpCall<BaseGenericWrapper<List<AddressModel>>> getAddressList(@Path("id") Integer id);


        @GET(ApiConstants.GET_ADDRESS_INFO)
        HttpCall<BaseGenericWrapper<AddressModel>> getAddressInfo(@Path("id") Integer id);


    }
}
