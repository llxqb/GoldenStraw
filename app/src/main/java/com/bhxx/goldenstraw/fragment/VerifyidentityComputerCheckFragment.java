package com.bhxx.goldenstraw.fragment;
/**
 * 身份认证 - 自动审核审核
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

public class VerifyidentityComputerCheckFragment extends BaseFragment {
    @InjectAll
    private Views v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_verifyidentity_computer_check, container, false);
        Handler_Inject.injectFragment(this, rootView);
        return rootView;
    }

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView verifyIdentity_computer_sfz, verifyIdentity_computer_czzp;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView verifyIdentity_computer_submit;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.verifyIdentity_computer_sfz://手动拍身份证
                showToast("手动拍身份证");
                break;
            case R.id.verifyIdentity_computer_czzp://持证自拍
                showToast("持证自拍");
                break;
            case R.id.verifyIdentity_computer_submit://提交
                showToast("提交成功");
                EventBus.getDefault().post(new CheckStepEntity(CheckStepEntity.THREE));
                getActivity().finish();
                break;
        }
    }
}
