package repo.olx.hernanbosqued.com.repo;

import com.hernanbosqued.olx.domain.model.TokenModel;
import com.hernanbosqued.olx.domain.model.TwitterModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TwitterService {

    @Headers({"grant_type: client_credentials,", "Content-Type: application/x-www-form-urlencoded;charset=UTF-8"})
    @POST("oauth2/token")
    Call<TokenModel> getToken(@Header("Authorization") String authorization, @Body RequestBody body);

    @GET("1.1/search/tweets.json?tweet_mode=extended&count=100")
    Call<TwitterModel> getTweets(@Header("Authorization") String authorization,
                                 @Query("q") String query);
}