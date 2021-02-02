package com.organicpy.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    private static final String TAG = "MainFrag";

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button circular = view.findViewById(R.id.circular_button);
        Button biasButton = view.findViewById(R.id.biasButton);
        super.onViewCreated(view, savedInstanceState);

        getChildFragmentManager().beginTransaction()
                .add(R.id.home_frag_container, new BiasFragment())
                .commit();

        circular.setOnClickListener(v -> replaceMyFragment(new CircularFragment()));

        biasButton.setOnClickListener(v -> replaceMyFragment(new BiasFragment()));

        Observable<String> animalObservable = Observable.just("Ant", "Cat", "Bee", "Dog", "Fox");

        Observer<String> animalsObserver = getAnimalsObserver();

        animalObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(animalsObserver);
    }

    Observer<String> getAnimalsObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull String s) {
                Log.d(TAG, "onNext: "+ s);
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                Log.d(TAG, "onError: "+ e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: All items are emitted");
            }
        };
    }

    void replaceMyFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.home_frag_container, fragment)
                .commit();
    }
}