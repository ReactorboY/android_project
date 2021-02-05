package com.organicpy.retromvc.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.organicpy.retromvc.R;
import com.organicpy.retromvc.controller.Controller;
import com.organicpy.retromvc.model.FaqModel;
import com.organicpy.retromvc.model.adapter.RecycleAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Controller.FaqCallbackListener {
    String TAG = "Maini";
    Button button;
    Controller controller;
    RecyclerView recyclerView;
    List<FaqModel> faqModelList = new ArrayList<>();

    private RecycleAdapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.fetch_button);

        button.setOnClickListener(v -> {
            controller.startFetching();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        controller = new Controller(this);


        recycleAdapter = new RecycleAdapter(faqModelList);
        recyclerView.setAdapter(recycleAdapter);
    }

    @Override
    public void onFetch(FaqModel faqModel) {
        recycleAdapter.setFaq(faqModel);
    }
}