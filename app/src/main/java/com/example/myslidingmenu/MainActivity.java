package com.example.myslidingmenu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myslidingmenu.fragment.HomeFragment;
import com.example.myslidingmenu.fragment.LeftFragment;
import com.example.myslidingmenu.fragment.RightFragment;
import com.example.myslidingmenu.slidingmenu.BaseSlidingFragmentActivity;
import com.example.myslidingmenu.slidingmenu.SlidingMenu;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述：主Activity extends BaseSlidingFragmentActivity
 *
 * @author hadis on 16.4.16.
 */

public class MainActivity extends BaseSlidingFragmentActivity {
    protected SlidingMenu mSlidingMenu;
    private Fragment mContent;
    @Bind(R.id.ivTitleBtnLeft)
    Button btn_left;
    @Bind(R.id.ivTitleBtnRigh)
    Button btn_right;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) { // 如果保存的状态不为空则得到之前保存的Fragment，否则实例化MyFragment
            mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
        }
        if (mContent == null) {
            mContent = new HomeFragment();
        }
        initLeftgMenu();//左侧菜单
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getReplaceFragment(R.id.content_frame, new HomeFragment());//主Fragment
        mSlidingMenu.setSecondaryMenu(R.layout.fragment_right);//右侧菜单
        getReplaceFragment(R.id.right_layout, new RightFragment());
    }

    private void initLeftgMenu() {
        setBehindContentView(R.layout.fragment_left);// 设置左菜单
        getReplaceFragment(R.id.left_layout, new LeftFragment());
        mSlidingMenu = getSlidingMenu();
        mSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);// 设置是左滑还是右滑，还是左右都可以滑，我这里左右都可以滑
        mSlidingMenu.setShadowWidth(getScreenWidth() / 60);// 设置阴影宽度
        mSlidingMenu.setBehindOffset(getScreenWidth() / 8);// 设置菜单宽度
        mSlidingMenu.setBehindWidth((int) (getScreenWidth() * 0.65));// 动态设置左边菜单伸出宽度
        mSlidingMenu.setRightBehindWidth((int) (getScreenWidth() * 0.35));// 动态设置右边菜单伸出宽度
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        mSlidingMenu.setShadowDrawable(R.drawable.slidingmenu_shadow);// 设置左菜单阴影图片
        mSlidingMenu.setSecondaryShadowDrawable(R.drawable.right_shadow);// 设置右菜单阴影图片
        mSlidingMenu.setFadeEnabled(true);// 设置滑动时菜单的是否淡入淡出
        mSlidingMenu.setFadeDegree(0.0f);// 设置淡入淡出的比例(范围0.0~1.0f)
        mSlidingMenu.setBehindScrollScale(0.333f);// 设置滑动时拖拽效果
        mSlidingMenu.setSelectorDrawable(R.mipmap.ic_launcher);
    }

    /**
     * replace替换
     *
     * @param resId
     * @param fragment
     */
    private void getReplaceFragment(int resId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(resId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * 获取屏幕分辨率宽度
     */
    private int getScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int mScreenWidth = dm.widthPixels;// 获取屏幕分辨率宽度
        return mScreenWidth;
    }

    /**
     * 切换Fragment
     *
     * @param fragment
     * @param title
     */
    public void switchConent(Fragment fragment, String title) {
        mContent = fragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
        getSlidingMenu().showContent();
    }

    @OnClick({R.id.ivTitleBtnLeft, R.id.ivTitleBtnRigh})
    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.ivTitleBtnLeft:
                mSlidingMenu.showMenu(true);
                break;
            case R.id.ivTitleBtnRigh:
                mSlidingMenu.showSecondaryMenu(true);
                break;
        }
    }

}
