package com.sxhxjy.roadmonitor.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;



import java.io.*;

/**
 * Utility of Bitmap, You should work with ImageLoader.
 *
 * @author Michael Zhao
 */
public final class BitmapUtil {
    public static Bitmap thumbnail(String pathName, ImageView imageView) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        // height and width of raw image
        int height = options.outHeight;
        int width = options.outWidth;
        // height and width of imageView which displays thumbnail
        int reqHeight = imageView.getHeight();
        int reqWidth = imageView.getWidth();
        options.inSampleSize = 1;
        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while ((height / options.inSampleSize) > reqHeight
                && (width / options.inSampleSize) > reqWidth) {
            options.inSampleSize *= 2;
        }
        options.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory.decodeFile(pathName, options);
        imageView.setImageBitmap(bm);
        return bm;
    }

   /* public static String findPathByUri(String uri) {
        return ImageLoader.getInstance().getDiskCache().get(uri).getAbsolutePath();
    }*/

    public static BitmapFactory.Options getBitmapOptions() {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inPreferredConfig = Bitmap.Config.RGB_565;
        o.inSampleSize = 4;
        return o;
    }

    /*public static File getUploadedInputStream(String path) {
        File compressFile = new File(PhotoPopupWindow.getTimestampUri().getPath());
        File file = new File(path);
        if ((file.length() / 1024 > 500)) {
            Bitmap bm = BitmapFactory.decodeFile(path);
            BufferedOutputStream out = null;
            try {
                out = new BufferedOutputStream(new FileOutputStream(compressFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (bm.compress(Bitmap.CompressFormat.JPEG, (int) (100 / (file.length() / 1024 / 500)), out)) {
                Log.e("BitmapUtil", "file length: " + compressFile.length() / 1024);
                return compressFile;
            }
        } else {
            return file;
        }
        return file;
    }*/
}
