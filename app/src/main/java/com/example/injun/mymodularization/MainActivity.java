package com.example.injun.mymodularization;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.view.View;
import android.widget.Toast;

import com.example.extlibrary.BaseActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = "小童童";
        color = Color.CYAN;
    }

    private String name;
    private int color;

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn1:
                Map<String, String> map = new HashMap<String, String>();
                map.put("color", String.valueOf(Color.CYAN));
                map.put("name", "小童童");
                startActivityWithParam("UrlActivity", map);
                break;
        }
    }

    @Override
    protected int setStatusBarColor(@ColorInt int color)
    {
        return Color.RED;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1 && resultCode == 2)
        {
            Toast.makeText(this, "返回结果" + data.getStringExtra("result").toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
