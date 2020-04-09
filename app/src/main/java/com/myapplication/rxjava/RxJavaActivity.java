package com.myapplication.rxjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplication.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {

    Button btnGetdata;
    TextView tvData;
    ImageView img;
    ProgressBar prg;
    int count =0;
    Disposable disposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        btnGetdata = findViewById(R.id.btn_get_data);
        img = findViewById(R.id.img);
        prg = findViewById(R.id.prg);
        tvData = findViewById(R.id.tv_data);
        btnGetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                String imgUrl = "https://wallpapercave.com/wp/wp2601438.jpg";
//                getBitmapFromURL(imgUrl);
                getData();
            }
        });
    }
    ArrayList list;
    private void getData() {
        list =new ArrayList<Bitmap>();
        list.add("Tiger");
        list.add("Lion");
        list.add("Elephant");
        prg.setVisibility(View.VISIBLE);
        getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                       disposable = d;
                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        updateView(bitmap);
                        prg.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxJavaActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        prg.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private Observable getObservable(){
//        final Observable<ArrayList> observable =null;
        String imgUrl = "https://wallpapercave.com/wp/wp2601438.jpg";
        return Observable.just(getBitmapFromURL(imgUrl));

    }
    private void updateView(final Bitmap bitmap) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                img.setImageBitmap(bitmap);
            }
        },3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
