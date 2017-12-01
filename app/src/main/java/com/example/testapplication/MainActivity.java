package com.example.testapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    ListView lv;
    Context context;

    private ArrayList<String> times = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting.");
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

      /*  mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener(){
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the //
                        // corresponding tab.
                        getActionBar().setSelectedNavigationItem(position);
                    }

                    }
        );
         */

        Date date = new Date(); // given date
        java.util.Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date); // assigns calendar to given date
        int hour = calendar.get(java.util.Calendar.HOUR_OF_DAY);

        java.util.Calendar cal = java.util.Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("E", Locale.ENGLISH);
        final String strDate = sdf.format(cal.getTime());

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        int index = 0;
        switch (strDate){
            case "Mon":
                index=1;
                break;
            case "Tue":
                index=2;
                break;
            case "Wed":
                index=3;
                break;
            case "Thur":
                index=4;
                break;
            case "Fri":
                index=5;
                break;
            case "Sat":
                index=6;
                break;
            case "Sun":
                index=7;
                break;
        }
        TabLayout.Tab tab = tabLayout.getTabAt(index);
        tab.select();

        ListView listView=(ListView)findViewById(R.id.listview1);
        context=this;
        lv=(ListView) findViewById(R.id.listview1);
        lv.setAdapter(new CustomAdapter(this,times));//prgmImages


        for(int i=0;i<24;i++) {
            if (i < 12){
                times.add(""+i + ":00 AM");
            }
            else
               times.add(""+i + ":00 PM");

        }


        ListView lst=(ListView)findViewById(R.id.listview1);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,times);
        lst.setAdapter(arrayAdapter);
        //((TextView) lst.findViewById(R.id.listview1)).setTypeface(null, Typeface.BOLD);
        lst.setSelection(hour);

    }


    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter= new SectionsPageAdapter(getSupportFragmentManager());


        adapter.addFragment(new Monday_Fragment(), "Mon");
        adapter.addFragment(new Tuesday_Fragment(), "Tue");
        adapter.addFragment(new Wednesday_Fragment(), "Wed");
        adapter.addFragment(new Thursday_Fragment(), "Thur");
        adapter.addFragment(new Friday_Fragment(), "Fri");
        adapter.addFragment(new Saturday_Fragment(), "Sat");
        adapter.addFragment(new Sunday_Fragment(), "Sun");
        viewPager.setAdapter(adapter);
    }

   /* private void setupTabs() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        ActionBar.Tab tab1 = actionBar
                .newTab()
                .setText("First")
                .setTabListener(new SupportFragmentTabListener<Monday_Fragment>(R.id.container, this,
                        "first", Monday_Fragment.class));

        actionBar.addTab(tab1);
        actionBar.selectTab(tab1);

        ActionBar.Tab tab2 = actionBar
                .newTab()
                .setText("Second")
                .setTabListener(new SupportFragmentTabListener<Tuesday_Fragment>(R.id.container, this,
                        "second", Tuesday_Fragment.class));
        actionBar.addTab(tab2);
    }  */



}


