package com.bhxx.goldenstraw.fragment;
/**
 * 还款--未逾期
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.pc.ioc.event.EventBus;
import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.view.listener.OnClick;
import com.android.pc.util.Handler_Inject;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.activity.PayWayBankActivity;
import com.bhxx.goldenstraw.entity.CheckStepEntity;
import com.bhxx.goldenstraw.utils.IntentUtil;
import com.bhxx.goldenstraw.views.MyCircleProgress;

public class PayFragment extends BaseFragment {
    @InjectAll
    private Views v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pay, container, false);
        Handler_Inject.injectFragment(this, rootView);
        return rootView;
    }

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView pay_totalMoney, pay_endDate, pay_startPay;
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        ImageView pay_tishiDetail;
        MyCircleProgress pay_circleProgress;
    }

    @Override
    protected void init() {
        v.pay_circleProgress.setProgress(35);
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.pay_tishiDetail://还款金额总数详情
                showDialog5();
                break;
            case R.id.pay_startPay://去还款
                showDialog6();
                break;
        }
    }

    /**
     * 还款金额总数详情
     */
    private void showDialog5() {
        // 获取Dialog布局
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_style5_2, null);
        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(4 * display.getWidth() / 5);

        // 定义Dialog布局和参数
        final Dialog dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        dialog.show();

        // 获取自定义Dialog布局中的控件
        TextView dialogStyle5_2_isIKnow = (TextView) view.findViewById(R.id.dialogStyle5_2_isIKnow);

        dialogStyle5_2_isIKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 去还款
     */
    private void showDialog6() {
        // 获取Dialog布局
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_style6, null);
        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(4 * display.getWidth() / 5);

        // 定义Dialog布局和参数
        final Dialog dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        dialog.show();

        // 获取自定义Dialog布局中的控件
        TextView dialog_style6_cancal = (TextView) view.findViewById(R.id.dialog_style6_cancal);
        TextView dialog_style6_sure = (TextView) view.findViewById(R.id.dialog_style6_sure);
        RelativeLayout dialog_style6_payWayLayout = (RelativeLayout) view.findViewById(R.id.dialog_style6_payWayLayout);//还款方式

        dialog_style6_cancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog_style6_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog_style6_payWayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showDialog3();
            }
        });
    }

    /**
     * 还款方式
     */
    private void showDialog3() {
        // 获取Dialog布局
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_style3, null);
        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(4 * display.getWidth() / 5);

        // 定义Dialog布局和参数
        final Dialog dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        dialog.show();

        // 获取自定义Dialog布局中的控件
        ListView dialog_style3_listview = (ListView) view.findViewById(R.id.dialog_style3_listview);

        String[] attr = {"建设银行", "支付宝", "微信", "新银行卡"};

        dialog_style3_listview.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.arrayadapter_item_text, attr));

        dialog_style3_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        dialog.dismiss();
                        IntentUtil.setIntent(getActivity(), PayWayBankActivity.class);
                        break;
                    case 1:
                        showDialog_zfb();
                        break;
                }
            }
        });
    }

    /**
     * 支付宝方式
     */
    private void showDialog_zfb() {
        // 获取Dialog布局
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_style5_zfb, null);
        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(4 * display.getWidth() / 5);

        // 定义Dialog布局和参数
        final Dialog dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        dialog.show();

        // 获取自定义Dialog布局中的控件
        TextView dialogStyle5_zfb_isIKnow = (TextView) view.findViewById(R.id.dialogStyle5_zfb_isIKnow);
        dialogStyle5_zfb_isIKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }
}
