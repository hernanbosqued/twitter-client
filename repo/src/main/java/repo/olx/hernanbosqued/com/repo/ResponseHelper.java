package repo.olx.hernanbosqued.com.repo;

import com.hernanbosqued.olx.domain.ModelCallbacks;
import com.hernanbosqued.olx.domain.ErrorModel;

import okhttp3.ResponseBody;
import retrofit2.Response;

import static repo.olx.hernanbosqued.com.repo.Constants.CUSTOM_ERROR_CODE;
import static repo.olx.hernanbosqued.com.repo.Constants.NULL_ERROR_MESSAGE;
import static repo.olx.hernanbosqued.com.repo.Constants.NULL_RESPONSE_MESSAGE;

class ResponseHelper<T> {

    private ErrorModel.Error parseError(ResponseBody responseBody) {
        ErrorModel response = ServiceGenerator.getInstance().parseResponse(ErrorModel.class, responseBody);
        if (response != null) {
            return response.errors.get(0);
        } else {
            return new ErrorModel.Error(CUSTOM_ERROR_CODE, NULL_ERROR_MESSAGE);
        }
    }

    void processResponse(Response<T> response, ModelCallbacks<T> callback) {
        if (response.isSuccessful()) {
            T body = response.body();
            if (body != null) {
                callback.onSuccess(body);
            } else {
                callback.onFail(new ErrorModel.Error(CUSTOM_ERROR_CODE, NULL_RESPONSE_MESSAGE));
            }
        } else {
            callback.onFail(parseError(response.errorBody()));
        }
    }
}
