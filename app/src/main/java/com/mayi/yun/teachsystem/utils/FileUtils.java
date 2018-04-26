package com.mayi.yun.teachsystem.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者： wh
 * 时间：  2018/3/13
 * 名称：文件路径
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class FileUtils {

    /**
     * 上传最大图片限制
     */
    private static final int MAX_UPLOAD_PHOTO_SIZE = 300 * 1024;
    /**
     * 限制图片最大宽度进行压缩
     */
    private static final int MAX_WIDTH = 720;
    /**
     * 限制图片最大高度进行压缩
     */
    private static final int MAX_HEIGHT = 1280;
    /**
     * 统一路径
     */
    public static String getFilePath() {
        return Environment.getExternalStorageDirectory() + "/teachSys";
    }

    public static File createApkFile(Context context) {
        String path = "/temp.apk";
        File file = new File(getApkFileLoadPath(context) + path);
        return file;

    }

    /**
     * 统一apk下载路径
     */
    public static String getApkFileLoadPath(Context context) {
        return createFile(getCachePath(context, "/apk"));
    }
    /**
     * 统一照相机拍照路径
     */
    public static String getImageFileLoadPath(Context context) {

        return createFile(getCachePath(context, "/image/"));
    }
    /**
     * 获取app缓存路径
     *
     * @param context
     * @return
     */
    public static String getCachePath(Context context, String path) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            //外部存储可用
            cachePath = getFilePath() + path;
        } else {
            //外部存储不可用
            cachePath = context.getCacheDir().getPath() + path;
        }
        return cachePath;
    }
    /**
     * jpg文件名
     *
     * @return
     */
    public static String getUploadPhotoFile(Context context) {

        return getImageFileLoadPath(context.getApplicationContext()) + getTimeString() + ".jpg";
    }
    /**
     * 统一下载路径
     */
    public static String getDownLadPath(Context context, String path) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            //外部存储可用
            cachePath = Environment.getExternalStorageDirectory() + path;
        } else {
            //外部存储不可用
            cachePath = context.getCacheDir().getPath() + path;
        }
        return cachePath;
    }
    /**
     * 保存拍摄图片
     *
     * @param photoPath
     * @param bitmap
     * @return
     */
    public static boolean savePhoto(String photoPath,Bitmap bitmap) {
        if (bitmap==null){
            bitmap = BitmapFactory.decodeFile(photoPath);
        }
        if (photoPath != null) {
            FileOutputStream fos = null;
            try {
                Bitmap preBitmap = compressBitmap(compressBitmapToBytes(bitmap,MAX_UPLOAD_PHOTO_SIZE), MAX_WIDTH, MAX_HEIGHT);
                Bitmap roBm = rotationBitmap(preBitmap);
                byte[] newDatas = compressBitmapToBytes(roBm, MAX_UPLOAD_PHOTO_SIZE);
                fos = new FileOutputStream(photoPath);
                fos.write(newDatas);
                G.log("compress over ");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                G.log(e);
            } finally {
                closeCloseable(fos);
            }
        }
        return false;
    }
    /**
     * 三星手机适配
     *
     * @param bitmap
     * @return
     */
    private static Bitmap rotationBitmap(Bitmap bitmap) {
        String model = Build.MODEL;
        if (model.startsWith("SM-") || model.startsWith("GT-")) {
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                    bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            return resizedBitmap;
        } else {
            return bitmap;
        }
    }

    /**
     * 质量压缩图片
     *
     * @param bitmap
     * @param maxSize
     * @return
     */
    public static byte[] compressBitmapToBytes(Bitmap bitmap, int maxSize) {
        if (bitmap == null) return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        byte[] datas = baos.toByteArray();
        int options = 80;
        int longs = datas.length;
        while (longs > maxSize && options > 0) {
            G.log("compressBitmapToBytes " + longs + "  " + options);
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            datas = baos.toByteArray();
            longs = datas.length;
            options -= 20;
        }
        return datas;
    }
    /**
     * 关闭资源
     *
     * @param close
     */
    public static void closeCloseable(Closeable close) {
        if (close != null) {
            try {
                close.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 把字节流按照图片方式大小进行压缩
     * @param datas
     * @param w
     * @param h
     * @return
     */
    public static Bitmap compressBitmap(byte[] datas, int w, int h) {
        if (datas != null) {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds  = true;
            BitmapFactory.decodeByteArray(datas, 0, datas.length, opts);
            if (opts.outWidth != 0 && opts.outHeight != 0) {
                G.log(opts.outWidth + " " + opts.outHeight);
                int scaleX = opts.outWidth / w;
                int scaleY = opts.outHeight / h;
                int scale = 1;
                if (scaleX >= scaleY && scaleX >= 1) {
                    scale = scaleX;
                }
                if (scaleX < scaleY && scaleY >= 1) {
                    scale = scaleY;
                }
                opts.inJustDecodeBounds = false;
                opts.inSampleSize = scale;
                G.log("compressBitmap inSampleSize " + datas.length + " " + scale);
                return BitmapFactory.decodeByteArray(datas, 0, datas.length, opts);
            }
        }
        return null;
    }

    public static String getChoosePicture(Context context,Uri selectedImage ) {
        String[] filePathColumns = {MediaStore.Images.Media.DATA};
        Cursor c = context.getContentResolver().query(selectedImage, filePathColumns, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(filePathColumns[0]);
        String imagePath = c.getString(columnIndex);
        c.close();
        return imagePath;
    }
        /**
         * 判断文件夹是否存在
         */
    public static String createFile(String path) {
        File file = new File(path);
        //判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }
    public static String getTimeString() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
}
