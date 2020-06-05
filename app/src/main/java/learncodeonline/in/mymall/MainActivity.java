package learncodeonline.in.mymall;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import learncodeonline.in.mymall.address.MyAccountFragment;
import learncodeonline.in.mymall.authentication.RegisterActivity;
import learncodeonline.in.mymall.authentication.SignInFragment;
import learncodeonline.in.mymall.authentication.SignUpFragment;
import learncodeonline.in.mymall.cart.MyCartFragment;
import learncodeonline.in.mymall.home.HomeFragment;
import learncodeonline.in.mymall.order.MyOrdersFragment;
import learncodeonline.in.mymall.reward.MyRewardsFragment;
import learncodeonline.in.mymall.wishlist.MyWishlistFragment;

import static learncodeonline.in.mymall.DBqueries.firebaseUser;
import static learncodeonline.in.mymall.authentication.RegisterActivity.setSignUpFragment;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private static final int HOME_FRAGMENT=0;
    private static final int CART_FRAGMENT=1;
    private static final int ORDERS_FRAGMENT=2;
    private static final int WISHLIST_FRAGMENT=3;
    private static final int REWARD_FRAGMENT=4;
    private static final int ACCOUNT_FRAGMENT=5;
    public static Boolean showCart = false;

    private FrameLayout frameLayout;
    private ImageView actionBarLogo;
    private int currentFragment=-1;
    private NavigationView navigationView;
    private Dialog signInDialog;

    private Window window;
    private Toolbar toolbar;

   // private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        actionBarLogo = findViewById(R.id.action_bar_logo);
        setSupportActionBar(toolbar);

        window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        frameLayout = findViewById(R.id.main_frameLayout);

        if (showCart) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            gotoFragment("My Cart", new MyCartFragment(), -2);
        } else {
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            setFragment(new HomeFragment(), HOME_FRAGMENT);
        }

        if(firebaseUser == null){
            navigationView.getMenu().getItem(navigationView.getMenu().size()-1).setEnabled(false);
        }
        else{
            navigationView.getMenu().getItem(navigationView.getMenu().size()-1).setEnabled(true);
        }

        signInDialog = new Dialog(MainActivity.this);
        signInDialog.setContentView(R.layout.sign_in_dialog);
        signInDialog.setCancelable(true);
        signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        Button dialogSignInBtn = signInDialog.findViewById(R.id.sign_in_btn_dialog);
        Button dialogSignUpBtn = signInDialog.findViewById(R.id.sign_up_btn_dialog);
        final Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);


        dialogSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInFragment.disableCloseBtn = true;
                SignUpFragment.disableCloseBtn = true;
                signInDialog.dismiss();
                setSignUpFragment = false;
                startActivity(registerIntent);
            }
        });

        dialogSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInFragment.disableCloseBtn = true;
                SignUpFragment.disableCloseBtn = true;
                signInDialog.dismiss();
                setSignUpFragment = true;
                startActivity(registerIntent);
            }
        });

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            if(currentFragment==HOME_FRAGMENT) {
                currentFragment=-1;
                super.onBackPressed();
            }
            else{
                if(showCart){
                     showCart=false;
                     finish();
                }else {
                    actionBarLogo.setVisibility(View.VISIBLE);
                    invalidateOptionsMenu();
                    setFragment(new HomeFragment(), HOME_FRAGMENT);
                    navigationView.getMenu().getItem(0).setChecked(true);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(currentFragment==HOME_FRAGMENT) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    private void gotoFragment(String title,Fragment fragment, int fragmentNum) {
        //action bar icons are removed by using this function and again on create option menu will run.
        actionBarLogo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment, fragmentNum);
        if (fragmentNum == CART_FRAGMENT) {
            navigationView.getMenu().getItem(3).setChecked(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.main_search_icon){
            return true;
        }else if(id == R.id.main_notification_icon){
             return true;
        }else if(id == R.id.main_cart_icon){

            if(firebaseUser==null) {
                signInDialog.show();
            }
            else {
                gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
            }
            return true;
        }else if(id == android.R.id.home){
            if(showCart){
                showCart=false;
                finish();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(firebaseUser != null) {
            int id = menuItem.getItemId();
            if (id == R.id.nav_my_mall) {
                actionBarLogo.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
                setFragment(new HomeFragment(), HOME_FRAGMENT);
            } else if (id == R.id.nav_my_orders) {
                gotoFragment("My Orders", new MyOrdersFragment(), ORDERS_FRAGMENT);
            } else if (id == R.id.nav_my_rewards) {
                gotoFragment("My Rewards", new MyRewardsFragment(), REWARD_FRAGMENT);
            } else if (id == R.id.nav_my_cart) {
                gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);

            } else if (id == R.id.nav_my_wishlist) {
                gotoFragment("My Wishlist", new MyWishlistFragment(), WISHLIST_FRAGMENT);

            } else if (id == R.id.nav_my_account) {
                gotoFragment("My Account", new MyAccountFragment(), ACCOUNT_FRAGMENT);
            } else if (id == R.id.nav_sign_out) {

            }
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        else{
            drawer.closeDrawer(GravityCompat.START);
            signInDialog.show();
            return false;
        }

    }


    private void setFragment(Fragment fragment, int fragmentNum) {

            if(currentFragment!=fragmentNum) {
                if(fragmentNum==REWARD_FRAGMENT){
                    window.setStatusBarColor(Color.parseColor("#5B04B1"));
                    toolbar.setBackgroundColor(Color.parseColor("#5B04B1"));
                }
                else{
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
                currentFragment = fragmentNum;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                fragmentTransaction.replace(frameLayout.getId(), fragment);
                fragmentTransaction.commit();
            }
    }
}
