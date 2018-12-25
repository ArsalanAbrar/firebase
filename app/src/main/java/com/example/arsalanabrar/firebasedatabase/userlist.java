package com.example.arsalanabrar.firebasedatabase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class userlist extends RecyclerView.Adapter<userlist.MyViewHolder> {

    private List<listdata> dataList;
    Context mcontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username;

        public MyViewHolder(View view) {
            super(view);
            username=(TextView)view.findViewById(R.id.textView2);
        }
    }

    public userlist(List<listdata> dataList, Context context) {
        this.dataList = dataList;
        this.mcontext=context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview=LayoutInflater.from(parent.getContext()).inflate(R.layout.userlist,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        listdata data = dataList.get(position);
        holder.username.setText(data.getUser_name());
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
