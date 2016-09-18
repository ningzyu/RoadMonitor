package com.sxhxjy.roadmonitor.base;



import com.sxhxjy.roadmonitor.entity.Monitor;
import com.sxhxjy.roadmonitor.entity.Station;

import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * http request goes here...
 *
 * BASE_URL:    http://183.203.28.91:3000/yw_php_api/
 *
 * @author Michael Zhao
 *
 */
public interface HttpService {

    /////////////////////////////////////////////////////////////////////////
    ////  general
    /////////////////////////////////////////////////////////////////////////
    @FormUrlEncoded
    @POST("points/pointList")
    Observable<HttpResponse<Monitor>> getMonitors(@Field("pageNumber") int pageIndex, @Field("pageSize") int pageSize, @Field("name") String name);
//    @GET("points/allPointList")
//    Observable<HttpResponse<List<Monitor>>> getMonitors(@Query("pageNumber") int pageIndex, @Query("pageSize") int pageSize, @Query("name") String name);


    @POST("stations/stationList")
    @FormUrlEncoded
    Observable<HttpResponse<Station>> getStations(@Field("pageNumber") int pageIndex, @Field("pageSize") int pageSize, @Field("name") String name);




    @GET("points/pointDetail")
    Observable<HttpResponse<Monitor>> getMonitor(@Query("id") String id);

    @GET("stations/stationDetail")
    Observable<HttpResponse<Station>> getStation(@Query("id") String id);










    @Multipart
    @POST("jk/uploadPic.htm")
    Call<BaseResult> uploadGoods(@Part MultipartBody.Part file);



    @GET("visitPlan/findByUserid.htm")
    Call<BaseResult> getPlans(@Query("vcUserid") String uid, @Query("state") Integer state, @Query("stateOpt") String stateOpt, @Query("planTime") String time, @Query("planTimeOpt") String planTimeOpt);

    @GET("business/findByUserid.htm")
    Call<BaseResult> getCustomers(@Query("vcUserid") String uid);



    /////////////////////////////////////////////////////////////////////////
    ////  home
    /////////////////////////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////////////////////
    ////  publish
    /////////////////////////////////////////////////////////////////////////

    @GET("visitPlan/msave.htm")
    Call<BaseResult> submitPlan(@QueryMap Map<String, String> map);

    @GET("visitPlan/addBusiness.htm")
    Call<BaseResult> submitCustomer(@QueryMap Map<String, String> map);


    class BaseResult {
    }
}
