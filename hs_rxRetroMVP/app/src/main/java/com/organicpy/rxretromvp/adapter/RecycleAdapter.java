package com.organicpy.rxretromvp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.organicpy.rxretromvp.R;
import com.organicpy.rxretromvp.model.FaqModel;

import java.util.List;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 04-02-2021
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    List<FaqModel.Faq> users;

    public RecycleAdapter(List<FaqModel.Faq> userList){
        this.users = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_custom,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.question.setText(users.get(position).getQuestion());
        holder.answer.setText(users.get(position).getAnswer());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView question, answer;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question_text);
            answer = itemView.findViewById(R.id.answer_text);
        }
    }
}
