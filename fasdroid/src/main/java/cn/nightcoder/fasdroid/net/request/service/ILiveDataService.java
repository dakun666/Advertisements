package cn.nightcoder.fasdroid.net.request.service;

import androidx.lifecycle.LiveData;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by xuedakun on 2019-12-03 13:48
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public interface ILiveDataService {

    @GET
    LiveData<String> doGet(@Url String url);

    @FormUrlEncoded
    @POST
    LiveData<String> doPost(@Url String url, @FieldMap HashMap<String, Object> params);

    @GET
    LiveData<ResponseBody> doDownload(@Url String url);

    @Multipart
    @POST
    LiveData<String> doUpload(@Part MultipartBody.Part file, @QueryMap HashMap<String, Object> params);
}
