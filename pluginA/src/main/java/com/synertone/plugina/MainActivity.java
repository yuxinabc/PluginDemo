package com.synertone.plugina;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.synertone.plugina.broadcast.MyReceiver;
import com.synertone.plugina.service.OneService;

public class MainActivity extends BaseActivity {
    private MyReceiver myReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plugina_activity_main);
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("pluginA");
         myReceiver = new MyReceiver();
        registerReceiver(myReceiver,intentFilter);
        findViewById(R.id.img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(that!=null){
                    Toast.makeText(that,"-------->",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"-------->",Toast.LENGTH_SHORT).show();
                }
                if(that!=null){
                    startActivity(new Intent(that,SecondActivity.class));
                    startService(new Intent(that, OneService.class));
                }else{
                    startActivity(new Intent(MainActivity.this,SecondActivity.class));
                    startService(new Intent(MainActivity.this, OneService.class));
                }

            }
        });
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(that!=null){
                    Intent intent=new Intent();
                    intent.setAction("pluginA");
                    sendBroadcast(intent);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(myReceiver!=null){
            unregisterReceiver(myReceiver);
        }
    }
}
