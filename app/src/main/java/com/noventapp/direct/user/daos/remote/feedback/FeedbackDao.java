package com.noventapp.direct.user.daos.remote.feedback;

import com.noventapp.direct.user.constants.ApiConstants;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.data.network.HttpHelper;
import com.noventapp.direct.user.model.BaseWrapper;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class FeedbackDao implements IFeedbackDao {


    private static FeedbackDao instance;
    private IFeedbackClient feedbackClient;

    public FeedbackDao() {
        feedbackClient = HttpHelper.getInstance().create(IFeedbackClient.class);

    }

    public static synchronized FeedbackDao getInstance() {
        if (instance == null) {
            instance = new FeedbackDao();
        }
        return instance;
    }

    @Override
    public HttpCall<BaseWrapper> createFeedback(Integer id, String textFeedback) {
        Map<String, Object> map = new HashMap<>();
        map.put("feedBackText", textFeedback);
        return feedbackClient.createFeedback(id, map);
    }


    private interface IFeedbackClient {
        @POST(ApiConstants.CREATE_FEEDBACK)
        HttpCall<BaseWrapper> createFeedback(@Path("id") Integer id, @Body Map<String, Object> map);
    }


}

