package com.organicpy.retromvc.model.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.organicpy.retromvc.R;
import com.organicpy.retromvc.model.FaqModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 03-02-2021
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private List<FaqModel> faqs;
    String TAG = "RecycleIt";
    public RecycleAdapter(List<FaqModel> faqModelList) {
        faqs = faqModelList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_custom_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FaqModel faq = faqs.get(position);
        holder.question.setText(faq.getQuestion());
        holder.answer.setText(faq.getAnswer());
    }

    @Override
    public int getItemCount() {
        return faqs.size();
    }

    public void setFaq(FaqModel faqModel) {
        faqs.add(faqModel);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        TextView answer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question_text1);
            answer = itemView.findViewById(R.id.answer_text1);
        }
    }
}
