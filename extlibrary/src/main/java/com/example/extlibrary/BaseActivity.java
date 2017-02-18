package com.example.extlibrary;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.github.mzule.activityrouter.router.Routers;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.Map;


public abstract class BaseActivity extends AppCompatActivity
{

    private SystemBarTintManager mSystemBarTintManager;

    protected <T extends View> T $(int resId)
    {
        return (T) findViewById(resId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // 4.4及以上版本开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            setTranslucentStatus(true);
        }

        mSystemBarTintManager = new SystemBarTintManager(this);
        mSystemBarTintManager.setStatusBarTintEnabled(true);
        mSystemBarTintManager.setNavigationBarTintEnabled(true);

        // 自定义颜色
        mSystemBarTintManager.setTintColor(setStatusBarColor(Color.parseColor("#00FFFFFF")));
    }

    protected abstract int setStatusBarColor(@ColorInt int color);

    @TargetApi(19)
    private void setTranslucentStatus(boolean on)
    {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on)
        {
            winParams.flags |= bits;
        } else
        {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    /**
     * 带有返回值的启动方式
     *
     * @param targetRouterName 目标Activity
     * @param requestCode      请求码
     */
    protected void startActivityForResult(String targetRouterName, int requestCode)
    {
        Routers.openForResult(this, RouterUtil.SCHEME + targetRouterName, requestCode);
    }

    /**
     * 启动Activity
     *
     * @param targetRouterName
     */
    protected void startActivity(String targetRouterName)
    {
        Routers.open(this, RouterUtil.SCHEME + targetRouterName);
    }

    /**
     * 带参数的启动方式 相当于Intent传值
     *
     * @param targetRouterName
     * @param params           相当于intent传递的值
     */
    protected void startActivityWithParam(String targetRouterName, Map<String, String> params)
    {

        if (params == null || params.size() == 0)
        {
            return;
        }

        StringBuffer url = new StringBuffer(
                RouterUtil.SCHEME + targetRouterName + RouterUtil.SYMBOL);

        for (Map.Entry<String, String> entry : params.entrySet())
        {
            url.append(
                    entry.getKey() + RouterUtil.ASSIGNMENT
                            + entry.getValue() + RouterUtil.AND);
        }

        String finalUrl = url.substring(0, url.lastIndexOf(RouterUtil.AND));

        Log.i("LCZ", finalUrl);

        Routers.open(this, finalUrl);

    }

    public interface RouterUtil
    {

        String SCHEME = "moduleTest://";
        String SYMBOL = "?";
        String AND = "&";
        String ASSIGNMENT = "=";

    }


}
