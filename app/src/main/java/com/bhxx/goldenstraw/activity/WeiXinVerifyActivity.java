package com.bhxx.goldenstraw.activity;
/**
 * 微信认证
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.utils.ActivityCollector;

public class WeiXinVerifyActivity extends BasicActivity implements View.OnClickListener {

    private ImageView titleBack;
    private TextView titleTitle;

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void click(View view) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin_verify);

        initView();

    }

    private void initView() {
        titleBack = (ImageView) findViewById(R.id.titleBack);
        titleBack.setOnClickListener(this);
        titleTitle = (TextView) findViewById(R.id.titleTitle);
        titleTitle.setText("微信认证");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleBack:
                finish();
                break;
        }
    }
}
