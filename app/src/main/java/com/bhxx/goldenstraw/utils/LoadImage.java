package com.bhxx.goldenstraw.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bhxx.goldenstraw.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImage {

    static Bitmap bitmap = null;

    /**
     * 从服务器读取图片转化为Bitmap
     *
     * @param urlString
     * @return
     */
    public static Bitmap getBitmapFromUrl(String urlString) {

        try {
            // 将字符串转换为URL路径
            URL url = new URL(urlString);
            // 使用url.openConnection()获得连接对象
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 获得输入流，读取信息
            BufferedInputStream is = new BufferedInputStream(connection.getInputStream());
            // 将输入流装换为Bitmap对象
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置图片未加载或正在加载时显示的默认图片
     *
     * @return
     */
    public static DisplayImageOptions getHeadImgOptions() {
        /** 设置图片加载的属�?/ */
        Builder b = new Builder();
        /** 图片为空时，设置图片 */
        b.showImageForEmptyUri(R.mipmap.default_avatar);
        /** 查不到图片时，设置图�? */
        b.showImageOnFail(R.mipmap.default_avatar);
        /** 设置图片在下载期间显示的图片 */
        b.showImageOnLoading(R.mipmap.default_avatar);
        /** 设置图片在下载前是否重置，复�? */
        b.resetViewBeforeLoading(Boolean.TRUE);
        /** 设置下载的图片是否缓存在SD卡中 */
        b.cacheOnDisk(Boolean.FALSE);
        /** 设置下载的图片是否缓存在内存�? */
        b.cacheInMemory(Boolean.FALSE);
        /** 设置图片以如何的编码方式显示 */
        b.imageScaleType(ImageScaleType.EXACTLY_STRETCHED);
        /** 返回�?个DisplayImageOptions对象 */
        return b.bitmapConfig(Bitmap.Config.RGB_565).build();
    }

    /**
     * 设置图片未加载或正在加载时显示的默认图片
     *
     * @return
     */
    public static DisplayImageOptions getDefaultOptions() {
        /** 设置图片加载的属�?/ */
        Builder b = new Builder();
        /** 图片为空时，设置图片 */
		b.showImageForEmptyUri(R.mipmap.default_photo);
		/** 查不到图片时，设置图�? */
		b.showImageOnFail(R.mipmap.default_photo);
		/** 设置图片在下载期间显示的图片 */
		b.showImageOnLoading(R.mipmap.default_photo);
        /** 设置图片在下载前是否重置，复�? */
        b.resetViewBeforeLoading(Boolean.TRUE);
        /** 设置下载的图片是否缓存在SD卡中 */
        b.cacheOnDisk(Boolean.TRUE);
        /** 设置下载的图片是否缓存在内存�? */
        b.cacheInMemory(Boolean.TRUE);
//        // 圆角
//        b.displayer(new RoundedBitmapDisplayer(320));
        /** 设置图片以如何的编码方式显示 */
        b.imageScaleType(ImageScaleType.EXACTLY_STRETCHED);
        /** 返回�?个DisplayImageOptions对象 */
        return b.bitmapConfig(Bitmap.Config.RGB_565).build();
    }
}
