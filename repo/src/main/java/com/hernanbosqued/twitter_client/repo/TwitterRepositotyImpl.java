package com.hernanbosqued.twitter_client.repo;

import android.support.annotation.NonNull;
import android.util.Base64;

import com.hernanbosqued.twitter_client.domain.ModelCallbacks;
import com.hernanbosqued.twitter_client.domain.model.ErrorModel;
import com.hernanbosqued.twitter_client.domain.model.TokenModel;
import com.hernanbosqued.twitter_client.domain.model.TwitterModel;
import com.hernanbosqued.twitter_client.domain.TwitterRepository;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static okhttp3.internal.Util.UTF_8;


public class TwitterRepositotyImpl implements TwitterRepository {
    private TokenModel token;
    private final TwitterService service;

    private static TwitterRepositotyImpl instance;

    public static TwitterRepositotyImpl getInstance() {
        if (instance == null) {
            instance = new TwitterRepositotyImpl();
        }
        return instance;
    }

    private TwitterRepositotyImpl() {
        service = ServiceGenerator.getInstance().createService(TwitterService.class);
    }

    private String getEncodedCredential() {
        String credential = Constants.CONSUMER_KEY + ":" + Constants.CONSUMER_SECRET;
        byte[] encodedCredential = credential.getBytes(UTF_8);
        return Base64.encodeToString(encodedCredential, Base64.NO_WRAP);
    }

    public interface TokenCallback {
        void onSession(String token);
    }

    private void getToken(final ModelCallbacks<TokenModel> callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.REQUEST_BODY_TYPE), Constants.OAUTH_REQUEST_BODY);
        service.getToken(Constants.TOKEN_AUTH_PREFIX + getEncodedCredential(), requestBody).enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(@NonNull Call<TokenModel> call, @NonNull Response<TokenModel> response) {
                new ResponseHelper<TokenModel>().processResponse(response, callback);
            }

            @Override
            public void onFailure(@NonNull Call<TokenModel> call, @NonNull Throwable t) {
                callback.onFail(new ErrorModel(-1, t.getMessage()));
            }
        });
    }

    private void askForToken(final TokenCallback tokenCallback, final ModelCallbacks<?> modelCallback) {
        if (token != null) {
            tokenCallback.onSession(token.access_token);
        } else {
            getToken(new ModelCallbacks<TokenModel>() {
                @Override
                public void onSuccess(TokenModel token) {
                    TwitterRepositotyImpl.this.token = token;
                    tokenCallback.onSession(token.access_token);
                }

                @Override
                public void onFail(ErrorModel errorModel) {
                    modelCallback.onFail(errorModel);
                }
            });
        }
    }

    @Override
    public void getTweets(final String query, final ModelCallbacks<TwitterModel> callback) {
        askForToken(new TokenCallback() {
            @Override
            public void onSession(String token) {
                service.getTweets(Constants.AUTH_PREFIX + token, query).enqueue(new Callback<TwitterModel>() {
                    @Override
                    public void onResponse(@NonNull Call<TwitterModel> call, @NonNull Response<TwitterModel> response) {
                        new ResponseHelper<TwitterModel>().processResponse(response, callback);
                    }

                    @Override
                    public void onFailure(@NonNull Call<TwitterModel> call, @NonNull Throwable t) {
                        callback.onFail(new ErrorModel(Constants.CUSTOM_ERROR_CODE, t.getMessage()));
                    }
                });
            }
        }, callback);
    }
}
