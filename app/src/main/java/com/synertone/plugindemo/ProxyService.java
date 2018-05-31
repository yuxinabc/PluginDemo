package com.synertone.plugindemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import com.synertone.plugindemo.utils.PluginManager;
import com.synertone.pluginstandard.PluginInterfaceActivity;
import com.synertone.pluginstandard.PluginInterfaceService;

import java.lang.reflect.Constructor;

public class ProxyService extends Service {
    private PluginInterfaceService interfaceService;
    private String serviceName;

    public ProxyService() {
    }



    @Override
    public IBinder onBind(Intent intent) {
        init(intent);
       return null;
    }

    private void init(Intent intent) {
        serviceName = intent.getStringExtra("serviceName");
//        class
        try {
            Class loadClass= PluginManager.getInstance().getDexClassLoader().loadClass(serviceName);

            Constructor<?> localConstructor =loadClass.getConstructor(new Class[] {});
            Object instance = localConstructor.newInstance(new Object[] {});
//            OneService
            interfaceService = (PluginInterfaceService) instance;
            interfaceService.attach(this);
            interfaceService.onCreate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (interfaceService == null) {
            if(intent!=null){
                init(intent);
            }
        }
        if(interfaceService==null){
           return START_STICKY;
        }
        return interfaceService.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        interfaceService.onDestroy();
        super.onDestroy();

    }

    @Override
    public void onLowMemory() {
        interfaceService.onLowMemory();
        super.onLowMemory();
    }


    @Override
    public boolean onUnbind(Intent intent) {
        interfaceService.onUnbind(intent);
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        interfaceService.onRebind(intent);
        super.onRebind(intent);
    }
}
