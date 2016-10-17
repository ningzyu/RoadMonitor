package com.sxhxjy.roadmonitor.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.HttpResponse;
import com.sxhxjy.roadmonitor.base.HttpService;
import com.sxhxjy.roadmonitor.base.MyApplication;

import java.io.File;

import cz.msebera.android.httpclient.Header;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 2016/10/12
 *
 * @author Michael Zhao
 */

public class TakePictureActivity extends BaseActivity {
    ImageView picture, pictureContrast;
    private TextView display;
    private Bitmap bitmap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_picture);
        picture = (ImageView) findViewById(R.id.picture);
//        pictureContrast = (ImageView) findViewById(R.id.pictureContrast);
        display = (TextView) findViewById(R.id.display);

        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/PictureContrast/");
        if (!file.exists()) {
            if (file.mkdirs()) Log.i("image ", "picture contrast" + " created !");
        }

        picture.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                display.setText("bitmap width: " + bitmap.getWidth() + ",  height: " + bitmap.getHeight() + "\n\n"
                    +"x in bitmap:  " + event.getX() / v.getWidth() * bitmap.getWidth() + ",  y: " + event.getY() / v.getHeight() * bitmap.getHeight() );
                return true;
            }
        });
    }

    public void takePicture(View view) {
        // Check permission for CAMERA
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now
            // Callback onRequestPermissionsResult
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    100);
        } else {
            // permission has been granted, continue as usual
            Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath() + "/PictureContrast/picture.jpg")));
            startActivityForResult(intentCamera, 7);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK) {
            picture.setImageURI(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath() + "/PictureContrast/picture.jpg")));
            bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath() + "/PictureContrast/picture.jpg");
            picture.setImageBitmap(bitmap);
        }
        if (requestCode == 8 && resultCode == RESULT_OK) {
            pictureContrast.setImageURI(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath() + "/PictureContrast/picture_contrast.jpg")));
        }

    }

    public void takePictureContrast(View view) {
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Environment.getExternalStorageDirectory().getPath() + "/PictureContrast/picture_contrast.jpg");
        startActivityForResult(intentCamera, 8);
    }

    public void upload(String path, Callback<HttpResponse<String>> callback) {
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", "test.jpg", requestBody);
        Call<HttpResponse<String>> call = MyApplication.getMyApplication().getHttpService().uploadImage(part);
        call.enqueue(callback);
    }

    public void test() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(this, "", null, new FileAsyncHttpResponseHandler(new File("")) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {

            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
            }
        });
    }
}
