package com.jf.manifestreader;

import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);

        PackageManager pm = getPackageManager();

        //application meta-data
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            addText(applicationInfo.metaData.getString("meta_data"));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //"activity"或"activity-alias" meta-data
        try {
            ActivityInfo activityInfo = pm.getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            addText(activityInfo.metaData.getString("meta_data"));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //"provider" meta-data
        ComponentName providerComponentInfo = new ComponentName(this, MyContentProvider.class);
        try {
            ProviderInfo providerInfo = pm.getProviderInfo(providerComponentInfo, PackageManager.GET_META_DATA);
            addText(providerInfo.metaData.getString("meta_data"));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //"receiver" meta-data
        ComponentName receiverComponentInfo = new ComponentName(this, MyBroadcastReceiver.class);
        try {
            ActivityInfo receiverInfo = pm.getReceiverInfo(receiverComponentInfo, PackageManager.GET_META_DATA);
            addText(receiverInfo.metaData.getString("meta_data"));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //"service" meta-data
        ComponentName serviceComponentInfo = new ComponentName(this, MyService.class);
        try {
            ServiceInfo serviceInfo = pm.getServiceInfo(serviceComponentInfo, PackageManager.GET_META_DATA);
            addText(serviceInfo.metaData.getString("meta_data"));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addText(String str) {
        textView.setText(textView.getText().toString() + "\n" + str);
    }
}
