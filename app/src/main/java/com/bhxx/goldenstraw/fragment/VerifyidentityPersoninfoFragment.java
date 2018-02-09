package com.bhxx.goldenstraw.fragment;
/**
 * 身份验证--个人信息
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.pc.ioc.event.EventBus;
import com.android.pc.ioc.inject.InjectAll;
import com.android.pc.ioc.inject.InjectBinder;
import com.android.pc.ioc.view.listener.OnClick;
import com.android.pc.util.Handler_Inject;
import com.bhxx.goldenstraw.R;
import com.bhxx.goldenstraw.application.App;
import com.bhxx.goldenstraw.beans.RegionInfo;
import com.bhxx.goldenstraw.db.RegionDAO;
import com.bhxx.goldenstraw.picker.OptionsPickerView;
import com.bhxx.goldenstraw.utils.LogUtils;

import java.util.ArrayList;

public class VerifyidentityPersoninfoFragment extends BaseFragment {
    @InjectAll
    private Views v;
    OptionsPickerView pvOptions;
    static ArrayList<RegionInfo> item1;
    static ArrayList<ArrayList<RegionInfo>> item2 = new ArrayList<ArrayList<RegionInfo>>();
    static ArrayList<ArrayList<ArrayList<RegionInfo>>> item3 = new ArrayList<ArrayList<ArrayList<RegionInfo>>>();

    private Handler handlerAddress = new Handler() {
        public void handleMessage(android.os.Message msg) {
            // 三级联动效果
            pvOptions.setPicker(item1, item2, item3, true);
            pvOptions.setCyclic(true, true, true);
            pvOptions.setSelectOptions(0, 0, 0);
            v.personinfo_address_Tv.setClickable(true);
        }
    };

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView personinfo_hunyin_Tv, personinfo_xueli_Tv,
                personinfo_address_Tv, personinfo_nextStep;
        EditText personinfo_qq_Et, personinfo_email_Et;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_verifyidentity_personinfo, container, false);
        Handler_Inject.injectFragment(this, rootView);
        return rootView;
    }

    @Override
    protected void init() {
        App.app.setData("VerifyIdentityState", "");
        //启动计时器 判断下一步是否为可点击状态
        handler.postDelayed(runnable, 1000); //每隔1s执行
        initCity();
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.personinfo_hunyin_Tv://婚姻状态
                showDialog3_hunyin();
                break;
            case R.id.personinfo_xueli_Tv://学历
                showDialog3_xueli();
                break;
            case R.id.personinfo_address_Tv://常住地址
                // 点击弹出选项选择器
                pvOptions.show();
                break;
            case R.id.personinfo_nextStep://下一步
                if (TextUtils.isEmpty(App.app.getData("VerifyIdentityState"))) {
                    showToast("请填写完整信息");
                    return;
                }
//                boolean flag = verifyTextisnull();
//                if (flag == false) {
//                    return;
//                }
                EventBus.getDefault().post(new Integer(1));
                break;
        }
    }

    private boolean verifyTextisnull() {
        if (TextUtils.isEmpty(v.personinfo_qq_Et.getText().toString())) {
            showToast("请填写QQ号码");
            return false;
        }
        if (TextUtils.isEmpty(v.personinfo_email_Et.getText().toString())) {
            showToast("请填写电子邮箱");
            return false;
        }
        if (TextUtils.isEmpty(v.personinfo_hunyin_Tv.getText().toString())) {
            showToast("请填写婚姻状态");
            return false;
        }
        if (TextUtils.isEmpty(v.personinfo_xueli_Tv.getText().toString())) {
            showToast("请填写学历");
            return false;
        }
        if (TextUtils.isEmpty(v.personinfo_address_Tv.getText().toString())) {
            showToast("请输入常住地址");
            return false;
        }
        return true;
    }

    private void showDialog3_hunyin() {
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
        TextView dialog_style3_title = (TextView) view.findViewById(R.id.dialog_style3_title);
        dialog_style3_title.setText("婚姻状态");

        final String[] attr = {"已婚", "未婚"};

        dialog_style3_listview.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.arrayadapter_item_text, attr));

        dialog_style3_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                v.personinfo_hunyin_Tv.setText(attr[position]);
                dialog.dismiss();
            }
        });


    }

    private void showDialog3_xueli() {
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
        TextView dialog_style3_title = (TextView) view.findViewById(R.id.dialog_style3_title);
        dialog_style3_title.setText("学历");

        final String[] attr = {"小学", "初中", "高中", "大学", "研究生", "研究生以上"};

        dialog_style3_listview.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.arrayadapter_item_text, attr));

        dialog_style3_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                v.personinfo_xueli_Tv.setText(attr[position]);
                dialog.dismiss();
            }
        });


    }


    Handler handler = new Handler();
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            // handler自带方法实现定时器
            try {
                handler.postDelayed(this, 1000);
                if (!TextUtils.isEmpty(v.personinfo_qq_Et.getText().toString()) && !TextUtils.isEmpty(v.personinfo_email_Et.getText().toString()) &&
                        !TextUtils.isEmpty(v.personinfo_hunyin_Tv.getText().toString()) && !TextUtils.isEmpty(v.personinfo_xueli_Tv.getText().toString()) &&
                        !TextUtils.isEmpty(v.personinfo_address_Tv.getText().toString())
                        ) {
                    //
                    v.personinfo_nextStep.setBackgroundResource(R.drawable.round_corner_app);
                    //记录身份认证状态 0 个人信息 ， 1 职业信息 ，2 社会关系
                    App.app.setData("VerifyIdentityState", "0");
                    //个人信息 已认证   0未认证 1已认证
                    App.app.setData("VerifyIdentityPersonInfo", "1");
                    handler.removeCallbacks(runnable);// 关闭定时器处理
                    handler = null;
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };

    private void initCity() {
        // 选项选择器
        pvOptions = new OptionsPickerView(getActivity());

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (item1 != null && item2 != null && item3 != null) {
                    handlerAddress.sendEmptyMessage(0x123);
                    return;
                }
                item1 = (ArrayList<RegionInfo>) RegionDAO.getProvencesOrCity(1);
                for (RegionInfo regionInfo : item1) {
                    item2.add((ArrayList<RegionInfo>) RegionDAO.getProvencesOrCityOnParent(regionInfo.getId()));
                }

                for (ArrayList<RegionInfo> arrayList : item2) {
                    ArrayList<ArrayList<RegionInfo>> list2 = new ArrayList<ArrayList<RegionInfo>>();
                    for (RegionInfo regionInfo : arrayList) {
                        ArrayList<RegionInfo> q = (ArrayList<RegionInfo>) RegionDAO.getProvencesOrCityOnParent(regionInfo.getId());
                        list2.add(q);
                    }
                    item3.add(list2);
                }
                handlerAddress.sendEmptyMessage(0x123);

            }
        }).start();
        // 设置选择的三级单位
        // pwOptions.setLabels("省", "市", "区");
        pvOptions.setTitle("选择城市");

        // 设置默认选中的三级项目
        // 监听确定选择按钮
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                // 返回的分别是三个级别的选中位置
                String tx = item1.get(options1).getPickerViewText() + item2.get(options1).get(option2).getPickerViewText() + item3.get(options1).get(option2).get(options3).getPickerViewText();
                v.personinfo_address_Tv.setText(tx);
            }
        });

        v.personinfo_address_Tv.setClickable(false);
    }

    @Override
    public void onDestroy() {
        if (handler != null) {
            handler.removeCallbacks(runnable);// 关闭定时器处理
        }
        super.onDestroy();
    }
}
