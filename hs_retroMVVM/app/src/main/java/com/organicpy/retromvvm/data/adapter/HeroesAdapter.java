package com.organicpy.retromvvm.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.organicpy.retromvvm.R;
import com.organicpy.retromvvm.data.model.Hero;

import java.util.List;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 07-02-2021
 */
public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.MyViewHolder> {

    List<Hero> heroList;
    Context heroContext;

    public HeroesAdapter(List<Hero> heroList, Context context) {
        this.heroList = heroList;
        this.heroContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Hero hero = heroList.get(position);
        Glide.with(heroContext)
                .load(hero.getImageurl())
                .into(holder.imageView);
        holder.textView.setText(hero.getName());
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
