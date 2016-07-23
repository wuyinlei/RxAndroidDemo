package yinlei.com.rxandroid.rx_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import yinlei.com.rxandroid.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText userName, passWord;
    private Button btnLogin;

    private LoginUtils mUtils;

    private String url = "http://192.168.1.102:8080/app_dyn/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUtils = new LoginUtils();
        initView();
        initListener();
    }

    private void initListener() {
        btnLogin.setOnClickListener(this);
    }

    private void initView() {
        userName = (EditText) findViewById(R.id.username);
        passWord = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("username", userName.getText().toString().trim());
            params.put("password", passWord.getText().toString().trim());
            mUtils.login(url, params).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    Log.i(TAG, "onCompleted");
                }

                @Override
                public void onError(Throwable e) {
                    Log.i(TAG, e.getMessage());
                }

                @Override
                public void onNext(String s) {
                    if (JsonUtils.parse(s)) {
                        Intent intent = new Intent(LoginActivity.this, ContentActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}

