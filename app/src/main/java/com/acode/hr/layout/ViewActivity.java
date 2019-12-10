package com.acode.hr.layout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {
    private HrLayout hrLayout;
    private Button btnTouchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        hrLayout = findViewById(R.id.hrLayout);
        btnTouchView = findViewById(R.id.btnTouchView);
        hrLayout.setTouchView(btnTouchView);
    }

    public void funClick(View view) {
        if (!hrLayout.isTop()) {
            hrLayout.toTop();
        } else {
            hrLayout.toBottom();
        }
    }
}
