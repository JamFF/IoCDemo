package com.ff.ioc.adapter;

import android.widget.TextView;

import com.ff.ioc.R;
import com.ff.ioc.bean.UserInfo;
import com.ff.ioc.lib.recyclerview.RViewAdapter;
import com.ff.ioc.lib.recyclerview.RViewHolder;

import java.util.List;

public class SampleAdapter extends RViewAdapter<UserInfo> {

    public SampleAdapter(List<UserInfo> data) {
        super(data);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_item;
    }

    @Override
    public void convert(RViewHolder holder, UserInfo info) {
        TextView textView = holder.getView(R.id.item_tv);
        textView.setText(info.toString());
    }
}
