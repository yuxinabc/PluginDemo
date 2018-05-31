package com.synertone.plugina.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.synertone.pluginstandard.PluginInterfaceBroadCast;

public class MyReceiver extends BroadcastReceiver implements PluginInterfaceBroadCast{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"---------收到广播！---------",Toast.LENGTH_LONG).show();
    }

    @Override
    public void attach(Context context) {
        Toast.makeText(context,"--------绑定context成功-----------",Toast.LENGTH_LONG).show();
    }

}
