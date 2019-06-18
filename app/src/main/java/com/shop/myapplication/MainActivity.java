package com.shop.myapplication;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shop.myapplication.api.UserApi;
import com.shop.myapplication.entry.EntryData;
import com.shop.myapplication.net.HttpUtils;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
/*
 *上传头像
* */
@BindView(R.id.tv)
Button textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否挂载
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    //创建文件
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "shuai.jpg");
                    //创建文件请求对象
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);//上传context-type类型
                    //多表单上传的工具类
                    MultipartBody.Part imgPart = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
                    HashMap<String,String>hashMap=new HashMap<>();
                    hashMap.put("userId","4800");
                    hashMap.put("sessionId","15608653139214800");
                    HttpUtils.getInstance().creat(UserApi.class).getParts(hashMap,imgPart)
                            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<EntryData>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(EntryData entryData) {
                                    Log.d("123", "onNext: "+entryData.headPath);
                                    Log.d("123", "onNext: "+entryData.message);
                                    Log.d("123", "onNext: "+entryData.status);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }
            }
        });

    }
}
