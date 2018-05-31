package com.synertone.plugina;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.synertone.pluginstandard.PluginInterfaceActivity;

public class BaseActivity extends AppCompatActivity implements PluginInterfaceActivity {
    protected Activity that;

    @Override
    public void attach(Activity proxyActivity) {
            that = proxyActivity;
    }

    @Override
    public void setContentView(int layoutResID) {
        if(that!=null){
            that.setContentView(layoutResID);
        }else{
            super.setContentView(layoutResID);
        }
    }

    @Override
    public void setContentView(View view) {
        if(that!=null){
            that.setContentView(view);
        }else{
            super.setContentView(view);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if(that!=null){
            that.setContentView(view,params);
        }else{
            super.setContentView(view,params);
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if(that!=null){
           return that.findViewById(id);
        }else{
           return super.findViewById(id);
        }
    }

    @Override
    public Intent getIntent() {
        if(that!=null){
            return that.getIntent();
        }else{
            return super.getIntent();
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        if(that!=null){
            return that.getClassLoader();
        }else{
            return super.getClassLoader();
        }
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        if(that!=null){
            return that.getLayoutInflater();
        }else{
            return super.getLayoutInflater();
        }
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        if(that!=null){
            return that.getApplicationInfo();
        }else{
            return super.getApplicationInfo();
        }
    }

    @Override
    public Window getWindow() {
        if(that!=null){
            return that.getWindow();
        }else{
            return super.getWindow();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if(that!=null){
            return that.getWindowManager();
        }else{
            return super.getWindowManager();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
      if(that==null){
          super.onCreate(savedInstanceState);
      }
    }

    @Override
    public void onStart() {
        if(that==null){
            super.onStart();
        }
    }

    @Override
    public void onResume() {
        if(that==null){
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if(that==null){
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if(that==null){
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if(that==null){
            super.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(that==null){
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void startActivity(Intent intent) {
        Intent m=new Intent();
        ComponentName component = intent.getComponent();
        String className = component.getClassName();
        m.putExtra("className",className);
        if(that!=null){
            that.startActivity(m);
        }else{
            super.startActivity(intent);
        }

    }

    @Override
    public ComponentName startService(Intent service) {
        Intent m=new Intent();
        ComponentName component = service.getComponent();
        String serviceName = component.getClassName();
        m.putExtra("serviceName",serviceName);
        if(that!=null){
           return that.startService(m);
        }else{
           return super.startService(service);
        }
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        if(that!=null){
           return that.registerReceiver(receiver,filter);
        }else{
            return super.registerReceiver(receiver, filter);
        }
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        if(that!=null){
            that.unregisterReceiver(receiver);
        }else{

            super.unregisterReceiver(receiver);
        }
    }

    @Override
    public void sendBroadcast(Intent intent) {
        if(that!=null){
            that.sendBroadcast(intent);
        }else{
            super.sendBroadcast(intent);
        }
    }
}
