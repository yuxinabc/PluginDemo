package com.synertone.plugindemo;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.synertone.plugindemo.utils.PluginManager;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().setContext(this);
        broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, " 我是宿主，收到你的消息,握手完成!", Toast.LENGTH_SHORT).show();
            }
        };
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.synertone.plugindemo");
        registerReceiver(broadcastReceiver,intentFilter);
    }
    public void load(View view) {
       PluginManager.getInstance().loadPlugin();

    }



    @Override
    protected void onStart() {
        super.onStart();

    }

    public void click(View view) {
        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra("className", PluginManager.getInstance().getPackageInfo().activities[0].name);
        startActivity(intent);
    }

    public void sendBroadCast(View view) {
        Toast.makeText(this, "我是宿主  插件插件!收到请回答!!  1", Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent();
                intent.setAction("com.synertone.plugina");
                sendBroadcast(intent);
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(broadcastReceiver!=null){
            unregisterReceiver(broadcastReceiver);
        }
    }
}
