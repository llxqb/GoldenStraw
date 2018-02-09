package com.bhxx.goldenstraw.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by bihua on 2016/10/31.
 */
public class IntentUtil {

    private static IntentUtil intents;

    public static IntentUtil getIntents(){
        if(intents == null)
            intents = new IntentUtil();
        return intents;
    }


    /**
     * 显示启动
     * Intent跳转  传单个参数
     */
    public static void setIntent(Context context, Class<?> cs) {
        Intent i = new Intent(context, cs);

        context.startActivity(i);
    }

}
