package yinlei.com.rxandroid.xt_observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: yinlei.com.rxandroid.xt_observer.XTobserver.java
 * @author: myName
 * @date: 2016-07-22 23:44
 */

public class XTobserver implements Observer {

    public XTobserver(XTObservable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        System.out.println("data is changed" + ((XTObservable) observable).getData());
    }
}
