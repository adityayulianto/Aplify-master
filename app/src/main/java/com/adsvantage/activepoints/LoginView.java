package com.adsvantage.activepoints;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by HP on 02/12/2016.
 */

public class LoginView extends AppCompatActivity {
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private TextView[] dots;
    private Button next, skip;
    private LinearLayout dotsLayout;


    private int[] layouts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= 21)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        setContentView(R.layout.activity_loginview);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layout_dots);
        skip = (Button) findViewById(R.id.btn_skip);
        next = (Button) findViewById(R.id.btn_next);

        layouts = new int[]{R.layout.activity_screen_welcome1, R.layout.activity_screen_welcome2,
            R.layout.activity_screen_welcome3};

        addBottomDots(0);
        changeStatusBarColor();
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListener);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = layouts.length-1;
                viewPager.setCurrentItem(current);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current;
                if (next.getText() == "NEXT")
                    current = getItem(+1);
                else
                    current = getItem(-1);

                if(current < layouts.length)
                    viewPager.setCurrentItem(current);
                else{
                    Intent i = new Intent(LoginView.this, Login.class);
                    startActivity(i);
                    finish();
                }

            }
        });
    }

    private int getItem(int i){
        return viewPager.getCurrentItem() + i;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    private void addBottomDots(int position){
        dots = new TextView[layouts.length];
        int colorActive = getResources().getColor(R.color.white);
        int colorInactive = getResources().getColor(R.color.colorGradImageSlider);
        dotsLayout.removeAllViews();
        for(int i=0; i<dots.length; i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive);
            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0)
            dots[position].setTextColor(colorActive);
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            if(position==layouts.length-1){
                next.setText("BACK");
                //next.setVisibility(View.GONE);
                skip.setVisibility(View.GONE);
            }
            else {
                next.setText("NEXT");
                //next.setVisibility(View.VISIBLE);
                skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void changeStatusBarColor()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public class ViewPagerAdapter extends PagerAdapter
    {
        private LayoutInflater layoutInflater;
        private int resId = 0;
        private View v;
        private Button btnRegister;
        private TextView txtLogin;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(layouts[position], container, false);

            if(position == layouts.length - 1){
                btnRegister = (Button) v.findViewById(R.id.button2);
                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View m) {
                        Intent i = new Intent(LoginView.this, Register.class);
                        startActivity(i);
                        finish();
                    }
                });

                txtLogin = (TextView) v.findViewById(R.id.link_login);
                txtLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View m) {
                        Intent i = new Intent(LoginView.this, Login.class);
                        startActivity(i);
                        finish();
                    }
                });
            }
            container.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View) object;
            container.removeView(v);
        }
    }
}
