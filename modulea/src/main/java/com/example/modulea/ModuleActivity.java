package com.example.modulea;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.extlibrary.BaseActivity;
import com.github.mzule.activityrouter.annotation.Router;

@Router("UrlActivity")
public class ModuleActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module1_activity_main);

        ((LinearLayout) $(R.id.module1_rootview))
                .setBackgroundColor(
                        Integer.parseInt(getIntent().getStringExtra("color")));
        ((TextView) $(R.id.module1_textview)).setText(getIntent().getStringExtra("name"));
    }

    @Override
    protected int setStatusBarColor(@ColorInt int color)
    {
        return 0;
    }
}
