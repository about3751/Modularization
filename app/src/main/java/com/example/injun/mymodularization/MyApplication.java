package com.example.injun.mymodularization;

import android.app.Application;

import com.github.mzule.activityrouter.annotation.Modules;

/**
 * Created by injun on 2017/2/17.
 */

/**
 * 这里的Modules值必须在每个模块的根目录创建相应的类 注解Module
 */
@Modules({"app","modulea"})
public class MyApplication extends Application
{

    @Override
    public void onCreate()
    {
        super.onCreate();
    }
}
