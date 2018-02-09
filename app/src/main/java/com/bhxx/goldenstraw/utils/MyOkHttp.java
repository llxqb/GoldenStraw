package com.bhxx.goldenstraw.utils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.cookie.CookieJarImpl;

import java.io.File;
import java.util.Map;

import okhttp3.CookieJar;
import okhttp3.MediaType;

public class MyOkHttp {
    /**
     * post请求上传map
     *
     * @param url    请求的url
     * @param id     id标示
     * @param tag    请求标示
     * @param params 参数
     * @param back   请求返回
     */
    public static void postMap(String url, int id, Object tag, Map<String, String> params, CommonCallback back) {
        OkHttpUtils
                .post()
                .url(url)
                .params(params)
                .tag(tag)
                .id(id)
                .build()
                .execute(back);
    }

    /**
     * post请求上传map
     *
     * @param url    请求的url
     * @param params 参数
     * @param tag    请求标示
     * @param back   请求返回
     */
    public static void postMap(String url, Object tag, Map<String, String> params, CommonCallback back) {
        OkHttpUtils
                .post()
                .url(url)
                .params(params)
                .tag(tag)
                .build()
                .execute(back);
    }

    /**
     * post提交json字符串
     *
     * @param url  请求的url
     * @param id   id标示
     * @param tag  请求标示
     * @param json 参数
     * @param back 请求返回
     */
    public static void postJson(String url, Object tag, int id, String json, CommonCallback back) {
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(json)
                .tag(tag)
                .id(id)
                .build()
                .execute(back);
    }

    /**
     * post提交json字符串
     *
     * @param url  请求的url
     * @param json 参数
     * @param tag  请求标示
     * @param back 请求返回
     */
    public static void postJson(String url, Object tag, String json, CommonCallback back) {
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(json)
                .tag(tag)
                .build()
                .execute(back);
    }

    /**
     * get请求
     *
     * @param url  请求url
     * @param id   id标示
     * @param tag  请求标示
     * @param back 请求返回
     */
    public static void get(String url, Object tag, int id, CommonCallback back) {
        OkHttpUtils
                .get()
                .url(url)
                .tag(tag)
                .id(id)
                .build()
                .execute(back);
    }

    /**
     * get请求
     *
     * @param tag  请求标示
     * @param url  请求url
     * @param back 请求返回
     */
    public static void get(String url, Object tag, CommonCallback back) {
        OkHttpUtils
                .get()
                .url(url)
                .tag(tag)
                .build()
                .execute(back);
    }

    /**
     * 单个文件提交
     *
     * @param tag  请求标示
     * @param url
     * @param id
     * @param file
     * @param back
     */
    public static void postFile(String url, Object tag, int id, File file, CommonCallback back) {
        OkHttpUtils
                .postFile()
                .url(url)
                .file(file)
                .tag(tag)
                .id(id)
                .build()
                .execute(back);

    }

    /**
     * 单个文件提交
     *
     * @param tag  请求标示
     * @param url
     * @param file
     * @param back
     */
    public static void postFile(String url, Object tag, File file, CommonCallback back) {
        OkHttpUtils
                .postFile()
                .url(url)
                .file(file)
                .tag(tag)
                .build()
                .execute(back);

    }

    /**
     * post多文件携带参数上传
     *
     * @param url       请求的url
     * @param tag       请求标示
     * @param id        返回标示
     * @param params    map参数
     * @param files     多文件 （健为文件名，值为File对象）
     * @param paramName 文件参数名
     * @param back      回调
     */
    public static void multiPostFile(String url, Object tag, int id, Map<String, String> params, Map<String, File> files, String paramName, CommonCallback back) {
        OkHttpUtils.post()
                .files(paramName, files)
                .url(url)
                .params(params)
                .tag(tag)
                .id(id)
                .build()
                .execute(back);
    }

    /**
     * post多文件携带参数上传
     *
     * @param url       请求的url
     * @param tag       请求标示
     * @param params    map参数
     * @param files     多文件 （健为文件名，值为File对象）
     * @param paramName 文件参数名
     * @param back      回调
     */
    public static void multiPostFile(String url, Object tag, Map<String, String> params, Map<String, File> files, String paramName, CommonCallback back) {

        OkHttpUtils.post()
                .files(paramName, files)
                .url(url)
                .params(params)
                .tag(tag)
                .build()
                .execute(back);
    }

    /**
     * post多文件上传
     * @param url
     * @param tag
     * @param files
     * @param paramName
     * @param back
     */
    public static void multiFile(String url, Object tag, Map<String, File> files, String paramName, CommonCallback back){
        OkHttpUtils.post()
                .files(paramName, files)
                .url(url)
                .tag(tag)
                .build()
                .execute(back);
    }
    /**
     * post单文件携带参数上传
     *
     * @param url       请求的url
     * @param tag       请求标示
     * @param id        回调标示
     * @param params    map参数
     * @param file      文件
     * @param paramName 文件参数名
     * @param back      回调
     */
    public static void uploadFileParams(String url, Object tag, int id, Map<String, String> params, File file, String paramName, CommonCallback back) {
        OkHttpUtils.post()
                .addFile(paramName, FileUtils.getFileName(file.getAbsolutePath()), file)
                .url(url)
                .params(params)
                .tag(tag)
                .id(id)
                .build()
                .execute(back);
    }

    /**
     * post单文件携带参数上传
     *
     * @param url       请求的url
     * @param tag       请求标示
     * @param params    map参数
     * @param file      文件
     * @param paramName 文件参数名
     * @param back      回调
     */
    public static void uploadFileParams(String url, Object tag, Map<String, String> params, File file, String paramName, CommonCallback back) {
        OkHttpUtils.post()
                .addFile(paramName, FileUtils.getFileName(file.getAbsolutePath()), file)
                .url(url)
                .params(params)
                .tag(tag)
                .build()
                .execute(back);
    }

    /**
     * 清除cookie
     */
    public static void clearSession() {
        CookieJar cookieJar = OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
        if (cookieJar instanceof CookieJarImpl) {
            ((CookieJarImpl) cookieJar).getCookieStore().removeAll();
        }
    }

    /**
     * 下载文件
     *
     * @param url
     */
    public static void downloadFile(String url, FileCallBack back) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(back);
    }

    /**
     * 取消请求
     *
     * @param tag
     */
    public static void cancelTag(Object tag) {
        OkHttpUtils.getInstance().cancelTag(tag);
    }
}
