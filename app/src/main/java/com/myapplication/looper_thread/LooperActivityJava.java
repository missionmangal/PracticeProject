package com.myapplication.looper_thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.myapplication.R;

public class LooperActivityJava extends AppCompatActivity {

    TextView tvCount;
    Button btn;
    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            System.out.println("Get Handled Message "+msg.obj.toString()+Thread.currentThread());
            tvCount.setText(msg.obj.toString());
        }
    };
    private int count =0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper);
        tvCount = findViewById(R.id.tv_count);
        btn = findViewById(R.id.btn);
        System.out.println("Thread name :Main "+Thread.currentThread());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWorker();
            }
        });
    }

    private void startWorker() {
        LooperSimpleWorker worker = new LooperSimpleWorker("Looper Thread",tvCount);
//        SimpleWorker worker = new SimpleWorker();
        worker.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count ++;
                System.out.println("Thread name : "+count+Thread.currentThread().getName());
//                tvCount.setText(count+"");
                Message msg = Message.obtain();
                msg.obj = count;
                handler.sendMessage(msg);

            }
        }).execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count ++;
                System.out.println("Thread name : "+count +Thread.currentThread().getName());
//                tvCount.setText(count+"");
                Message msg = Message.obtain();
                msg.obj = count;
                handler.sendMessage(msg);

            }
        }).execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count ++;
                System.out.println("Thread name : "+count+Thread.currentThread().getName());
//                tvCount.setText(count+"");
                Message msg = Message.obtain();
                msg.obj = count;
                handler.sendMessage(msg);

            }
        });
    }
}
