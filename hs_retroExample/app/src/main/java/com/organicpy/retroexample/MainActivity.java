package com.organicpy.retroexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.organicpy.retroexample.api.RetroCall;
import com.organicpy.retroexample.models.Post;
import com.organicpy.retroexample.util.RecyclerAdapter;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    String TAG = "GetPost";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerAdapter adapter;
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new RecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        RetroCall.retroService().getPath(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Post>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onSuccess(@NonNull Post post) {
                        Log.d(TAG, "onSuccess: " + post);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ",e );
                    }
                });

//        RetroCall.getRequestApi().getPosts()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new SingleObserver<List<Post>>() {
//                @Override
//                public void onSubscribe(@NonNull Disposable d) {
//                    Log.d(TAG, "onSubscribe: ");
//                }
//
//                @Override
//                public void onSuccess(@NonNull List<Post> posts) {
//                    adapter.setPosts(posts);
//                }
//
//                @Override
//                public void onError(@NonNull Throwable e) {
//                    Log.e(TAG, "onError: ", e);
//                }
//        });

//        Map<String , String> bd = new HashMap<>();
//        bd.put("categoryId", "6");
//        bd.put("description", "Checking from Studio: Intern");
//        bd.put("email", "roopa.patil@techtreeit.com");
//        bd.put("name", "Hussain");
//        bd.put("phoneNumber", "918861238357");
//
//
//        RetroCall.retroService().postit(bd)
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new CompletableObserver() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.d(TAG, "onSubscribe: "
//                );
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete: Successfully Posted");
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.e(TAG, "onError: ", e);
//            }
//        });
    }
}