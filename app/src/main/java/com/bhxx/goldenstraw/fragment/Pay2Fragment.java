package com.bhxx.goldenstraw.fragment;
/**
 * 还款--已逾期
 */

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

public class Pay2Fragment extends BaseFragment {
    @InjectAll
    private Views v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pay2, container, false);
        Handler_Inject.injectFragment(this, rootView);
        return rootView;
    }

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView pay2_totalMoney, pay2_endDate, pay2_startPay,pay2_znj,pay2_fajin,pay2_totalMoney2;
        MyCircleProgress pay2_circleProgress;
    }

    @Override
    protected void init() {
        v.pay2_circleProgress.setProgress(35);
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
        }
    }
}
