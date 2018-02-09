package com.bhxx.goldenstraw.activity;
/**
 * 借款协议
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

import org.w3c.dom.Text;

@InjectLayer(R.layout.activity_ti_shi2)
public class TiShi2Activity extends BasicActivity {

    @InjectAll
    private Views v;

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView titleBack, titleRightImg;
        TextView titleTitle;

    }

    @Override
    protected void init() {
        ActivityCollector.addActivity(this);
        v.titleRightImg.setVisibility(View.VISIBLE);
        v.titleRightImg.setImageResource(R.mipmap.icon_meanu);
        v.titleTitle.setText("借款协议");
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
