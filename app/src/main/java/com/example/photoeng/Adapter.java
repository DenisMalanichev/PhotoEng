package com.example.photoeng;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context mContext;
    ArrayList<String> temp, temp2;


    public Adapter(Context mContext, ArrayList<String> temp, ArrayList<String> temp2) {
        this.mContext = mContext;
        this.temp = temp;
        this.temp2 = temp2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.my_row,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.text1.setText(temp.get(position));
    holder.text2.setText(temp2.get(position));
    }

    @Override
    public int getItemCount() {
        return temp.size();//data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView text1, text2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.word_item);
            text2 = itemView.findViewById(R.id.translated_item);
        }
    }
}
