package com.bhxx.goldenstraw.utils;
/**
 * 上传图片压缩使用
 */

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageCompress {

	
	public static Bitmap compress(String imagePath, int maxWidth, int maxHeight){
		
		if(TextUtils.isEmpty(imagePath)){
			return null;
		}
		
		File rawFile = new File(imagePath);
		if(!rawFile.exists()){
			return null;
		}
		
		Options opts = new Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(imagePath, opts);
		opts.inSampleSize = caculateInSampleSize(opts.outWidth, opts.outHeight, maxWidth, maxHeight);
		opts.inJustDecodeBounds = false;
		
		return BitmapFactory.decodeFile(imagePath, opts);
	}
	
	public static Bitmap compress(Bitmap sourceBitmap, int maxBytes, FileType fileType){
		
		if(fileType == FileType.JPEG){
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			sourceBitmap.compress(CompressFormat.JPEG, 100, baos);
			int quality = 90;
			while(baos.toByteArray().length > maxBytes){
				
				baos.reset();
				sourceBitmap.compress(CompressFormat.JPEG, quality, baos);
				
				if(quality > 50){
					quality -= 10;
				}else if(quality > 30){
					quality -= 5;
				}else{
					break;
				}
			}
			ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
	        return BitmapFactory.decodeStream(isBm, null, null);
		}else if(fileType == FileType.PNG){
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			sourceBitmap.compress(CompressFormat.PNG, 100, baos);
			int quality = 90;
			while(baos.toByteArray().length > maxBytes){
				
				baos.reset();
				sourceBitmap.compress(CompressFormat.PNG, quality, baos);
				
				if(quality > 50){
					quality -= 10;
				}else if(quality > 30){
					quality -= 5;
				}else{
					break;
				}
			}
			ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
	        return BitmapFactory.decodeStream(isBm, null, null);
		}else{
			
			return sourceBitmap;
		}
	}
	
	public static void compress(Bitmap sourceBitmap, int maxBytes, FileType fileType, String savePath){
		
		try {
			Bitmap bitmap = compress(sourceBitmap, maxBytes, fileType);
			
			FileOutputStream fos = new FileOutputStream(new File(savePath));
			
			if(fileType == FileType.PNG){
				bitmap.compress(CompressFormat.PNG, 100, fos);
			}else if(fileType == FileType.JPEG){
				bitmap.compress(CompressFormat.JPEG, 100, fos);
			}else{
				
				bitmap.compress(CompressFormat.JPEG, 100, fos);
			}
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param maxWidth
	 * @param maxHeight
	 * @return
	 */
	private static int caculateInSampleSize(int rawWidth,int rawHeight,int maxWidth, int maxHeight){
		
		if(rawWidth <= 0 || rawHeight <= 0){
			throw new IllegalArgumentException();
		}
		
		int inSamleSize = 1;
		if(maxWidth <= 0 && maxHeight <=0){
			inSamleSize = 1;
		}else{
			
			if(maxWidth <= 0 && maxHeight < rawHeight){
				
				inSamleSize = rawHeight/maxHeight;
			}
			
			if(maxHeight <= 0 && maxWidth < rawWidth){
				
				inSamleSize = rawWidth/maxWidth;
			}
			
			if(maxWidth > 0 && maxHeight > 0 && (maxWidth < rawWidth || maxHeight < rawHeight)){
				
				float widthRatio = (float)rawWidth/maxWidth;
				float heightRatio = (float)rawHeight/maxHeight;
			
				if(widthRatio > heightRatio){
					
					inSamleSize = rawWidth/maxWidth;
				}else{
					
					inSamleSize = rawHeight/maxHeight;
				}
			}
		}
		return inSamleSize;
	}
	
	/**
	 * @param bitmap
	 * @param degress
	 * @return
	 */
	public static Bitmap rotateBitmap(Bitmap bitmap, int degress) {
        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(degress); 
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), m, true);
            return bitmap;
        }
        return null;
    }
	
	/**
	 * @param path
	 * @return
	 */
	public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                degree = 90;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                degree = 180;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                degree = 270;
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }
}
