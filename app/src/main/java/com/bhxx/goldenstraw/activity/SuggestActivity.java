package com.bhxx.goldenstraw.activity;
/**
 * 意见反馈
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.utils.ActivityCollector;

public class SuggestActivity extends BasicActivity implements View.OnClickListener {
    private ImageView titleBack;
    private TextView titleTitle, suggest_sure;

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
        setContentView(R.layout.activity_suggest);
        initView();
    }

    private void initView() {
        titleBack = (ImageView) findViewById(R.id.titleBack);
        titleBack.setOnClickListener(this);
        titleTitle = (TextView) findViewById(R.id.titleTitle);
        titleTitle.setText("意见反馈");
        suggest_sure = (TextView) findViewById(R.id.suggest_sure);
        suggest_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleBack:
                finish();
                break;
            case R.id.suggest_sure:
                showToast("提交成功");
                break;
        }
    }
}
