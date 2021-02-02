package com.organicpy.retroexample.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.organicpy.retroexample.R;
import com.organicpy.retroexample.models.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 30-01-2021
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

        private static final String TAG = "RecyclerAdapter";

        private List<Post> posts = new ArrayList<>();

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post_list_item, null, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            holder.bind(posts.get(position));
        }

        @Override
        public int getItemCount() {
            return posts.size();
        }

        public void setPosts(List<Post> posts){
            this.posts = posts;
            notifyDataSetChanged();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            TextView title;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title);
            }

            public void bind(Post post){
                title.setText(post.getTitle());
            }
        }
}
