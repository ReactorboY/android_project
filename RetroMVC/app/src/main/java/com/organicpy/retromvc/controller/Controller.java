package com.organicpy.retromvc.controller;


import android.util.Log;

import com.organicpy.retromvc.model.FaqModel;
import com.organicpy.retromvc.model.FaqModelRetro;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 03-02-2021
 */
public class Controller {
    String TAG = "RightMe";
    private RetroController retroController;

    private FaqCallbackListener faqCallbackListener;

    public Controller(FaqCallbackListener listener) {
        this.faqCallbackListener = listener;
        retroController = new RetroController();
    }

    public void startFetching() {
        retroController.retroService().getFaqs(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<FaqModelRetro>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: Controller");
                    }

                    @Override
                    public void onSuccess(@NonNull FaqModelRetro faqModelRetro) {
                        Log.d(TAG, "onSuccess: " + faqModelRetro.getFaq());
                        for (FaqModelRetro.Faq f : faqModelRetro.getFaq()) {
                            FaqModel faq = new FaqModel(f.getQuestion(), f.getAnswer());
                            faqCallbackListener.onFetch(faq);
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }
                });
    }

    public interface FaqCallbackListener {
        void onFetch(FaqModel faqModel);
    }
}