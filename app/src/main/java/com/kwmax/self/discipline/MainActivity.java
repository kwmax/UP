package com.kwmax.self.discipline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kwmax.self.discipline.widget.TomatoView;

/**
 * Created by kwmax on 2019/10/3.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        findViewById(R.id.button_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewPageIndicatorActivity.class);
                startActivity(intent);
            }
        });

        TomatoView tomatoView = findViewById(R.id.tomato_view);
        tomatoView.start();
    }
}
