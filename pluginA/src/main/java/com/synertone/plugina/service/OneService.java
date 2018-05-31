package com.synertone.plugina.service;
import android.util.Log;

import com.synertone.plugina.BaseService;


public class OneService extends BaseService {
    int i=0;
    private String TAG="david";
    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(){
            @Override
            public void run() {
                while (true) {
                    Log.i(TAG, "run: "+(i++));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }


}
