package com.synertone.pluginstandard;
import android.content.Context;
import android.content.Intent;

public interface PluginInterfaceBroadCast {
    void attach(Context context);
    void onReceive(Context context, Intent intent);
}
