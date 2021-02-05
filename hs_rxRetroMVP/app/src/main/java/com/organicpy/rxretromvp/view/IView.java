package com.organicpy.rxretromvp.view;

import com.organicpy.rxretromvp.model.FaqModel;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 04-02-2021
 */
public interface IView {
    void onFetch(FaqModel faq);
    void onError(String message);
}
