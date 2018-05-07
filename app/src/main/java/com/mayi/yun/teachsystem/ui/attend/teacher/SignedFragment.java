package com.mayi.yun.teachsystem.ui.attend.teacher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mayi.yun.teachsystem.R;


public class SignedFragment extends Fragment {

    public SignedFragment() {

    }

    public static SignedFragment newInstance() {
        SignedFragment fragment = new SignedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_signed, container, false);
    }
}
