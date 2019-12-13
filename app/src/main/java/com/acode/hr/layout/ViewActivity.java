package com.acode.hr.layout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {
    private HrLayout hrLayout;
    private LinearLayout llRoot;
    private Button btnLeft;
    private Button btnRight;
    private TextView tv11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        hrLayout = findViewById(R.id.hrLayout);
        llRoot = findViewById(R.id.llRoot);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);
        View header1 = View.inflate(this, R.layout.adapter_item, null);
        tv11 = header1.findViewById(R.id.tv11);
        hrLayout.addTouchView(llRoot);
        hrLayout.addHeaderView(header1);
        hrLayout.addTitleView(btnLeft, btnRight);
        tv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewActivity.this, "点击了headerview", Toast.LENGTH_SHORT).show();
            }
        });
        hrLayout.setOnScrollStateListener(new HrLayout.OnScrollStateListener() {
            @Override
            public void onState(int state) {
                if (state > 0) {
                    Log.d("HRLAYOUT", "顶部");
                } else {
                    Log.d("HRLAYOUT", "底部");
                }
            }
        });
    }

    public void funClick(View view) {
        if (!hrLayout.isTop()) {
            hrLayout.toTop();
        } else {
            hrLayout.toBottom();
        }
    }

    public void funStart(View view) {
        if (!hrLayout.isTop()) {
            hrLayout.toTop();
        }
    }

    public void funClose(View view) {
        if (hrLayout.isTop()) {
            hrLayout.toBottom();
        }
    }

}
