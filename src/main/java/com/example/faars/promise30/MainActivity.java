package com.example.faars.promise30;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.faars.promise30.Fragments.About2Fragment;
import com.example.faars.promise30.Fragments.AboutFragment;
import com.example.faars.promise30.Fragments.ChooseChildFragment;
import com.example.faars.promise30.Fragments.ContactFragment;
import com.example.faars.promise30.Fragments.FAQFragment;
import com.example.faars.promise30.Fragments.FeedbackFragment;
import com.example.faars.promise30.Fragments.InstructionVideoFragment;
import com.example.faars.promise30.Fragments.MainPageFragment;
import com.example.faars.promise30.Fragments.MyVideosFragment;
import com.example.faars.promise30.Fragments.NewVideoFragment;
import com.example.faars.promise30.Fragments.NextVideoFragment;
import com.example.faars.promise30.Fragments.SendVideoFragment;
import com.example.faars.promise30.SQL.MyDBHandler;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    android.support.v4.app.FragmentTransaction fragmentTransaction;
    public final static String EXTRA_LAYOUT = "com.example.faars.promise20";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Make navigation drawer (main menu)
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Set childName in main menu header:
        MyDBHandler dbHandler = MyDBHandler.getInstance(this);
        String childName = dbHandler.getCurrentChild();
        View navHeaderView = navigationView.getHeaderView(0);
        TextView childNameHeaderTextView = (TextView) navHeaderView.findViewById(R.id.childNameHeader);
        childNameHeaderTextView.setText(childName);

        // Check which layout to display:
        String layout = getIntent().getStringExtra(EXTRA_LAYOUT);
        if(layout != null){
            if(layout.equals("SendVideoFragment")){
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new SendVideoFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("PROMISE");
            } else if(layout.equals("MyVideosFragment")) {
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new MyVideosFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("PROMISE");
            }else{ // layout = "MainPageFragment" (or other)
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new MainPageFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("PROMISE");
            }
        }else{ // layout = empty
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new MainPageFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("PROMISE");
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new About2Fragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("PROMISE");

        } else if (id == R.id.nav_instruction) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new InstructionVideoFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("PROMISE");

        } else if (id == R.id.nav_next_video) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new NextVideoFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("PROMISE");

        } else if (id == R.id.nav_new_video) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new NewVideoFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("PROMISE");

        } else if (id == R.id.nav_my_videos) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new MyVideosFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("PROMISE");

        } else if (id == R.id.nav_feedback) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new FeedbackFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("PROMISE");

        }else if(id == R.id.nav_faq) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new FAQFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("PROMISE");

        } else if (id == R.id.nav_contact) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new ContactFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("PROMISE");

        } else if (id == R.id.nav_choose_child) {
            finish();
            startActivity(new Intent(this, ChildActivity.class));

        } else if (id == R.id.nav_log_out) {
            MyDBHandler dbHandler = MyDBHandler.getInstance(this);
            dbHandler.updateLoggedIn("false");
            finish();
            startActivity(new Intent(this, LogInActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
