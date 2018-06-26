package com.noventapp.direct.user.data.network;


import com.noventapp.direct.user.constants.ApiConstants;
import com.noventapp.direct.user.data.prefs.PrefsUtils;
import com.noventapp.direct.user.model.ErrorModel;
import com.noventapp.direct.user.utils.LocalHelper;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static com.noventapp.direct.user.constants.AppConstants.AR;
import static com.noventapp.direct.user.constants.AppConstants.EN;

/*
 * Created by Hisham Snaimeh on 5/6/2018.
 */
public class HttpHelper {

    //    private static final String token = "Basic VVNFUjokRHJIZWFsdGkjVjE=";
    private static HttpHelper instance;
    private Retrofit retrofit;

    public static HttpHelper getInstance() {
        synchronized (HttpHelper.class) {
            if (instance == null) {
                instance = new HttpHelper();
            }
        }
        return instance;
    }

    private HttpHelper() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(new HttpFactory())
                .client(getClient())
                .build();
    }

    public <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }

    private OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader("Content-Type", "application/json");
                    builder.addHeader("Accept", "application/json");
                    builder.addHeader("Authorization", "Bearer " + PrefsUtils.getInstance().getToken());
                    builder.addHeader("Accept-Language", LocalHelper.isLanguageEn() ? EN : AR);
                    return chain.proceed(builder.build());
                }).build();


    }

    public <T> ErrorModel getError(Response<T> response) {
        if (response == null || response.errorBody() == null) {
            return new ErrorModel();
        }
        Converter<ResponseBody, ErrorModel> converter =
                retrofit.responseBodyConverter(ErrorModel.class, new Annotation[0]);
        ErrorModel error = new ErrorModel();
        try {
            ResponseBody errorBody = response.errorBody();
            if (errorBody != null) {
                error = converter.convert(errorBody);
            }
        } catch (IOException e) {
            return new ErrorModel();
        }
        return error;
    }
}
