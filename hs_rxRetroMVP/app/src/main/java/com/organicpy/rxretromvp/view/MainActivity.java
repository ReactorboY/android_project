package com.organicpy.rxretromvp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.organicpy.rxretromvp.R;
import com.organicpy.rxretromvp.adapter.RecycleAdapter;
import com.organicpy.rxretromvp.model.FaqModel;
import com.organicpy.rxretromvp.presenter.Presenter;
import com.organicpy.rxretromvp.util.NetworkConnectivity;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements IView{
    String TAG = "Mainis";
    Presenter presenter;
    RecyclerView recyclerView;
    RecycleAdapter recycleAdapter;
    Button fetchButton;
    TextView errorTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter(this);

//        NetworkConnectivity.isInternetOn(this).subscribe(new Observer<Boolean>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.d(TAG, "onSubscribe: ");
//            }
//
//            @Override
//            public void onNext(@NonNull Boolean aBoolean) {
//                Log.d(TAG, "onNext: ");
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.e(TAG, "onError: ",e );
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
        errorTextView = findViewById(R.id.http_error_text);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchButton = findViewById(R.id.fetch_button);
        fetchButton.setOnClickListener(v -> presenter.loadFaqs());
    }

    @Override
    public void onFetch(FaqModel faq) {
        if (recyclerView.getVisibility() == View.GONE) {
            recyclerView.setVisibility(View.VISIBLE);
            errorTextView.setVisibility(View.GONE);
        }
        recycleAdapter = new RecycleAdapter(faq.getFaq());
        recyclerView.setAdapter(recycleAdapter);
    }

    @Override
    public void onError(String message) {
        recyclerView.setVisibility(View.GONE);
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(message);
    }
}