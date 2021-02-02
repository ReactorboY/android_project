package com.organicpy.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.organicpy.myapplication.data.Task;
import com.organicpy.myapplication.databinding.ActivitySimpleBinding;
import com.organicpy.myapplication.repo.TaskRepo;

import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Simple extends AppCompatActivity {
    ActivitySimpleBinding binding;
    String TAG = "SimpleACT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_simple);

        Single<Task> taskSingle = Single.just(new Task("Single task", true, "map", TaskRepo.createTaskList()))
                .subscribeOn(Schedulers.io());
        binding.observeButton.setOnClickListener(v-> {
            taskSingle.subscribe(new SingleObserver<Task>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    Log.d(TAG, "onSubscribe: Single");
                }

                @Override
                public void onSuccess(@NonNull Task task) {
                    getOperated(task);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.e(TAG, "onError: ",e);
                }
            });
        });
    }
    void getOperated(Task task) {
        Observable
                .just(task)
                .flatMapIterable(new Function<Task, Iterable<Integer>>() {
                    @Override
                    public Iterable<Integer> apply(@NonNull Task task) throws Exception {
                        return task.getDummyData();
                    }
                })
                 //[1,2,3,4,5,6]
                .filter(task2 -> (task2 % 2 != 0) )
                .map(integer -> {
                    Log.d(TAG, "apply: " + integer);
                    return integer * 3;
                })
//                .map(task12 -> {
//                    task12.setName("InnerCore");
//                    return task12;
//                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: Inner");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                        binding.observerText.setText(integer.toString());

//                        Log.d(TAG, "onNext: "+ task1.getDummyData());
//                        binding.setTask(task1);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }
    
}