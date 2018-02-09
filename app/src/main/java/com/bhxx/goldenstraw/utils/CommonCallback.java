package com.bhxx.goldenstraw.utils;
/***
 * MyOkHttp回调
 */

import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

public abstract class CommonCallback extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {
        return response.body().string();
    }

}
