package com.synertone.plugina;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.synertone.pluginstandard.PluginInterfaceService;

public class BaseService extends Service implements PluginInterfaceService{
    protected Service that;
    private static final String TAG = "david";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void attach(Service proxyService) {
         that=proxyService;
    }
    @Override
    public void onCreate() {
        if(that==null){
            super.onCreate();
        }
        // TODO Auto-generated method stub
        Log.d(TAG, TAG + " onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Log.d(TAG, TAG + " onStartCommand");
        if(that==null){
          return   super.onStartCommand(intent,flags,startId);
        }else{
             return Service.START_STICKY;
        }
    }

    @Override
    public void onDestroy() {
        if(that==null){
            super.onDestroy();
        }
        Log.d(TAG, TAG + " onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        if(that==null){
            super.onConfigurationChanged(newConfig);
        }
        Log.d(TAG, TAG + " onConfigurationChanged");
    }

    @Override
    public void onLowMemory() {
        // TODO Auto-generated method stub
        if(that==null){
            super.onLowMemory();
        }
        Log.d(TAG, TAG + " onLowMemory");
    }

    @Override
    public void onTrimMemory(int level) {
        // TODO Auto-generated method stub
        if(that==null){
            super.onTrimMemory(level);
        }
        Log.d(TAG, TAG + " onTrimMemory");

    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        Log.d(TAG, TAG + " onUnbind");
        if(that==null){
          return   super.onUnbind(intent);
        }
        return false;
    }

    @Override
    public void onRebind(Intent intent) {
        // TODO Auto-generated method stub
        if(that==null){
             super.onRebind(intent);
        }
        Log.d(TAG, TAG + " onRebind");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        // TODO Auto-generated method stub
        if(that==null){
            super.onTaskRemoved(rootIntent);
        }
        Log.d(TAG, TAG + " onTaskRemoved");
    }
}
