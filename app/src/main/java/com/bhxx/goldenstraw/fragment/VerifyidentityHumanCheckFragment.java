package com.bhxx.goldenstraw.fragment;
/**
 * 身份认证 - 人工审核
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.pc.ioc.event.EventBus;
import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.view.listener.OnClick;
import com.android.pc.util.Handler_Inject;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.entity.CheckStepEntity;

public class VerifyidentityHumanCheckFragment extends BaseFragment {

    @InjectAll
    private Views v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_verifyidentity_human_check, container, false);
        Handler_Inject.injectFragment(this, rootView);
        return rootView;
    }

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView verifyIdentity_human_sfz, verifyIdentity_human_rlsb;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView verifyIdentity_human_submit;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.verifyIdentity_human_sfz://扫描身份证
                showToast("扫描身份证");
                break;
            case R.id.verifyIdentity_human_rlsb://人脸识别
                showToast("人脸识别");
                break;
            case R.id.verifyIdentity_human_submit://提交
                showToast("提交成功");
                EventBus.getDefault().post(new CheckStepEntity(CheckStepEntity.THREE));
                getActivity().finish();
                break;
        }
    }
}
