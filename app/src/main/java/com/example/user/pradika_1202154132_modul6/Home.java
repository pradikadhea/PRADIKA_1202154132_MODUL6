package com.example.user.pradika_1202154132_modul6;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.user.pradika_1202154132_modul6.MainActivity;
import com.example.user.pradika_1202154132_modul6.homeall;
import com.example.user.pradika_1202154132_modul6.homeuser;
import com.example.user.pradika_1202154132_modul6.upload;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

//deklarasi variabel
public class Home extends AppCompatActivity {
    ViewPager viewpag; TabLayout tablay; AppBarLayout applay; Toolbar toolb;
    FirebaseAuth auth;

    //menghubungkan dengan layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);viewpag = findViewById(R.id.viewpag); tablay= findViewById(R.id.tablay);
        applay = findViewById(R.id.applay); toolb = findViewById(R.id.toolb);
        auth = FirebaseAuth.getInstance();

        //Menentukan toolbar
        setSupportActionBar(toolb);
        setupPager(viewpag);

        //Mengikat tablayout dengan viewpager
        tablay.setupWithViewPager(viewpag);
        tablay.getTabAt(0).setIcon(R.drawable.ic_group);
        tablay.getTabAt(1).setIcon(R.drawable.ic_person);
        tablay.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    applay.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }else{
                    applay.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //Method ketika membuat menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    //Method ketika menu logout dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menulogout) {
            auth.signOut();
            startActivity(new Intent(Home.this, MainActivity.class));
            finish();
        }
        return true;
    }


    //Menetapkan adapter untuk viewpager
    public void setupPager(ViewPager v){
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager());
        adapter.addFragment(new homeall(), "TERBARU");
        adapter.addFragment(new homeuser(), "SAYA");

        v.setAdapter(adapter);
    }

    //membangun intent
    public void AddPost(View view) {
        startActivity(new Intent(Home.this, upload.class));
    }

    //Subclass sebagai adapter untuk Viewpager dengan fragmentnya
    class VPAdapter extends FragmentPagerAdapter{
        private final List<Fragment> listfragment = new ArrayList<>();
        private final List<String> listfragmenttitle = new ArrayList<>();
        public VPAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listfragment.get(position);
        }

        public void addFragment(Fragment f, String title){
            listfragment.add(f);
            listfragmenttitle.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return listfragment.size();
        }
    }
}
