package yinlei.com.rxandroid.rx_down_image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import yinlei.com.rxandroid.R;

public class DownLoadImageActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = DownLoadImageActivity.class.getSimpleName();

    private String url = "http://pic29.nipic.com/20130508/9252150_163600489317_2.jpg";

    private ImageView mImageView;
    private Button btnLoad;

    private Utils mUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load_image);
        mUtils = new Utils();
        mImageView = (ImageView) findViewById(R.id.imageView);
        btnLoad = (Button) findViewById(R.id.button);
        btnLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            //使用HTTP协议获取数据
            mUtils.downLoadImageOne(url)
                    .subscribeOn(Schedulers.io())  //在子线程请求
                    .observeOn(AndroidSchedulers.mainThread()) //结果返回到主线程这一步很厉害啊，不用我们去用handler或者async切换线程了
                    // 主要我们去调用一下代码，就已经帮我们切换好了线程，是不是感觉有点很厉害啊
                    .subscribe(new Subscriber<byte[]>() {
                @Override
                public void onCompleted() {
                    Log.i(TAG,"onCompleted");//对话框消失
                }

                @Override
                public void onError(Throwable e) {
                    Log.i(TAG,e.getMessage());
                }

                @Override
                public void onNext(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    mImageView.setImageBitmap(bitmap);
                }
            });
        }
    }
}
