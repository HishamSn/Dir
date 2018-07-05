package com.noventapp.direct.user.daos.remote.feedback;

import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.model.BaseWrapper;

public interface IFeedbackDao {


    HttpCall<BaseWrapper> createFeedback(Integer id, String textFeedbacck);
}
