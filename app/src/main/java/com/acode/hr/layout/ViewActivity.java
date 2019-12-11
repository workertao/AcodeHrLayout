package com.acode.hr.layout;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {
    private HrLayout hrLayout;
    private LinearLayout llRoot;
    private Button btnLeft;
    private Button btnRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        hrLayout = findViewById(R.id.hrLayout);
        llRoot = findViewById(R.id.llRoot);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);
        View header1 = View.inflate(this, R.layout.adapter_item, null);
        hrLayout.addTouchView(llRoot);
        hrLayout.addHeaderView(header1);
        hrLayout.addTitleView(btnLeft, btnRight);
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
