package com.acode.hr.layout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyleActivity extends AppCompatActivity {
    private RecyclerView rv;
    private MyAdapter myAdapter;
    private HrLayout hrLayout;
    private TextView tvTouchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyle);
        initView();
    }

    @SuppressLint("WrongConstant")
    private void initView() {
        rv = findViewById(R.id.rv);
        hrLayout = findViewById(R.id.hrLayout);
        tvTouchView = findViewById(R.id.tvTouchView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
        myAdapter = new MyAdapter();
        rv.setAdapter(myAdapter);
        hrLayout.addTouchView(tvTouchView);
    }

    public class MyAdapter extends RecyclerView.Adapter {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tv11.setText("" + position);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(RecyleActivity.this, "点击了" + position, Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return 50;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tv11;

            public ViewHolder(@NonNull ViewGroup parent) {
                super(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false));
                tv11 = itemView.findViewById(R.id.tv11);
            }
        }
    }

    public void funClick(View view) {
        if (!hrLayout.isTop()) {
            hrLayout.toTop();
        } else {
            hrLayout.toBottom();
        }
    }
}
