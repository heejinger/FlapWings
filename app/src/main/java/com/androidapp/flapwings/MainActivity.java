package com.androidapp.flapwings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener { // 클릭리스너 임플리먼트
    @BindView(R.id.btn_butt)
    ImageView btn_butt;
    @BindView(R.id.btn_custom)
    ImageView btn_custom;
    @BindView(R.id.btn_flap)
    ImageView btn_flap;
    @BindView(R.id.btn_key)
    ImageView btn_key;
    @BindView(R.id.img_logo)
    ImageView img_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        listener();
        Glide.with(this).load(R.drawable.butterfly).into(img_logo); // gif를 재생시켜주는 라이브러리

    }

    private void listener() { // 리스너 설정
        btn_butt.setOnClickListener(this);
        btn_custom.setOnClickListener(this);
        btn_flap.setOnClickListener(this);
        btn_key.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_custom:
                Intent intent3 = new Intent(MainActivity.this, NaverActivity.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
                overridePendingTransition(R.anim.loadin, R.anim.loadout); // 페이지 이동 애니메이션
                break;
            case R.id.btn_butt:
                Intent intent = new Intent(MainActivity.this, ButterflyActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.loadin, R.anim.loadout);
                break;
            case R.id.btn_flap:
                Intent intent2 = new Intent(MainActivity.this, FlapActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                overridePendingTransition(R.anim.loadin, R.anim.loadout);
                break;
            case R.id.btn_key:
                Intent intent4 = new Intent(MainActivity.this, KeyBoardActivity.class);
                intent4.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent4);
                overridePendingTransition(R.anim.loadin, R.anim.loadout);
                break;
        }
    }
}
