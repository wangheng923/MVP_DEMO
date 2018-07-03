package com.example.wangheng2.ak47.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangheng2.ak47.R;

/**
 * Created by wangheng2 on 2017/11/13.
 */

public class CFragment extends Fragment {

    private TextView tv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_c, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        tv = view.findViewById(R.id.textvb);
        super.onViewCreated(view, savedInstanceState);
    }
}
