package com.bhxx.goldenstraw.activity;
/**
 * 更换手机号
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectLayer;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.utils.ActivityCollector;

public class ChangePhoneActivity extends BasicActivity implements View.OnClickListener {
    private ImageView titleBack;
    private TextView titleTitle, changePhone_sure;

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
        setContentView(R.layout.activity_change_phone);
        initView();
    }

    private void initView() {
        titleBack = (ImageView) findViewById(R.id.titleBack);
        titleBack.setOnClickListener(this);
        titleTitle = (TextView) findViewById(R.id.titleTitle);
        changePhone_sure = (TextView) findViewById(R.id.changePhone_sure);
        changePhone_sure.setOnClickListener(this);
        titleTitle.setText("更换手机号");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleBack:
                finish();
                break;
            case R.id.changePhone_sure://确认更换
                showToast("确认更换");
                break;
        }
    }
}
