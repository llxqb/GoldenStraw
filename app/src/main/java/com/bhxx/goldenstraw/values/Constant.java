package com.bhxx.goldenstraw.values;

import android.os.Environment;

/**
 * TOAST常量
 * Created by liuli on 2016/10/13.
 */
public class Constant {
    public static final String ERROR_WEB = "连接超时";
    public static final String NO_DATA = "暂无数据";//listview 无数据
    public static final String FINISH = "加载完成";//成功显示
    public static final String NO_DATA_REFRESH = "无刷新数据";//刷新无数据加载


    public static final String SDPATH = Environment.getExternalStorageDirectory() + "/goldenStraw/";
    public static final String CACHE_DIR = SDPATH + "cache";

}
