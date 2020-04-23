package com.example.photoeng.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photoeng.Details;
import com.example.photoeng.R;
import com.example.photoeng.data.DBHelper;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    public Context mContext;
    public ArrayList<String> temp, temp2;
    public DBHelper dbhelper;

    public Adapter(Context mContext, ArrayList<String> temp, ArrayList<String> temp2 ) {
        this.mContext = mContext;
        this.temp = temp;
        this.temp2 = temp2;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view;

           view = inflater.inflate(R.layout.my_row , parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.text1.setText(temp.get(position));
    holder.text2.setText(temp2.get(position));
    }

    @Override
    public int getItemCount() {
        return temp.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;
        CardView cardView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Details.class);
                    intent.putExtra("title", temp.get(getAdapterPosition()));
                    intent.putExtra("position", getAdapterPosition());
                    intent.putExtra("translation", temp2.get(getAdapterPosition()));
                    v.getContext().startActivity(intent);
                }
            });
            text1 = itemView.findViewById(R.id.word_item);
            text2 = itemView.findViewById(R.id.translated_item);
            cardView = itemView.findViewById(R.id.mCardView);

        }


    }
    public boolean deleteTitle(String name)
    {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        return db.delete(DBHelper.TABLE_CONTACTS, DBHelper.KEY_WORDS + "=?", new String[]{name}) > 0;
    }


}
