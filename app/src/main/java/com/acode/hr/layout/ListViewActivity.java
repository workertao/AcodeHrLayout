package com.acode.hr.layout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ListViewActivity extends AppCompatActivity {
    private ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        lv = findViewById(R.id.lv);
        lv.setAdapter(new MyAdapter());
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, "点击了" + position, Toast.LENGTH_LONG).show();
            }
        });
    }


    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
            TextView tv11 = view.findViewById(R.id.tv11);
            tv11.setText("ListView" + position);
            return view;
        }
    }
}
