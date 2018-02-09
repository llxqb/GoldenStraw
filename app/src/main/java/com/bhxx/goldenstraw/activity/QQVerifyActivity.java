package com.bhxx.goldenstraw.activity;
/**
 * QQ认证
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.view.listener.OnClick;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.utils.ActivityCollector;

@InjectLayer(R.layout.activity_qq_verify)
public class QQVerifyActivity extends BasicActivity {

    @InjectAll
    private Views v;

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack;
        TextView titleTitle;
    }

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        v.titleTitle.setText("QQ认证");
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;
        }
    }

}
