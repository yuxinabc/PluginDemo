package com.synertone.plugindemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.synertone.plugindemo.utils.PluginManager;
import com.synertone.pluginstandard.PluginInterfaceBroadCast;
import java.lang.reflect.Constructor;

public class ProxyBroadCastReceiver extends BroadcastReceiver{
    private String ReceiverName;
    private PluginInterfaceBroadCast interfaceReceiver;
    public ProxyBroadCastReceiver(String  className,Context context) {
        ReceiverName= className;
        //        class
        try {
            Class loadClass= PluginManager.getInstance().getDexClassLoader().loadClass(ReceiverName);

            Constructor<?> localConstructor =loadClass.getConstructor(new Class[] {});
            Object instance = localConstructor.newInstance(new Object[] {});
//            Receiver
            interfaceReceiver = (PluginInterfaceBroadCast) instance;
            interfaceReceiver.attach(context);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ProxyBroadCastReceiver(BroadcastReceiver receiver, ProxyActivity context) {
        interfaceReceiver = (PluginInterfaceBroadCast) receiver;
        interfaceReceiver.attach(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        interfaceReceiver.onReceive(context,intent);
    }
}
