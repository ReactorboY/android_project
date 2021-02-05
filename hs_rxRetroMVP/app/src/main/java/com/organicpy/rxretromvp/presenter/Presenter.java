package com.organicpy.rxretromvp.presenter;

import com.organicpy.rxretromvp.callback.Callback;
import com.organicpy.rxretromvp.model.Faq;
import com.organicpy.rxretromvp.model.FaqModel;
import com.organicpy.rxretromvp.view.IView;


/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 04-02-2021
 */
public class Presenter implements IPresenter {
    IView view;
    public Presenter(IView iView) {
        this.view = iView;
    }

    @Override
    public void loadFaqs() {
        Faq.getFaq(new Callback<FaqModel>() {
            @Override
            public void onResultReturn(FaqModel faqModel) {
                view.onFetch(faqModel);
            }

            @Override
            public void onErrorReturn(String message) {
                view.onError(message);
            }
        }, 1);
    }
}
