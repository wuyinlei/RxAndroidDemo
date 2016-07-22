package yinlei.com.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import yinlei.com.rxandroid.rx_android.RxUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void create(View view){
        RxUtils.createObserable();
    }

    public void print(View view){
        RxUtils.createPrint();
    }

    public void from(View view){
        RxUtils.from();
    }

    public void interval(View view){
        RxUtils.interval();
    }

    public void just(View view){
        RxUtils.just();
    }

    public void range(View view){
        RxUtils.range();
    }

    public void filter(View view){
        RxUtils.filter();
    }
}
