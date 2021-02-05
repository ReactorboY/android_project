package com.organicpy.rxretromvp.model;

import android.util.Log;

import com.organicpy.rxretromvp.callback.Callback;
import com.organicpy.rxretromvp.util.NetworkUtils;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 04-02-2021
 */
public class Faq {
    private Faq(){}
    static String TAG = "RetroMe";
    public static void getFaq(final Callback<FaqModel> callback, int faqId) {
        NetworkUtils
                .iRetro()
                .getFaqs(faqId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<FaqModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: Faq Model");
                    }

                    @Override
                    public void onSuccess(@NonNull Response<FaqModel> faqModelResponse) {
                        if (faqModelResponse.code() == 200) {
                            callback.onResultReturn(faqModelResponse.body());
                        } else if (faqModelResponse.code() == 401) {
                            callback.onErrorReturn("Invalid token");
                        } else if (faqModelResponse.code() == 400) {
                            callback.onErrorReturn("Header Error");
                        } else if (faqModelResponse.code() == 500) {
                            callback.onErrorReturn("Internal Server error");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.onErrorReturn(e.getMessage());
                    }
                });
    }
}
