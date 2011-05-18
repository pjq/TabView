
package net.impjq.tabview;

import android.app.Dialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabView extends TabActivity implements OnClickListener {

    public static final String TAB_MAIN = "tab0";
    public static final String TAB_BUDDY = "tab1";
    public static final String TAB_MORE = "tab2";
    public static final String TAB_ABOUT = "tab3";
    final int maxTabs = 4;
    private TabHost mTabHost;
    private ImageView[] mCoverBtns;
    private LinearLayout[] mTabs;
    int mPreClickID = 0;
    int mCurClickID = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_view);
        initView();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("tab") == true) {
            String tab = bundle.getString("tab");
            if (tab != null && tab.length() > 0) {
                mTabHost.setCurrentTabByTag(tab);
                switch (Integer.parseInt(tab.trim().substring(tab.length() - 1))) {
                    case 0:
                        mCurClickID = 0;
                        updataCurView(mCurClickID);
                        break;
                    case 1:
                        mCurClickID = 1;
                        updataCurView(mCurClickID);
                        break;
                    case 2:
                        mCurClickID = 2;
                        updataCurView(mCurClickID);
                        break;
                    case 3:
                        mCurClickID = 3;
                        updataCurView(mCurClickID);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Trace.e("Main onDestroy");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Trace.e("TabView", "Handle back keyevent");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initView() {
        mTabHost = getTabHost();
        setupIntent();
        setupTabSpec();
        mTabs = new LinearLayout[maxTabs];
        mTabs[0] = (LinearLayout) this.findViewById(R.id.tab1);
        mTabs[1] = (LinearLayout) this.findViewById(R.id.tab2);
        mTabs[2] = (LinearLayout) this.findViewById(R.id.tab3);
        mTabs[3] = (LinearLayout) this.findViewById(R.id.tab4);
        mCoverBtns = new ImageView[maxTabs];
        mCoverBtns[0] = (ImageView) this.findViewById(R.id.cover_btn1);
        mCoverBtns[1] = (ImageView) this.findViewById(R.id.cover_btn2);
        mCoverBtns[2] = (ImageView) this.findViewById(R.id.cover_btn3);
        mCoverBtns[3] = (ImageView) this.findViewById(R.id.cover_btn4);
        for (int i = 0; i < maxTabs; i++) {
            mTabs[i].setOnClickListener(this);
        }
        mPreClickID = 0;

    }

    private Intent mTabMainIntent;
    private Intent mTabBuddyIntent;
    private Intent mTabMoreIntent;
    private Intent mTabAboutIntent;

    private void setupIntent() {
        mTabMainIntent = new Intent(TabView.this, TabMain.class);
        mTabBuddyIntent = new Intent(TabView.this, TabBuddy.class);
        mTabMoreIntent = new Intent(TabView.this, TabMore.class);
        mTabAboutIntent = new Intent(TabView.this, TabAbout.class);
    }

    private void setupTabSpec() {
        TabSpec localTabSpec1 = mTabHost.newTabSpec(TAB_MAIN).setContent(mTabMainIntent)
                .setIndicator(getString(R.string.tab_main));
        mTabHost.addTab(localTabSpec1);

        TabSpec localTabSpec2 = mTabHost.newTabSpec(TAB_BUDDY).setContent(mTabBuddyIntent)
                .setIndicator(getString(R.string.tab_buddy));
        mTabHost.addTab(localTabSpec2);

        TabSpec localTabSpec3 = mTabHost.newTabSpec(TAB_MORE).setContent(mTabMoreIntent)
                .setIndicator(getString(R.string.tab_more));
        mTabHost.addTab(localTabSpec3);

        TabSpec localTabSpec4 = mTabHost.newTabSpec(TAB_ABOUT).setContent(mTabAboutIntent)
                .setIndicator(getString(R.string.tab_about));
        mTabHost.addTab(localTabSpec4);
    }

    private void updataCurView(int curClickID) {
        if (0 <= curClickID && maxTabs > curClickID) {
            mCoverBtns[mPreClickID].setVisibility(View.INVISIBLE);
            mCoverBtns[curClickID].setVisibility(View.VISIBLE);
            mPreClickID = curClickID;
        }
    }

    private void startSlip(View v) {
        Animation a = new TranslateAnimation(0.0f, v.getLeft() - mTabs[mPreClickID].getLeft(),
                0.0f, 0.0f);
        
        //int len = Math.abs(v.getLeft() - mTabs[mPreClickID].getLeft());
        // Interpolator interpolator=new LinearInterpolator();
        // a.setInterpolator(interpolator);
        a.setDuration(200);
        //a.setDuration(len * 1.5);
        a.setFillAfter(false);
        a.setFillBefore(false);
        for (int i = 0; i < maxTabs; i++) {
            if (mTabs[i] == v) {
                mCurClickID = i;
                break;
            }
        }
        a.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                updataCurView(mCurClickID);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        mCoverBtns[mPreClickID].startAnimation(a);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab1:
                mTabHost.setCurrentTabByTag(TAB_MAIN);
                startSlip(v);
                break;
            case R.id.tab2:
                mTabHost.setCurrentTabByTag(TAB_BUDDY);
                startSlip(v);
                break;
            case R.id.tab3:
                mTabHost.setCurrentTabByTag(TAB_MORE);
                startSlip(v);
                break;
            case R.id.tab4:
                mTabHost.setCurrentTabByTag(TAB_ABOUT);
                startSlip(v);
                break;

            default:
                break;
        }
    }
}
