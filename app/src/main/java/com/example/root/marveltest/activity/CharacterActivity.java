package com.example.root.marveltest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.marveltest.R;
import com.example.root.marveltest.adapter.ViewPagerAdapter;
import com.example.root.marveltest.fragment.StoriesFragment;
import com.example.root.marveltest.fragment.ComicsFragment;
import com.example.root.marveltest.fragment.SeriesFragment;
import com.example.root.marveltest.fragment.EventsFragment;
import com.squareup.picasso.Picasso;

/**
 * Created by root on 07.06.17.
 */

/**
 * Created by root on 06.06.17.
 */

public class CharacterActivity extends AppCompatActivity {


    private ImageView imageView;
    private TextView textName;
    private TextView textStory;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    ComicsFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_activity);

        imageView = (ImageView) findViewById(R.id.character_image);
        textName = (TextView) findViewById(R.id.character_name);
        initToolbar();


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        String image = extras.getString("image");

        textName.setText(name);
        Picasso.with(CharacterActivity.this)
                .load(image)
                .into(imageView);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_character);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ComicsFragment(), "COMICS");
        adapter.addFragment(new EventsFragment(), "EVENTS");
        adapter.addFragment(new SeriesFragment(), "SERIES");
        adapter.addFragment(new StoriesFragment(), "STORIES");
        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.apply, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.apply: {
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}