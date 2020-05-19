package com.androidapp.flapwings;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KeyBoardActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.chunjiin_text)
    EditText et;
    @BindView(R.id.btn_insert)
    ImageView btn_insert;
    @BindView(R.id.txt_in)
    EditText txt_in;
    @BindView(R.id.txt_put)
    EditText txt_put;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    String[] in, put;
    KeyBoardActivity keyBoardActivity;
    final DBHelper dbHelper = new DBHelper(KeyBoardActivity.this, "Flap", null, 1);
    ItemRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboardactivity);
        ButterKnife.bind(this);

        adapter = new ItemRecyclerAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(KeyBoardActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter); // 리사이클러뷰 어댑터 세팅

        in = dbHelper.getIn().split("\\n"); // db헬퍼 클래스 함수를 불러옴 (\n형태로 리턴되게 해놔서 split(\\n)을 써서 배열로 받음
        put = dbHelper.getPut().split("\\n");
        if (in[0].equals("") || in[0] == null) { // sqlite 쿼리문에서 "" 값이나 null 값이 리턴되면 배열을 null로 지정
            in = null;
            put = null;
        }

        keyBoardActivity = this;

        listener();
    }

    private void listener() {
        et.addTextChangedListener(new TextWatcher() { // 텍스트체인지 리스너 설정
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(adapter.getItemCount() != 0){ // 어댑터에 포함된 아이템이 0이 아니면
                    adapter.removeItem(); // 어댑터 클리어함수
                    adapter.notifyDataSetChanged(); // 어댑터가 바뀌었다는걸 뷰에 전달
                }
                if (in != null) { // 배열이 null 이 아니라면
                    for (int i = 0; i < in.length; i++) { // for문 실행
                        if (et.getText().toString().contains(in[i])) { // 입력된 문자에 변경할 단어가 포함되어 있는지
                            ItemData itemData = new ItemData(); // ItemData 클래스에 in/put 단어를 보냄
                            itemData.setIn(in[i]);
                            itemData.setPut(put[i]);
                            adapter.addItem(itemData); // 클래스를 어댑터 리스트에 add
                            adapter.notifyDataSetChanged();
                            break;
                        }
                    }
                }
            }
        });
        btn_insert.setOnClickListener(this); // 클릭리스너 설정
    }

    public void etSet(String in,String put){ //  리사이클러뷰에서 텍스트를 선택하면 입력단어,출력단어를 파라미터로 받아서 replace
        et.setText(et.getText().toString().replace(in,put));
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_insert: // 단어 추가
                if (txt_in.getText().toString().length() != 0 && txt_put.getText().toString().length() != 0) {
                    String in = txt_in.getText().toString();
                    String put = txt_put.getText().toString();
                    dbHelper.Insert(in, put); // insert 함수를 불러옴
                    Toast toast = Toast.makeText(KeyBoardActivity.this, "추가 성공.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    txt_in.setText("");
                    txt_put.setText("");
                    Log.e("db_insert 결과 : ", dbHelper.getResult()); // 디비에 값이 잘 들어갔는지 로그

                } else { // 단어가 입력되지 않았을 시 토스트메세지 출력
                    Toast toast = Toast.makeText(KeyBoardActivity.this, "단어가 입력되지 않았습니다.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                break;
        }
    }
}
