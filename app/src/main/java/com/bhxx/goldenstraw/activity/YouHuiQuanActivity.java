package com.bhxx.goldenstraw.activity;
/**
 * 优惠券
 */
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.utils.ActivityCollector;

public class YouHuiQuanActivity extends BasicActivity implements View.OnClickListener {

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
        setContentView(R.layout.activity_you_hui_quan);
        initView();
    }

    private void initView() {
        titleBack = (ImageView) findViewById(R.id.titleBack);
        titleBack.setOnClickListener(this);
        titleTitle = (TextView) findViewById(R.id.titleTitle);
        titleTitle.setText("优惠券");
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
