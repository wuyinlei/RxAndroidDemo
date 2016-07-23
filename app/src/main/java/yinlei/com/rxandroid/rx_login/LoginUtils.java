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

    public Observable<String> login(String url, Map<String, String> params) {

        return Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            if (!subscriber.isUnsubscribed()) {
                FormBody.Builder builder = new FormBody.Builder();
                if (params != null && !params.isEmpty()) {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        builder.add(entry.getKey(), entry.getValue());
                    }
                }
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
                            subscriber.onNext(response.body().string());
                        }
                        subscriber.onCompleted();
                    }
                });
            }
        });
    }
}
