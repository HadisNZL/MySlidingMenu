package com.example.myslidingmenu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myslidingmenu.MainActivity;
import com.example.myslidingmenu.R;

/**
 * 类描述：左侧菜单
 *
 * @author hadis on 16.4.16.
 */

public class LeftFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left, container, false);
        Button button = (Button) view.findViewById(R.id.left_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "功能键", Toast.LENGTH_SHORT).show();
                switchFragment(new HomeFragment(), "哈哈");
            }
        });
        return view;
    }

    /**
     * 切换fragment
     */
    private void switchFragment(Fragment fragment, String title) {
        if (getActivity() == null) {
            return;
        }
        if (getActivity() instanceof MainActivity) {
            MainActivity fca = (MainActivity) getActivity();
            fca.switchConent(fragment, title);
        }
    }
}
