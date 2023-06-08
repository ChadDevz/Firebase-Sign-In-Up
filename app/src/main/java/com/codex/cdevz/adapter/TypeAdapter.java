package com.codex.cdevz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.codex.cdevz.R;

public class TypeAdapter extends ArrayAdapter<String> {

    private int spinner_id;
    private String[] sName;
    public TypeAdapter(Context context, int spinner_id, String[] list) {
        super(context, R.layout.spinner_item, list);
        this.spinner_id = spinner_id;
        this.sName = list;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return view(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return view(position, convertView, parent);
    }

    private View view(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);
        TextView tv = v.findViewById(R.id.tvItemName);
		String sname = sName[position];
        tv.setText(sname);
        return v;
    }

}

