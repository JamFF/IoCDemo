package com.ff.ioc;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.ff.ioc.adapter.SampleAdapter;
import com.ff.ioc.base.BaseActivity;
import com.ff.ioc.bean.UserInfo;
import com.ff.ioc.lib.InjectManager;
import com.ff.ioc.lib.annotations.ContentView;
import com.ff.ioc.lib.annotations.InjectView;
import com.ff.ioc.lib.annotations.OnItemClick;
import com.ff.ioc.lib.annotations.OnItemLongClick;
import com.ff.ioc.lib.recyclerview.RView;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_recyclerview)
public class RViewActivity extends BaseActivity {

    @InjectView(R.id.recyclerView)
    private RView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRView();
    }

    private List<UserInfo> initData() {
        List<UserInfo> data = new ArrayList<>();
        for (int i = 0; i < 49; i++) {
            data.add(new UserInfo("FF", "p" + i));
        }
        return data;
    }

    private void initRView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        SampleAdapter adapter = new SampleAdapter(initData());
        recyclerView.setRViewAdapter(adapter);
        recyclerView.setAdapter(adapter);

        // BaseActivity中调用InjectManager.inject(this);前面没有初始化好adapter
        // 所以需要在创建adapter后，再调用一次
        InjectManager.injectEvents(this);
    }

    @OnItemClick(R.id.recyclerView)
    public void itemClick(View view, UserInfo info, int position) {
        Toast.makeText(this, "OnItemClick\n" + info.getPassword(), Toast.LENGTH_SHORT).show();
    }

    @OnItemLongClick(R.id.recyclerView)
    public boolean itemLongClick(View view, UserInfo info, int position) {
        Toast.makeText(this, "OnItemLongClick\n" + info.getPassword(), Toast.LENGTH_SHORT).show();
        return true;
    }
}
