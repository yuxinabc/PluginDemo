package com.synertone.plugindemo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.synertone.plugindemo.utils.PluginManager;
import com.synertone.pluginstandard.PluginInterfaceActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class ProxyActivity extends AppCompatActivity {
    //需要加载的类名
   private String className;
    private PluginInterfaceActivity interfaceActivity;
    private ProxyBroadCastReceiver proxyBroadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        className=getIntent().getStringExtra("className");
        try {
            Class<?> activiyClass = getClassLoader().loadClass(className);
            Constructor<?> constructor = activiyClass.getConstructor(new Class[]{});
            Object instance = constructor.newInstance(new Object[]{});
            interfaceActivity= (PluginInterfaceActivity) instance;
            interfaceActivity.attach(this);
            Bundle bundle=new Bundle();
            interfaceActivity.onCreate(bundle);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return  PluginManager.getInstance().getResources();
    }
    @Override
    protected void onStart() {
        super.onStart();
        interfaceActivity.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        interfaceActivity.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        interfaceActivity.onPause();
    }

    @Override
    public void startActivity(Intent intent) {
        String className1=intent.getStringExtra("className");
        Intent intent1 = new Intent(this, ProxyActivity.class);
        intent1.putExtra("className", className1);
        super.startActivity(intent1);
    }
    @Override
    public ComponentName startService(Intent service) {
        String serviceName = service.getStringExtra("serviceName");
        Intent intent1 = new Intent(this, ProxyService.class);
        intent1.putExtra("serviceName", serviceName);
        return super.startService(intent1);
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        IntentFilter newIntentFilter=new IntentFilter();
        for (int i=0;i<filter.countActions();i++) {
            newIntentFilter.addAction(filter.getAction(i));
        }
        proxyBroadCast=new ProxyBroadCastReceiver(receiver,this);//new ProxyBroadCastReceiver(receiver.getClass().getName(),this);
        return super.registerReceiver(proxyBroadCast,newIntentFilter);
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        if(proxyBroadCast!=null){
            super.unregisterReceiver(proxyBroadCast);
        }
    }
}
