package com.organicpy.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

public class OfferFragment extends Fragment {

    public OfferFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton chainButton = view.findViewById(R.id.chain_button);
        MaterialButton ratioButton = view.findViewById(R.id.ratio_button);

        getChildFragmentManager().beginTransaction()
                .add(R.id.offer_frag_container, new ChainFragment())
                .commit();

        chainButton.setOnClickListener(v -> replaceMyFragment(new ChainFragment()));

        ratioButton.setOnClickListener(v -> replaceMyFragment(new RatioFragment()));
    }

    void replaceMyFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.offer_frag_container, fragment)
                .commit();
    }
}