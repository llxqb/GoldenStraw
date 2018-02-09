package com.bhxx.goldenstraw.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图片处理的工具类
 *
 * @author Lih
 *
 */
public class PictureManageUtil {

	/**
	 * 设置图片大小
	 *
	 * @param bm
	 *            ：原图
	 * @param newWidth
	 *            ：最终希望得到的宽
	 * @param newHeight
	 *            ：最终希望得到的高
	 */
	public static Bitmap resizeBitmap(Bitmap bm, int newWidth, int newHeight) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		// 计算缩放比例
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		float scale = (scaleWidth <= scaleHeight) ? scaleWidth : scaleHeight;
		matrix.postScale(scale, scale);
		// 设置大小
		return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
	}

	/**
	 * 带旋转的，缩放图片，会按照 宽高缩放比 较小的值进行缩放
	 *
	 * @param bm
	 * @param newWidth
	 * @param newHeight
	 * @param rotate
	 *            旋转参数
	 * @return
	 */
	public static Bitmap resizeBitmap(Bitmap bm, int newWidth, int newHeight,
									  int rotate) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		// 计算缩放比例
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		float scale = (scaleWidth <= scaleHeight) ? scaleWidth : scaleHeight;
		matrix.postScale(scale, scale);
		matrix.postRotate(rotate);
		// 设置大小
		return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
	}

	/**
	 * 获取旋转参数
	 *
	 * @param imagePath
	 * @return
	 */
	public static int getCameraPhotoOrientation(String imagePath) {
		int rotate = 0;
		try {
			File imageFile = new File(imagePath);
			ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
			int orientation = exif.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);

			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_270:
					rotate = 270;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					rotate = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_90:
					rotate = 90;
					break;
			}

			// Log.v(TAG, "Exif orientation: " + orientation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rotate;
	}

	/**
	 * 将图片按照宽高中比较小的裁剪成正方形
	 *
	 * @param bm
	 * @return
	 */
	public static Bitmap cropBitmap(Bitmap bm) {
		int height = bm.getHeight();
		int width = bm.getWidth();
		if (height > width) {
			return Bitmap.createBitmap(bm, 0, (height - width) / 4, width,
					width);
		} else {
			return Bitmap.createBitmap(bm, (width - height) / 2, 0, height,
					height);
		}
	}


	/**
	 * 将指定路径的图片根据指定宽度缩放(支持大图片)
	 *
	 * @param filePath
	 * @param width
	 * @return
	 */
	public static Bitmap getMicroImage(String filePath, int width) {
		// 获取文件输入流
		InputStream is = null;
		Bitmap bitmap = null;
		try {
			File f = new File(filePath);
			is = new FileInputStream(f);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			// 获取调整后的options
			BitmapFactory.decodeFile(filePath, options);
			int height = options.outHeight * width / options.outWidth;
			if (is != null) {
				int isSize = is.available();
				// 大于1M=1048576字节，则视为大图
				int base = 309600;
				if (isSize > base) {
					options.inSampleSize = 10; // width，hight设为原来的十分一
				} else if (isSize <= 409600 && isSize > 104800) {
					options.inSampleSize = 4;
				} else if (isSize <= 104800 && isSize > 60) {
					options.inSampleSize = 2;
				} else {
					options.inSampleSize = 1;
				}
			}
			options.inJustDecodeBounds = false;
			bitmap = BitmapFactory.decodeStream(is, null, options);
			int rotate = PictureManageUtil.getCameraPhotoOrientation(filePath);
			bitmap = PictureManageUtil.resizeBitmap(bitmap, width, height,
					rotate);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bitmap;
	}

	/**
	 * 根据路径，不失真压缩
	 *
	 * @param filePath
	 * @return
	 */
	public static Bitmap getCompressBm(String filePath) {
		Bitmap bm = null;
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, 500, 500);
		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		bm = BitmapFactory.decodeFile(filePath, options);
		return bm;
	}

	/**
	 * 计算压缩比例
	 *
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	private static int calculateInSampleSize(BitmapFactory.Options options,
											 int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? widthRatio : heightRatio;
		}

		return inSampleSize;
	}

	/**
	 * 旋转图片
	 *
	 * @param bitmap
	 * @param rotate
	 * @return
	 */
	public static Bitmap rotateBitmap(Bitmap bitmap, int rotate) {
		if (bitmap == null)
			return null;

		int w = bitmap.getWidth();
		int h = bitmap.getHeight();

		// Setting post rotate to 90
		Matrix mtx = new Matrix();
		mtx.postRotate(rotate);
		return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
	}

}
