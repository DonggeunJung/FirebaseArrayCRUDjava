package com.example.firebasearraycrudjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BaseAdapter extends RecyclerView.Adapter {
    protected List<?> list = null;
    protected int itemLayout = -1;

    public void makeLayoutVertical(BaseAdapter adapter, RecyclerView rv, int layoutItem, ItemEvent itemEvent) {
        LinearLayoutManager lm = new LinearLayoutManager((Context)itemEvent,
                LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lm);
        adapter.setLayout(layoutItem, itemEvent);
        rv.setAdapter(adapter);
    }

    public void setLayout(int itemLayout, ItemEvent itemEvent) {
        this.itemLayout = itemLayout;
        this.itemEvent = itemEvent;
    }

    public void setList(List<?> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Object getData(int index) {
        return list.get(index);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(itemLayout, parent, false);
        return new BaseVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {}

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    protected View setClickable(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(list == null || list.size() <= position) return null;
        View itemView = ((BaseVH)holder).itemView;
        setClickable(itemView, position);
        return itemView;
    }

    protected void setClickable(View v, int position) {
        v.setTag(position);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemEvent != null) {
                    int index = (int) view.getTag();
                    itemEvent.onClickItem(index);
                }
            }
        });
    }

    class BaseVH extends RecyclerView.ViewHolder {
        public BaseVH(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface ItemEvent {
        void onClickItem(int index);
    }

    ItemEvent itemEvent = null;

}
