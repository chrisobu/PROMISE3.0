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

import com.example.faars.promise30.Fragments.AboutFragment;
import com.example.faars.promise30.Fragments.ChooseChildFragment;
import com.example.faars.promise30.Fragments.ContactFragment;
import com.example.faars.promise30.Fragments.FAQFragment;
import com.example.faars.promise30.Fragments.FeedbackFragment;
import com.example.faars.promise30.Fragments.InstructionVideoFragment;
import com.example.faars.promise30.Fragments.MainPageFragment;
import com.example.faars.promise30.Fragments.NextVideoFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    android.support.v4.app.FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, new MainPageFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("PROMISE 3.0");

        View navHeaderView = navigationView.getHeaderView(0);
        TextView childNameHeaderTextView = (TextView) navHeaderView.findViewById(R.id.childNameHeader);
        childNameHeaderTextView.setText(getChildName());

    }

    public String getChildName(){
        Intent intent = getIntent();
        String childName = intent.getStringExtra(ChooseChildFragment.EXTRA_CHILDNAME);

        return childName;
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



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new AboutFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("About the App");

        } else if (id == R.id.nav_instruction) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new InstructionVideoFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Instruction Video");

        } else if (id == R.id.nav_next_video) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new NextVideoFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Next Video");

        } else if (id == R.id.nav_new_video) {

        } else if (id == R.id.nav_my_videos) {

        } else if (id == R.id.nav_feedback) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new FeedbackFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Feedback");

        } else if (id == R.id.nav_faq) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new FAQFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Frequently Asked Questions");

        } else if (id == R.id.nav_contact) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new ContactFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Contact us");

        } else if (id == R.id.nav_choose_child) {
            finish();
            startActivity(new Intent(this, ChildActivity.class));

        } else if (id == R.id.nav_log_out) {
            finish();
            startActivity(new Intent(this, LogInActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
