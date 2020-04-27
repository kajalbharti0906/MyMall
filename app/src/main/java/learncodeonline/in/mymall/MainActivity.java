package learncodeonline.in.mymall;

import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private static final int HOME_FRAGMENT=0;
    private static final int CART_FRAGMENT=1;

    private FrameLayout frameLayout;
    private ImageView actionBarLogo;
    private static int currentFragment=1;
    private NavigationView navigationView;

   // private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        actionBarLogo = findViewById(R.id.action_bar_logo);
        setSupportActionBar(toolbar);

        Log.i("error","oncreate");
        frameLayout = findViewById(R.id.main_frameLayout);
        setFragment(new HomeFragment(),HOME_FRAGMENT);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,0,0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);


    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(currentFragment==HOME_FRAGMENT) {
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    private void myCart() {
        //action bar icons are removed by using this function and again on create option menu will run.
        actionBarLogo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Cart");
        invalidateOptionsMenu();
        setFragment(new MyCartFragment(),CART_FRAGMENT);
        navigationView.getMenu().getItem(3).setChecked(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.main_search_icon){
            return true;
        }else if(id == R.id.main_notification_icon){
             return true;
        }else if(id == R.id.main_cart_icon){
            myCart();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();
        if(id == R.id.nav_my_mall){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            actionBarLogo.setVisibility(View.VISIBLE);
            invalidateOptionsMenu();
            setFragment(new HomeFragment(),HOME_FRAGMENT);
        }else if(id == R.id.nav_my_orders){
           return true;
        }else if(id == R.id.nav_my_rewards){
              return true;
        }else if(id == R.id.nav_my_cart){
            myCart();
            return true;
        }else if(id == R.id.nav_my_wishlist){
             return true;
        }else if(id == R.id.nav_my_account){
               return true;
        }else if(id == R.id.nav_sign_out){
              return true;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void setFragment(Fragment fragment, int fragmentNum) {

            if(currentFragment!=fragmentNum) {
                currentFragment = fragmentNum;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                fragmentTransaction.replace(frameLayout.getId(), fragment);
                fragmentTransaction.commit();
            }
    }
}
