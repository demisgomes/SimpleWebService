package com.simplewebservice.domain;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Demis on 10/24/16.
 */
public interface WebServiceInterface {
    @FormUrlEncoded
    @POST
    Call<Choice> postChoice(@Field("pk") int pk, @Field("choice_text") String choiceText, Callback<ServerResponse> serverResponseCallback );
}
