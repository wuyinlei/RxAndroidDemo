package yinlei.com.rxandroid.observer;

/**

 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: Test.java
 * @author: myName
 * @date: 2016-07-15 22:07
 */

public class Test {

    public static void main(String[] args){
        Watched watched = new ConcreteWathed();
        Watcher watcher1 = new ConcreteWather();
        Watcher watcher2 = new ConcreteWather();
        Watcher watcher3 = new ConcreteWather();

        watched.addWatcher(watcher1);
        watched.addWatcher(watcher2);
        watched.addWatcher(watcher3);

        watched.notifyWathers("I go");
    }
}
