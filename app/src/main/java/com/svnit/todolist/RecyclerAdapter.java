package com.svnit.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.listviewholder>{

    ArrayList<String> listview;
    ArrayList<Boolean> tickview;
    Context context;

    public RecyclerAdapter(ArrayList<String> listview, ArrayList<Boolean> tickview, Context context) {
        this.listview = listview;
        this.tickview = tickview;
        this.context = context;
    }

    @NonNull
    @Override
    public listviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);

        return new listviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listviewholder holder, int position) {
        holder.text.setText(listview.get(position));
        holder.checkBox.setChecked(tickview.get(position));
    }

    @Override
    public int getItemCount() {
        return listview.size();
    }

    public class listviewholder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView text;
        CardView card;
        public listviewholder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!tickview.get(getAdapterPosition())){
                        tickview.set(getAdapterPosition(), true);
                    }
                    else{
                        tickview.set(getAdapterPosition(), false);
                    }

                    Filehelper.writeData(listview,tickview, context.getApplicationContext());
                    notifyDataSetChanged();
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listview.remove(getAdapterPosition());
                    tickview.remove(getAdapterPosition());
                    Filehelper.writeData(listview,tickview, context.getApplicationContext());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                }

            });
            text = itemView.findViewById(R.id.textView2);
            card = itemView.findViewById(R.id.card);

        }

    }
}
