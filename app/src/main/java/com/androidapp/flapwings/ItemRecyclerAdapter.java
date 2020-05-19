package com.androidapp.flapwings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> {
    private ArrayList<ItemData> listData = new ArrayList<>();
    Context context;
    KeyBoardActivity activity;

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        activity = (KeyBoardActivity) context;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_change, viewGroup, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) { // 뷰 홀더 onBind 함수로 전달
        itemViewHolder.onBind(listData.get(i));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addItem(ItemData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    public void removeItem() {
        listData.clear();
    } // 리스트데이터 삭제

    class ItemViewHolder extends RecyclerView.ViewHolder {


        private TextView txt_change;
        private ItemData itemData;

        ItemViewHolder(View itemView) {
            super(itemView);

            txt_change = itemView.findViewById(R.id.txt_change);

        }

        void onBind(final ItemData data) {
            this.itemData = data;
            txt_change.setText(data.getPut());
            txt_change.setOnClickListener(new View.OnClickListener() { // 텍스트가 클릭되면 액티비티의 etSet 함수에 in/put 단어를 넘김
                @Override
                public void onClick(View view) {
                    activity.etSet(data.getIn(), data.getPut());
                }
            });
        }
    }
}
