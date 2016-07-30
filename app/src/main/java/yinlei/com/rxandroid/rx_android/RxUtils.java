package yinlei.com.rxandroid.rx_android;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

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
                    subscriber.onNext("hello"); //返回的数据
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
     * 打印的功能  链式结构，更加易于代码的可读性
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
     * 它接收一个集合作为输入，然后每次输出一个元素给subscriber
     * from()创建符可以从一个列表/数组来创建Observable，冰一个接一个的从列表/数组中
     * 发射出来每一个对象，或者也可以从Java Futrue类来创建Observable，并发射Future对象的.get()方法
     * 返回的结果值，传入Future作为参数的时候，我们可以指定一个超市的值，
     * Observable将等待来自Future的结果，如果在超时的时候仍没有返回结果，Observable将会触发onError()
     * 方法通吃观察者有错误发生了
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
     * interval()函数的两个参数：一个指定两次发射的时间间隔，另一个是用到的时间单位
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
     * 假如我们只有3个独立的AppInfo对象并且我们想把他们转化为Observable并填充到RecyclerView的item中：
     * 这里我们有两个数组，然后通过转化为Observable组成一个item
     * <p>
     * 如果我们已经有了一个传统的Java函数，我们想把它变成一个Observable，我们可以使用create()方法，
     * 不管怎样，just可以是我们想要的任何函数，just()方法可以传入一到九个参数，我们会按照传入的参数的顺序
     * 来发射他们，just()方法可以接受列表或者数组，就像from()方法，但是它不会迭代列表发射每一个值，它将
     * 发射整个列表，通常，当我们想发射一组已经定义好的值的时候会用到他，但是如果我们的函数不是时变性的，我们
     * 可以用just来创建一个更有组织性的和可测性的代码库
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
     * Observable.empty(),Observable.never()和Observable.throw()
     * 当我们需要一个Observable毫无理由的不在发射数据正常结束的时候，我们可以使用empty()
     * 我们可以使用never()创建一个不发射数据并且永远不会结束的Observable
     * 我们也可以使用throw()创建一个不发射数据并且以错误结束的Observable
     */

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

    /**
     * Subject = Observable + Observer
     * subject是一个神器的对象，它可以是一个Observable同时也可以是一个Observer，它作为连接
     * 两个世界的一座桥梁，一个Subject可以订阅一个Observable，就像一个观察者，并且他可以发射新的
     * 数据，或者传递他接收到的数据，就像Observable，很明显，作为一个Observable，观察者们或者其
     * 他的Subject都可以订阅他
     * <p>
     * 一旦Subject订阅了Observable，它将触发Observable开始发射，如果原始的Observable是“冷”的
     * 它将会对订阅一个“热”的Observable变量产身影响
     * RxJava提供四种不同的Subjet
     * <p>
     * PublishSubject 是subject的一个基础子类， 我们在下面的例子中创建了一个PublishSubject，
     * 用create()方法发射了一个string值，然后我们订阅了PublishSubject，此时没有数据要发送呢，因此我们的观察者
     * 只能等待，没有阻塞线程，也没有消耗资源，就在这随时准备从subjec接受值，如果subject没有发射值
     * 那么我们的观察者就会一直去等待，   无须担心---->观察者知道在每个场景中该做些什么，我们不用担心什么
     * 时候是因为他是响应的，系统会响应，我们并不关心他什么时候响应，我们只需要关心它响应的时候该做些什么
     *
     * BehaviorSubject
     * 简单的说BehaviorSubject会首先向它的订阅者发送截至订阅前的最新的一个数据对象(或初始值)，然后发送订阅后的数据流
     *
     * ReplaySubject
     * 会缓存它所订阅的所有数据，向任意一个订阅他的观察者重发
     *
     * AsyncSubject
     * 当observable完成AsyncSubject只会发布最后一个数据给已经订阅的每一个观察者
     */
    public static void publicObservable() {
        PublishSubject<String> stringPublishSubject = PublishSubject.create();
        stringPublishSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "完成");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, s);
            }
        });
        stringPublishSubject.onNext("hello world");

    }

    /**
     * 例子：
     * 我们有个private声明的Observable，外部不能访问，Observable在它的生命周期内发射值，我们不用关心
     * 这些值，我们只关心他们的结束
     *
     */
    public static void privatePublish() {
        //首先我们创建一个新的PublishSubject来响应啊的onNext()方法，并且外部也可以访问
        final PublishSubject<Boolean> subject = PublishSubject.create();
        subject.subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                Log.d(TAG, "aBoolean:" + aBoolean);
            }
        });

        //然后我们创建我们私有的Observable，只有subject才可以访问的到
        //Observable.create()方法包含了我们熟悉的循环  发射数字
        //
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
            //doOnCompleted()方法指定当Observable结束的时候要做什么事情，在subject发射
            //true的时候，最后我们定于和了observable，很明显，空的subscribe()调用仅仅是为了
            //开启Observable，而不用管已发出的任何值，也不用管完成时间或者错误时间
        }).doOnCompleted(() -> subject.onNext(true)).subscribe();


    }

}
