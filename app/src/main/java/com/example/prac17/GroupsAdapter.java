package com.example.prac17;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Group> list;

    public GroupsAdapter(Context context, ArrayList<Group> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GroupsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        Log.d("adapter", "adapter");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupsAdapter.ViewHolder holder, int position) {
        Group manga = list.get(position);
        holder.mangaName.setText(manga.getManga_Name());
        holder.mangaAuthor.setText(manga.getManga_Author());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mangaName;
        TextView mangaAuthor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mangaName = itemView.findViewById(R.id.m_name);
            mangaAuthor = itemView.findViewById(R.id.m_author);
        }
    }
}



