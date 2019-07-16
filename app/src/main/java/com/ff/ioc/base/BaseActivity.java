package com.ff.ioc.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ff.ioc.lib.InjectManager;

/**
 * description: Activity基类
 * author: FF
 * time: 2019-07-15 16:04
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 帮助子类进行布局、控件、事件的注入
        InjectManager.inject(this);
    }
}
