package com.first.phonesearch;

import android.app.Application;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.lzy.okgo.OkGo;

public class MyApplication extends Application {
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.init(this);


    }
}
