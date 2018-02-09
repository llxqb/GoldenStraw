package com.bhxx.goldenstraw.fragment;
/**
 * 身份验证--职业信息
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ImageView;
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

public class VerifyidentityProfessioninfoFragment extends BaseFragment {
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
            v.professioninfo_city_Tv.setClickable(true);
        }
    };

    private class Views {
        @InjectBinder(listeners = {OnClick.class}, method = "click")
        TextView professioninfo_zhiye_Tv, professioninfo_shouru_Tv, professioninfo_city_Tv, professioninfo_nextStep;
        EditText professioninfo_danwei_Et, professioninfo_danweiAddress_Et, professioninfo_danweiPhone_Et;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_verifyidentity_professioninfo, container, false);
        Handler_Inject.injectFragment(this, rootView);
        return rootView;
    }

    @Override
    protected void init() {
        //启动计时器 判断下一步是否为可点击状态
        handler.postDelayed(runnable, 1000); //每隔1s执行
        initCity();
    }

    @Override
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.professioninfo_zhiye_Tv://职业
                showDialog3_zhiye();
                break;
            case R.id.professioninfo_shouru_Tv://收入区间
                showDialog3_shouru();
                break;
            case R.id.professioninfo_city_Tv://省市
                // 点击弹出选项选择器
                pvOptions.show();
                break;
            case R.id.professioninfo_nextStep:
                if (TextUtils.isEmpty(App.app.getData("VerifyIdentityPersonInfo"))) {
                    showToast("请先认证个人信息");
                    return;
                }
                if (!App.app.getData("VerifyIdentityState").equals("2") && !App.app.getData("VerifyIdentityState").equals("3")) {
                    showToast("请填写完整信息");
                    return;
                }
                EventBus.getDefault().post(new Integer(2));
                break;
        }
    }


    private void showDialog3_zhiye() {
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
        dialog_style3_title.setText("职业");

        final String[] attr = {"程序猿", "设计师", "教师", "服务员", "司机", "厨师", "其他"};

        dialog_style3_listview.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.arrayadapter_item_text, attr));

        dialog_style3_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast(attr[position]);
                dialog.dismiss();
                v.professioninfo_zhiye_Tv.setText(attr[position]);
            }
        });


    }

    private void showDialog3_shouru() {
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
        dialog_style3_title.setText("月薪");

        final String[] attr = {"小于1000元", "1000-2000元", "2000-4000元", "4000-6000元", "6000-10000元", "10000元以上"};

        dialog_style3_listview.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.arrayadapter_item_text, attr));

        dialog_style3_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast(attr[position]);
                dialog.dismiss();
                v.professioninfo_shouru_Tv.setText(attr[position]);
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
                if (!TextUtils.isEmpty(v.professioninfo_zhiye_Tv.getText().toString()) && !TextUtils.isEmpty(v.professioninfo_shouru_Tv.getText().toString()) &&
                        !TextUtils.isEmpty(v.professioninfo_danwei_Et.getText().toString()) && !TextUtils.isEmpty(v.professioninfo_city_Tv.getText().toString()) &&
                        !TextUtils.isEmpty(v.professioninfo_danwei_Et.getText().toString()) && !TextUtils.isEmpty(v.professioninfo_danweiPhone_Et.getText().toString())) {
                    if (TextUtils.isEmpty(App.app.getData("VerifyIdentityPersonInfo"))) {
                        return;
                    }
                    v.professioninfo_nextStep.setBackgroundResource(R.drawable.round_corner_app);
                    //记录身份认证状态 0 个人信息 ， 1 职业信息 ，2 社会关系
                    App.app.setData("VerifyIdentityState", "2");
                    //职业信息 已认证   0未认证 1已认证
                    App.app.setData("VerifyIdentityProfessionInfo", "1");

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
                v.professioninfo_city_Tv.setText(tx);
            }
        });

        v.professioninfo_city_Tv.setClickable(false);
    }

    @Override
    public void onDestroy() {
        if (handler != null) {
            handler.removeCallbacks(runnable);// 关闭定时器处理
        }
        super.onDestroy();
    }
}
