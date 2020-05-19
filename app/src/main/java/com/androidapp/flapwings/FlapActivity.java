package com.androidapp.flapwings;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlapActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.img_flap)
    ImageView img_flap;
    int ran_s = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flap);
        ButterKnife.bind(this);
        listener();
        random();
    }

    private void random() {
        Random random = new Random();
        int ran = random.nextInt(5);
        for (ran = ran; ran == ran_s; ) { // 번호가 중복이면 for문이 무한실행됨
            ran = random.nextInt(5);
        }

        switch (ran + 1) {
            case 1:
                img_flap.setBackgroundResource(R.drawable.flap1);
                break;
            case 2:
                img_flap.setBackgroundResource(R.drawable.flap2);
                break;
            case 3:
                img_flap.setBackgroundResource(R.drawable.flap3);
                break;
            case 4:
                img_flap.setBackgroundResource(R.drawable.flap4);
                break;
            case 5:
                img_flap.setBackgroundResource(R.drawable.flap5);
                break;
            case 6:
                img_flap.setBackgroundResource(R.drawable.flap6);
                break;
        }
        ran_s = ran;
    }

    private void listener() {
        img_flap.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_flap:
                // 클릭마다 랜덤으로 이미지를 변경
                random();
                break;
        }
    }
}
