package yinlei.com.rxandroid.xt_observer;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: yinlei.com.rxandroid.xt_observer.Test.java
 * @author: myName
 * @date: 2016-07-22 23:47
 */

public class Test {

    public static void main(String[] args) {

        XTObservable mObservable = new XTObservable();
        XTobserver mXTobserver = new XTobserver(mObservable);
        mObservable.setData(1);
        mObservable.setData(2);
        mObservable.setData(3);
    }
}
