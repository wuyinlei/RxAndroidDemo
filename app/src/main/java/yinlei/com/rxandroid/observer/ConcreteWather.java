package yinlei.com.rxandroid.observer;

/**
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: yinlei.com.rxandroid.observer.ConcreteWather.java
 * @author: myName
 * @date: 2016-07-15 22:08
 */

public class ConcreteWather implements Watcher {
    @Override
    public void update(String str) {
        System.out.println(str);
    }
}
