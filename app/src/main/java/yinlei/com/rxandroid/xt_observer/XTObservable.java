package yinlei.com.rxandroid.xt_observer;

import java.util.Observable;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: yinlei.com.rxandroid.xt_observer.XTObservable.java
 * @author: myName
 * @date: 2016-07-22 23:42
 */

public class XTObservable extends Observable {

    private int data = 0;

    public int getData(){
        return data;
    }

    public void setData(int i){
        if (this.data != i){
            this.data = i;
            setChanged();//发生改变
            notifyObservers();//通知观察者
        }
    }
}
