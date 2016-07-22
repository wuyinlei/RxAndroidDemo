package yinlei.com.rxandroid.observer;

/**

 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: yinlei.com.rxandroid.observer.Watched.java
 * @author: myName
 * @date: 2016-07-15 22:03
 */

public interface Watched {

    public void addWatcher(Watcher watcher);

    public void removeWatcher(Watcher watcher);

    public void notifyWathers(String str);
}
