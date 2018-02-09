package com.bhxx.goldenstraw.activity;
/**
 * 消息
 */
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.inject.InjectLayer;
import com.android.pc.ioc.view.listener.OnClick;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.utils.ActivityCollector;

@InjectLayer(R.layout.activity_message)
public class MessageActivity extends BasicActivity {

    @InjectAll
    private Views v;

    private class Views{
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack;
        TextView titleTitle;
    }
    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        v.titleTitle.setText("消息");
    }

    @Override
    protected void click(View view) {
        switch (view.getId()){
            case R.id.titleBack:
                finish();
                break;
        }
    }


}
