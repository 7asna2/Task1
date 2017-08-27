package com.example.hasnaa.orangelabstask.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hasnaa.orangelabstask.R;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Hasnaa on 14-08-2017.
 */
public class PhotosRecyclerViewAdapter extends RecyclerView.Adapter<PhotosRecyclerViewAdapter.ViewHolder> {
    Context context;
    List<String> imgUrls;
    private final String LOG_TAG = this.getClass().getSimpleName();

    public PhotosRecyclerViewAdapter(Context context, List<String> al) {
        this.context = context;
        imgUrls = al;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.photo_list_item, parent, false);
        final ViewHolder vh = new ViewHolder(item);
        return vh;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Picasso.with(context).load(imgUrls.get(position)).resize(640, 640).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return imgUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;

        public ViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.img_android);
        }

    }
}

