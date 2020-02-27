package com.hernanbosqued.twitter_client.repo;

import com.hernanbosqued.twitter_client.domain.ModelCallbacks;
import com.hernanbosqued.twitter_client.domain.model.ErrorModel;
import com.hernanbosqued.twitter_client.domain.model.ErrorsModel;

import okhttp3.ResponseBody;
import retrofit2.Response;

class ResponseHelper<T> {

    private ErrorModel parseError(ResponseBody responseBody) {
        ErrorsModel response = ServiceGenerator.getInstance().parseResponse(ErrorsModel.class, responseBody);
        if (response != null && response.errors != null ){
            return response.errors.get(0);
        } else {
            return new ErrorModel(Constants.CUSTOM_ERROR_CODE, Constants.NULL_ERROR_MESSAGE);
        }
    }

    void processResponse(Response<T> response, ModelCallbacks<T> callback) {
        if (response.isSuccessful()) {
            T body = response.body();
            if (body != null) {
                callback.onSuccess(body);
            } else {
                callback.onFail(new ErrorModel(Constants.CUSTOM_ERROR_CODE, Constants.NULL_RESPONSE_MESSAGE));
            }
        } else {
            callback.onFail(parseError(response.errorBody()));
        }
    }
}
