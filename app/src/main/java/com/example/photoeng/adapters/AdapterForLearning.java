package com.example.photoeng.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photoeng.DictionaryForLearningActivity;
import com.example.photoeng.R;
import com.example.photoeng.data.DBHelper;

import java.util.ArrayList;

public class AdapterForLearning  extends RecyclerView.Adapter<AdapterForLearning.MyViewHolderLearning>{

    public Context mContext;
    public ArrayList<String> temp, temp2;
    public DBHelper dbhelper;
    public static final String KEY_FOR_CHECKED_WORDS = "extra_checked";

    public AdapterForLearning(Context mContext, ArrayList<String> temp, ArrayList<String> temp2) {
        this.mContext = mContext;
        this.temp = temp;
        this.temp2 = temp2;

    }

    @NonNull
    @Override
    public MyViewHolderLearning onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.row_for_learning,parent, false);
        return  new MyViewHolderLearning(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForLearning.MyViewHolderLearning holder, int position) {
        holder.text1.setText(temp.get(position));
        holder.text2.setText(temp2.get(position));
    }

    @Override
    public int getItemCount() {
        return temp.size();
    }

    public class MyViewHolderLearning extends RecyclerView.ViewHolder {
        TextView text1, text2;
        CardView cardView;
        CheckBox checkBox;

        public MyViewHolderLearning(@NonNull View itemView) {
            super(itemView);

            text1 = itemView.findViewById(R.id.word_item);
            text2 = itemView.findViewById(R.id.translated_item);
            checkBox = itemView.findViewById(R.id.row_checkBox);
            cardView = itemView.findViewById(R.id.mCardViewForLearning);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkBox.isChecked()) {
                        Log.d("Test", ""+temp.get(getAdapterPosition()));
                        DictionaryForLearningActivity.getWordsToLearn().add(temp.get(getAdapterPosition()));
                        DictionaryForLearningActivity.getWordsToLearn().add(temp2.get(getAdapterPosition()));
                    }
                }
            });

        }


    }
    public boolean deleteTitle(String name)
    {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        return db.delete(DBHelper.TABLE_CONTACTS, DBHelper.KEY_WORDS + "=?", new String[]{name}) > 0;
    }


}

