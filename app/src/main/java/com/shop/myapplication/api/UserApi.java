package com.shop.myapplication.api;

import com.shop.myapplication.entry.EntryData;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/*
 *@Auther:甄帅
 *@Date: 时间
 *@Description:功能
 * */public interface UserApi {

    /**
     * 单图
     * @param header
     * @param file
     * @return
     */

    @POST(Api.UPLOAD_URL)
@Multipart
    Observable<EntryData>getParts(@HeaderMap HashMap<String,String> header, @Part MultipartBody.Part file);
}
