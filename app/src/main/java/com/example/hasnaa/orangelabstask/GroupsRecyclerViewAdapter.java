package com.example.hasnaa.orangelabstask;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by Hasnaa on 23-08-2017.
 */
public class GroupsRecyclerViewAdapter extends RecyclerView.Adapter<GroupsRecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<GroupDetails> groups ;
    private final String LOG_TAG = this.getClass().getSimpleName();

    public GroupsRecyclerViewAdapter(Context context, ArrayList<GroupDetails> al) {
        this.context = context;
        groups = al;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.group_list_item, parent, false);
        final ViewHolder vh = new ViewHolder(item);
        return vh;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.name.setText( groups.get(position).name);
        holder.members.setText( groups.get(position).members);
        holder.topics.setText( groups.get(position).topicCount);
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView members;
        TextView topics;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.group_name);
            members = (TextView) view.findViewById(R.id.group_members);
            topics = (TextView) view.findViewById(R.id.group_topics);
        }

    }
}



