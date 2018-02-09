package com.bhxx.goldenstraw.activity;
/**
 * 帮助
 */

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.view.listener.OnClick;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.utils.ActivityCollector;

@InjectLayer(R.layout.activity_help)
public class HelpActivity extends BasicActivity {

    @InjectAll
    private Views v;

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView titleTitle, help_chatKF;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        LinearLayout help_cjwt, help_jkxg, help_hkxg, help_fkxg,
                help_fyxg, help_shxg, help_zhyys;

    }

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        v.titleTitle.setText("帮助");
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;
            case R.id.help_cjwt:
                showToast("常见问题");
                break;
        }
    }


}
