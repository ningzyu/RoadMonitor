package com.sxhxjy.roadmonitor.base;



import com.sxhxjy.roadmonitor.entity.AlertData;
import com.sxhxjy.roadmonitor.entity.AlertTree;
import com.sxhxjy.roadmonitor.entity.GroupTree;
import com.sxhxjy.roadmonitor.entity.LoginData;
import com.sxhxjy.roadmonitor.entity.Monitor;
import com.sxhxjy.roadmonitor.entity.MonitorTypeTree;
import com.sxhxjy.roadmonitor.entity.RealTimeData;
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
 * BASE_URL:
 *
 * @author Michael Zhao
 *
 */
public interface HttpService {

    /////////////////////////////////////////////////////////////////////////
    ////  general
    /////////////////////////////////////////////////////////////////////////

//    @FormUrlEncoded
//    @POST("stations/findPointByStationId")
@GET("stations/findPointByStationId")
    Observable<HttpResponse<List<Monitor>>> getMonitors(@Query("stationId") String stationId);

    @GET("stations/findStationByGroupId")
//    @FormUrlEncoded
    Observable<HttpResponse<List<Station>>> getStations(@Query("groupId") String groupId);


    @GET("userGroup/userGroupTreeList")
    Observable<HttpResponse<List<GroupTree>>> getGroups(@Query("gid") String gid);

    @GET("user/appLogin")
    Observable<HttpResponse<LoginData>> login(@Query("account") String username, @Query("password") String password);

    @GET("sensorData/dataList")
    Observable<HttpResponse<List<RealTimeData>>> getRealTimeData(@Query("code") String positionID, @Query("beforeTime") long start, @Query("afterTime") long end);


    @GET("points/pointDetail")
    Observable<HttpResponse<Monitor>> getMonitor(@Query("id") String id);

    @GET("points/pointTreeList")
    Observable<HttpResponse<List<MonitorTypeTree>>> getMonitorTypeTree();

    @GET("alarm/getAlermFilter")
    Observable<HttpResponse<AlertTree>> getAlertTree();

    @GET("stations/findStationByFilters")
    Observable<HttpResponse<List<MonitorPosition>>> getPositions(@Query("pointId") String pointId, @Query("gid") String gid);

    @GET("stations/stationDetail")
    Observable<HttpResponse<ParamInfo>> getParamInfo(@Query("id") String id);

    @FormUrlEncoded
    @POST("alarm/confirmAlarmInfo")
    Observable<HttpResponse<Object>> confirmAlertMsg(@Field("alarmId") String alarmId, @Field("userId") String userId, @Field("confirmMsg") String confirmMsg, @Field("stime") String startTime, @Field("etime") String endTime);

    @FormUrlEncoded
    @POST("user/editPwd")
    Observable<HttpResponse<Object>> changePassword(@Field("userId") String uid, @Field("oldPwd") String old, @Field("newPwd") String newP);

    @Multipart

    @POST("jk/uploadPic.htm")
    Call<HttpResponse<String>> uploadImage(@Part MultipartBody.Part file);

    @GET("alarm/findByStationId")
    Observable<HttpResponse<List<AlertData>>> getAlertDataDetail(@Query("stationId") String stationId, @Query("stime") String startTime, @Query("etime") String endTime);

    @GET("alarmUnionData/pageList")
    Observable<HttpResponse<List<AlertData>>> getAlertDataList(@Query("cStype") String type);

    /////////////////////////////////////////////////////////////////////////
    ////  home
    /////////////////////////////////////////////////////////////////////////
}
