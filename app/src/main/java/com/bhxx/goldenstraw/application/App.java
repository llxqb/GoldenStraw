package com.bhxx.goldenstraw.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap.Config;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.util.Handler_SharedPreferences;
import com.bhxx.goldenstraw.db.DBManager;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

public class App extends Application {
    public static App app;
    private DBManager dbHelper;

    @Override
    public void onCreate() {
        app = this;
        Ioc.getIoc().init(this);
        super.onCreate();
        initImageLoader(getApplicationContext());

        // 程序启动，完成初始化操作
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);

        //导入省市数据库
        dbHelper = new DBManager(this);
        dbHelper.openDatabase();

        // 异常捕捉
//        new CrashUtils().init();

    }

    private void initImageLoader(Context context) {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true)
                .bitmapConfig(Config.RGB_565).considerExifParams(true).build();

        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "goldenStraw/cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(defaultOptions)
                //
                .memoryCacheExtraOptions(720, 1080)
                // 缓存图片的大小
                .threadPoolSize(3)
                // 线程池数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                // 线程的优先等级
                .denyCacheImageMultipleSizesInMemory()
                // 缓存图片在内存
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                // 内存缓存大小
                .memoryCacheSize(2 * 1024 * 1024)
                // 内存缓存最大值
                .diskCacheSize(60 * 1024 * 1024)
                // SD卡缓存最大值
                .tasksProcessingOrder(QueueProcessingType.LIFO).diskCache(new UnlimitedDiscCache(cacheDir))
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 3000)).writeDebugLogs().build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 数据存储到本地数据库
     *
     * @param key
     * @param value
     * @return void
     */
    public void setData(String key, String value) {
        Handler_SharedPreferences.WriteSharedPreferences("Cache", key, value);
    }

    /**
     * 取出本地数据
     *
     * @param key
     * @return String
     */
    public String getData(String key) {
        return Handler_SharedPreferences.getValueByName("Cache", key, Handler_SharedPreferences.STRING).toString();
    }

    /**
     * 存储boolean类型
     *
     * @param key
     * @param value
     */
    public void setBooleanData(String key, Boolean value) {
        Handler_SharedPreferences.WriteSharedPreferences("Cache", key, value);
    }

    /**
     * 读取boolean类型
     *
     * @param key
     * @return
     */
    public Boolean getBooleanData(String key) {
        return (Boolean) Handler_SharedPreferences.getValueByName("Cache", key, Handler_SharedPreferences.BOOLEAN);
    }


    /**
     * 删除一条本地数据
     *
     * @param key
     * @return String
     */
    public void deleteData(String key) {
        Handler_SharedPreferences.removeSharedPreferences("Cache", key);
    }

    /**
     * 写入对象
     *
     * @param key
     * @param o
     */
    public void saveObjData(String key, Object o) {
        Handler_SharedPreferences.saveObject("Cache", key, o);
    }

    /**
     * 读取对象
     *
     * @param key
     * @param o
     */
    public Object readObjData(String key) {
        return Handler_SharedPreferences.readObject("Cache", key);
    }

    /**
     * 删除本地数据文件
     *
     * @param key
     * @return String
     */
    public void clearDatabase(String dataBaseName) {
        Handler_SharedPreferences.ClearSharedPreferences(dataBaseName);
    }

}
