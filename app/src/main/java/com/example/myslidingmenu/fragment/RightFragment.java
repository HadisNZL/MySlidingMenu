package com.example.myslidingmenu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myslidingmenu.R;

/**
 * 类描述：右侧菜单
 * @author hadis on 16.4.16.
 */
public class RightFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right, container, false);
        Button button = (Button) view.findViewById(R.id.right_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "RIGHT", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
