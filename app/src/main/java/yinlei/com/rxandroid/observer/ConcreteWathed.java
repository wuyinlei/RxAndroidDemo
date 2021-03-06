package yinlei.com.rxandroid.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: ConcreteWathed.java
 * @author: myName
 * @date: 2016-07-15 22:05
 */

public class ConcreteWathed implements Watched {
    //观察者
    List<Watcher> mList = new ArrayList<>();

    @Override
    public void addWatcher(Watcher watcher) {
        mList.add(watcher);
    }

    @Override
    public void removeWatcher(Watcher watcher) {
        mList.remove(watcher);
    }

    @Override
    public void notifyWathers(String str) {
        for (Watcher w : mList) {
            w.update(str);
        }
    }
}
