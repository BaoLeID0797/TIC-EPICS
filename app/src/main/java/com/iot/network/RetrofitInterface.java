package com.iot.network;

import com.iot.model.Response;

import retrofit2.http.GET;
import rx.Observable;

public interface RetrofitInterface {

  /*  @POST("users")
    Observable<Response> register(@Body User user);

    @POST("authenticate")
    Observable<Response> login();

    @GET("users/{email}")
    Observable<User> getProfile(@Path("email") String email);

    @PUT("users/{email}")
    Observable<Response> changePassword(@Path("email") String email, @Body User user);

    @POST("users/{email}/password")
    Observable<Response> resetPasswordInit(@Path("email") String email);

    @POST("users/{email}/password")
    Observable<Response> resetPasswordFinish(@Path("email") String email, @Body User user);*/

  @GET("channels/470455/feeds.json?api_key=MWREYTNW0VT7YBT6")
  Observable<Response> data();
}
