package yinlei.com.rxandroid.rx_down_image;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * 使用Rx下载图片工具类
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: Utils.java
 * @author: 若兰明月
 * @date: 2016-07-23 00:12
 */

public class Utils {

    private OkHttpClient mOkHttpClient;

    public Utils() {
        mOkHttpClient = new OkHttpClient();
    }

    /**
     * 声明一个被观察者对象，作为结果返回
     */
    public Observable<byte[]> downLoadImage(String path) {
        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(Subscriber<? super byte[]> subscriber) {
                if (!subscriber.isUnsubscribed()) {  //存在订阅关系
                    //访问网络操作
                    //请求体
                    Request request = new Request.Builder().url(path).get().build();
                    //异步回调
                    mOkHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                byte[] bytes = response.body().bytes();
                                if (bytes != null) {
                                    subscriber.onNext(bytes);  //返回结果
                                }
                            }
                            subscriber.onCompleted();  //访问完成
                        }
                    });

                }
            }
        });
    }


    /**
     * 声明一个被观察者对象，作为结果返回
     *
     * @param path
     * @return
     */
    public Observable<byte[]> downLoadImageOne(String path) {
        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(Subscriber<? super byte[]> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    //访问网络操作
                    Request.Builder builder = new Request.Builder();
                    Request request = builder.url(path).get().build();
                   //Request request = new Request.Builder().url(path).build();
                    mOkHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                byte[] data = response.body().bytes();
                                if (data != null) {
                                    subscriber.onNext(data);
                                }
                            }
                            subscriber.onCompleted();
                        }
                    });

                }
            }
        });
    }

}
