package yinlei.com.rxandroid.observer;

/**

 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: Watched.java
 * @author: myName
 * @date: 2016-07-15 22:03
 */

public interface Watched {
    //添加观察者
    public void addWatcher(Watcher watcher);
    //移除观察者
    public void removeWatcher(Watcher watcher);
    //通知观察者
    public void notifyWathers(String str);
}
