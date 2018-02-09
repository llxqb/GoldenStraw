package com.bhxx.goldenstraw.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.bhxx.goldenstraw.values.Constant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class FileUtils {
    static {
        File file = new File(Constant.SDPATH);
        if (!file.exists()) {
            file.mkdirs();
        }

        file = new File(Constant.CACHE_DIR);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * 截取文件名
     *
     * @param pathandname
     * @return
     */
    public static String getFileName(String pathandname) {

        int start = pathandname.lastIndexOf("/");
        if (start != -1) {
            return pathandname.substring(start + 1);
        } else {
            return null;
        }
    }

    public static void saveBitmap(Bitmap bm, String picName) {
        Log.e("", "保存图片");
        try {
            if (!isFileExist("")) {
                File tempf = createSDDir("");
            }
            File f = new File(Constant.SDPATH, picName + ".JPEG");
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Log.e("", "已经保存");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File createSDDir(String dirName) throws IOException {
        File dir = new File(Constant.SDPATH + dirName);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            System.out.println("createSDDir:" + dir.getAbsolutePath());
            System.out.println("createSDDir:" + dir.mkdir());
        }
        return dir;
    }

    public static boolean isFileExist(String fileName) {
        File file = new File(Constant.SDPATH + fileName);
        file.isFile();
        return file.exists();
    }

    public static void delFile(String fileName) {
        File file = new File(Constant.SDPATH + fileName);
        if (file.isFile()) {
            file.delete();
        }
        file.exists();
    }

    public static void deleteDir(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isDirectory())
            return;

        if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {

            File[] childFiles = file.listFiles();
            for (int i = 0; i < childFiles.length; i++) {
                deleteDir(childFiles[i].getAbsolutePath());
            }
        } else {
            return;
        }
    }

    public static boolean fileIsExists(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {

            return false;
        }
        return true;
    }

    public static String getImageSavePath() {

        File checkers = new File(Environment.getExternalStorageDirectory().toString() + "/xsfamily");
        if (!checkers.exists()) {
            checkers.mkdirs();
        }

        return checkers + "/download_image" + new Date().getTime() + ".jpg";
    }

    public static boolean copyFile(File rawFile, File destFile) {

        if (rawFile == null || !rawFile.exists()) {
            return false;
        }

        try {
            InputStream inputStream = new FileInputStream(rawFile);
            FileOutputStream fos = new FileOutputStream(destFile);

            int len = 0;
            byte[] buffer = new byte[1024];

            while ((len = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

            inputStream.close();
            fos.flush();
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 获取上传文件（压缩）
     * 图片路径转换为文件
     *
     * @param imagePath
     * @return
     */
    public static File getUploadImageFile(String imagePath) {

        // 获取图片类型
        FileType fileType = FileTypeJudge.getType(imagePath);

        // 先压缩大小
        Bitmap bitmap = ImageCompress.compress(imagePath, 1024, 1024);

        // 创建缓存文件，使用完可以删除
        File cacheFile = new File(Constant.CACHE_DIR, System.currentTimeMillis() + "");

        // 压缩为最大500KB
        ImageCompress.compress(bitmap, 500 * 1024, fileType, cacheFile.getAbsolutePath());

        if (!cacheFile.exists()) {
            return new File(imagePath);
        }

        return cacheFile;
    }
}
