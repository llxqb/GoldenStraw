package com.bhxx.goldenstraw.fragment;
/**
 * 还款-->续期
 */

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.view.listener.OnClick;
import com.android.pc.util.Handler_Inject;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.views.MyCircleProgress;

public class XuQiFragment extends BaseFragment {
    @InjectAll
    private Views v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_xuqi, container, false);
        Handler_Inject.injectFragment(this, rootView);
        return rootView;
    }

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView xuqi_7day, xuqi_14day, xuqi_endDate, xuqi_totalMoney,
                xuqi_xifei, xuqi_fuwufei, xuqi_totalMoney_fei, xuqi_startXuQi;
    }

    @Override
    protected void init() {
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.xuqi_7day:
                v.xuqi_14day.setBackgroundResource(R.drawable.hollow_round_corner_app);
                v.xuqi_14day.setTextColor(Color.parseColor("#FF623E"));

                v.xuqi_7day.setBackgroundResource(R.drawable.round_corner_app);
                v.xuqi_7day.setTextColor(Color.parseColor("#ffffff"));

                break;
            case R.id.xuqi_14day:
                v.xuqi_7day.setBackgroundResource(R.drawable.hollow_round_corner_app);
                v.xuqi_7day.setTextColor(Color.parseColor("#FF623E"));

                v.xuqi_14day.setBackgroundResource(R.drawable.round_corner_app);
                v.xuqi_14day.setTextColor(Color.parseColor("#ffffff"));
                break;

            case R.id.xuqi_startXuQi://开始续期
                showToast("续期成功");
                break;
        }
    }
}
