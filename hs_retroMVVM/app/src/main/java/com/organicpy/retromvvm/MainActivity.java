package com.organicpy.retromvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.organicpy.retromvvm.data.adapter.HeroesAdapter;
import com.organicpy.retromvvm.data.model.Hero;
import com.organicpy.retromvvm.databinding.ActivityMainBinding;
import com.organicpy.retromvvm.viewmodel.HeroesViewModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    String TAG = "RetroMia";
    RecyclerView recyclerView;
    Button fetchButton;
    HeroesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HeroesViewModel viewModel = ViewModelProviders.of(this).get(HeroesViewModel.class);
        viewModel
                .getHeroes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Hero>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onSuccess(@NonNull List<Hero> heroList) {
                        Log.d(TAG, "onSuccess: " + heroList);
                        adapter = new HeroesAdapter(heroList, MainActivity.this);
                        binding.recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }
                });

    }
}