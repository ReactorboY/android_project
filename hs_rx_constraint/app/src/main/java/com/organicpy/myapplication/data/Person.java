package com.organicpy.myapplication.data;

import android.text.Editable;
import android.text.TextWatcher;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

import java.util.Objects;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 20-01-2021
 */
public class Person {
    public ObservableField<String> name = new ObservableField<>();

    public TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!Objects.equals(name.get(), s.toString())) {
                name.set(s.toString());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
