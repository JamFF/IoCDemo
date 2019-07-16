package com.ff.ioc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ff.ioc.base.BaseActivity;
import com.ff.ioc.lib.annotations.ContentView;
import com.ff.ioc.lib.annotations.InjectView;
import com.ff.ioc.lib.annotations.OnClick;
import com.ff.ioc.lib.annotations.OnLongClick;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @InjectView(R.id.tv)
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv.setText("TextView");
    }

    @OnClick({R.id.bt, R.id.tv}) // 有可能注解值是没有控件注入赋值的
    public void click(View btn) {
        switch (btn.getId()) {
            case R.id.bt:
//                Toast.makeText(this, "bt click", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, RViewActivity.class));
                break;

            case R.id.tv:
                Toast.makeText(this, "tv click", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @OnLongClick({R.id.bt, R.id.tv})
    public boolean longClick(View btn) {
        switch (btn.getId()) {
            case R.id.bt:
                Toast.makeText(this, "bt longClick", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv:
                Toast.makeText(this, "tv longClick", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
