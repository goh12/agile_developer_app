package com.agiledev.agiledeveloper;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.agiledev.agiledeveloper.utils.InfoAdapter;

public class InfoActivity extends AppCompatActivity {

    private Button mGotIt;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        mGotIt = findViewById(R.id.got_it);
        mViewPager = findViewById(R.id.info_viewpager);


        InfoAdapter adapter = new InfoAdapter(getSupportFragmentManager());


        adapter.addFragment(new InfoScreenProject());
        adapter.addFragment(new InfoScreenMenuBar());
        adapter.addFragment(new InfoScreenPlanningPoker());
        adapter.addFragment(new InfoScreenPriority());

        mViewPager.setAdapter(adapter);



        mGotIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
