package cn.nightcoder.fasdroid.net.request.service;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by xuedakun on 2019/12/12 16:49
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public interface IRxService {

    @GET
    Observable<ResponseBody> doGet(@Url String url);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> doPost(@Url String url, @FieldMap HashMap<String, Object> params);

    @GET
    Observable<ResponseBody> doDownload(@Url String url);
}
