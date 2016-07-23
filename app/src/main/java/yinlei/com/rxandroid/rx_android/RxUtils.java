package yinlei.com.rxandroid.rx_android;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * RxAndroid工具类
 *
 * @version V1.0 展示RxAndroid的几种调用方式
 * @FileName: RxUtils.java
 * @author: 若兰明月
 * @date: 2016-07-22 22:56
 */

public class RxUtils {

    private static final String TAG = RxUtils.class.getSimpleName();

    /**
     * 使用create方式
     */
    public static void createObserable() {
        //定义被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) { //观察者和被观察者还有订阅消息
                    subscriber.onNext("hello");
                    subscriber.onNext("hi");
                    subscriber.onNext(getUserName());  //因为是传入的是字符串泛型
                    subscriber.onCompleted(); //完成
                }
            }
        });

        //定义观察者
        Subscriber showSub = new Subscriber() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");   //用于对话框消失
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, e.getMessage());   //错误处理
            }

            @Override
            public void onNext(Object o) {
                Log.i(TAG, o.toString());
            }
        };

        observable.subscribe(showSub); //两者产生订阅
    }

    /**
     * 可以用来写成我们的下载返回数据
     *
     * @return
     */
    public static String getUserName() {
        return "jsonName";
    }

    /**
     * 打印的功能
     */
    public static void createPrint() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 0; i < 10; i++) {
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "result--->:" + integer);
            }
        });
    }


    /**
     * 使用在被观察者，返回的对象一般都是数据类型
     */
    public static void from() {
        Integer[] items = {1, 2, 3, 4, 5, 6, 7, 8};
        Observable onservable = Observable.from(items);
        onservable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.i(TAG, o.toString());
            }
        });
    }


    /**
     * 指定某一时刻进行数据发送
     */
    public static void interval() {
        Integer[] items = {1, 2, 3, 4};
        Observable observable = Observable.interval(1, 1, TimeUnit.SECONDS);
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.i(TAG, o.toString());
            }
        });
    }

    /**
     *
     */
    public static void just() {
        Integer[] items1 = {1, 2, 3, 4};
        Integer[] items2 = {2, 4, 6, 8};

        Observable observable = Observable.just(items1, items2);
        observable.subscribe(new Subscriber<Integer[]>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onNext(Integer[] integers) {
                for (int i = 0; i < integers.length; i++) {
                    Log.i(TAG, "result--->" + i);
                }
            }
        });
    }

    /**
     * 指定输出数据的范围
     */
    public static void range() {
        Observable observable = Observable.range(1, 4);
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onNext(Integer o) {
                Log.i(TAG, "next---->" + o);
            }
        });
    }


    /**
     * 使用过滤功能  发送消息的时候，先过滤在发送
     */
    public static void filter() {
        Observable observable = Observable.just(1, 2, 3, 4, 5, 6);
        observable.filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer o) {
                return o < 5;
            }
        }).observeOn(Schedulers.io()).subscribe(new Subscriber() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                Log.i(TAG, o.toString());
            }
        });
    }

}
