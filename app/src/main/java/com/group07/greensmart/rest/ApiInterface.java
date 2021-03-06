package com.group07.greensmart.rest;

import com.group07.greensmart.model.AgriculturalProduct;
import com.group07.greensmart.model.ApiResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by nguyenvuhuy on 4/2/18.
 */

public interface ApiInterface {

    /**
     * TODO: Agricultural Product API
     */

    @GET("agricultural-product/list")
    Call<ApiResponse> getAGPList();


    @GET("agricultural-product/view/{id}")
    Call<ApiResponse> viewAGP(@Path("id") String id, @Query("sort") String sort);


    @GET("agricultural-product/delete/{id}/{rev}")
    Call<ApiResponse> deleteAGP(@Path("id") String id, @Path("rev") String rev);


    @POST("agricultural-product/get-compatible-weather-list")
    Call<ApiResponse> getCompatibleWeatherList(@Body AgriculturalProduct agp);

    /*https://stackoverflow.com/questions/39953457/how-to-upload-image-file-in-retrofit-2*/
    @Multipart
    @POST("agricultural-product/create")
    Call<ApiResponse> createAGP(
            @PartMap Map<String, RequestBody> params

    );

    @Multipart
    @POST("agricultural-product/update")
    Call<ApiResponse> updateAGP(
            @PartMap Map<String, RequestBody> params

    );

    /**
     * TODO: Agricultural Open Weather API
     */

    @GET("open-weather/weather")
    Call<ApiResponse> getCurrentInternetWeather(@Query("lat") String lat, @Query("lng") String lng);


    /**
     * TODO: Agricultural Notification API
     */

    @GET("notifications/list")
    Call<ApiResponse> getNotificationList(@Query("sort") String sort);

    @GET("notifications/view/{id}")
    Call<ApiResponse> viewNotification(@Path("id") String id);


}
