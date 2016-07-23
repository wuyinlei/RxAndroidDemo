package yinlei.com.rxandroid.rx_login;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: yinlei.com.rxandroid.rx_login.LoginUtils.java
 * @author: myName
 * @date: 2016-07-23 11:22
 */

public class LoginUtils {

    private OkHttpClient mOkHttpClient;

    public LoginUtils() {
        mOkHttpClient = new OkHttpClient();
    }

    /**
     *
     * @param url  登录地址
     * @param params  请求参数
     * @return   后台返回的数据
     */
    public Observable<String> login(String url, Map<String, String> params) {

        return Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            if (!subscriber.isUnsubscribed()) {
                //创建formbody
                FormBody.Builder builder = new FormBody.Builder();
                if (params != null && !params.isEmpty()) {
                    //循环获取body中的数据
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        builder.add(entry.getKey(), entry.getValue());
                    }
                }
                //请求体
                RequestBody requestBody = builder.build();
                Request request = new Request.Builder().url(url).post(requestBody).build();
                mOkHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            //交给观察者处理数据
                            subscriber.onNext(response.body().string());
                        }
                        //完成的回调
                        subscriber.onCompleted();
                    }
                });
            }
        });
    }
}
