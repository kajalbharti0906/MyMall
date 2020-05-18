package learncodeonline.in.mymall.product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import learncodeonline.in.mymall.MainActivity;
import learncodeonline.in.mymall.address.DeliveryActivity;
import learncodeonline.in.mymall.R;
import learncodeonline.in.mymall.reward.RewardAdapter;
import learncodeonline.in.mymall.reward.RewardModel;

import static learncodeonline.in.mymall.MainActivity.showCart;

public class ProductDetailActivity extends AppCompatActivity {

    private ViewPager productImagesViewPager;
    private TabLayout viewpagerIndicator;
    private Button couponRedemBtn;

    ///////coupon redem
    public static TextView couponTitle;
    public static TextView couponExpiryDate;
    public static TextView couponBody;
    private static RecyclerView couponRecyclerView;
    private static LinearLayout selectedCoupon;
    //////coupon redem

    private ViewPager productDetailsViewpager;
    private TabLayout productDetailsTabLayout;

    ////////rating layout
    private LinearLayout rateNowContainer;
    ////////rating layout

    private Button buyNowBtn;
    private static boolean ALREADY_ADDED_TO_WISHLIST = false;
    private FloatingActionButton addToWishlistBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        productImagesViewPager = findViewById(R.id.product_images_viewpager);
        viewpagerIndicator = findViewById(R.id.viewpager_indicator);
        addToWishlistBtn = findViewById(R.id.add_to_wishlist_btn);
        productDetailsViewpager = findViewById(R.id.product_details_viewPager);
        productDetailsTabLayout = findViewById(R.id.product_details_tabLayout);
        buyNowBtn = findViewById(R.id.buy_now_btn);
        couponRedemBtn = findViewById(R.id.coupon_redemption_btn);

        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.mobile_phone);
        productImages.add(R.drawable.banner);
        productImages.add(R.drawable.banner1);
        productImages.add(R.drawable.banner3);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
        productImagesViewPager.setAdapter(productImagesAdapter);

        viewpagerIndicator.setupWithViewPager(productImagesViewPager,true);
        addToWishlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ALREADY_ADDED_TO_WISHLIST) {
                    ALREADY_ADDED_TO_WISHLIST = false;
                  addToWishlistBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                } else {
                    ALREADY_ADDED_TO_WISHLIST = true;
                    addToWishlistBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorPrimary));
                }
            }
        });

        productDetailsViewpager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(),productDetailsTabLayout.getTabCount()));

        productDetailsViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));
        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ////////rating layout
         rateNowContainer = findViewById(R.id.rate_now_container);
         for(int x=0;x<rateNowContainer.getChildCount();x++){
             final int starPosition = x;
             rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     setRating(starPosition);
                 }
             });
         }
        ////////rating layout

        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent = new Intent(ProductDetailActivity.this, DeliveryActivity.class);
                startActivity(deliveryIntent);
            }
        });

         ///////coupon dialog
        final Dialog checkCouponPriceDialog = new Dialog(ProductDetailActivity.this);
        checkCouponPriceDialog.setContentView(R.layout.coupon_reedem_dialog);
        checkCouponPriceDialog.setCancelable(true);
        checkCouponPriceDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        ImageView toogleRecyclerView = checkCouponPriceDialog.findViewById(R.id.toogle_recyclerview);
        couponRecyclerView = checkCouponPriceDialog.findViewById(R.id.coupons_recyclerview);
        selectedCoupon = checkCouponPriceDialog.findViewById(R.id.selected_coupon);
        couponTitle = checkCouponPriceDialog.findViewById(R.id.coupon_title);
        couponExpiryDate = checkCouponPriceDialog.findViewById(R.id.coupon_validity);
        couponBody = checkCouponPriceDialog.findViewById(R.id.coupon_body);

        TextView originalPrice = checkCouponPriceDialog.findViewById(R.id.original_price);
        TextView discountedPrice = checkCouponPriceDialog.findViewById(R.id.discounted_price);

        LinearLayoutManager layoutManager = new LinearLayoutManager(ProductDetailActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        couponRecyclerView.setLayoutManager(layoutManager);

        List<RewardModel> rewardModelList = new ArrayList<>();
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));

        RewardAdapter rewardAdapter = new RewardAdapter(rewardModelList,false);
        couponRecyclerView.setAdapter(rewardAdapter);
        rewardAdapter.notifyDataSetChanged();


        toogleRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogRecyclerView();
            }
        });

         couponRedemBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 checkCouponPriceDialog.show();
             }
         });
           /////////coupon dialog
    }

    public static void showDialogRecyclerView(){
        if(couponRecyclerView.getVisibility()==View.GONE){
            couponRecyclerView.setVisibility(View.VISIBLE);
            selectedCoupon.setVisibility(View.GONE);
        }
        else{
            couponRecyclerView.setVisibility(View.GONE);
            selectedCoupon.setVisibility(View.VISIBLE);
        }
    }
    private void setRating(int starPosition) {
        for(int x=0;x<rateNowContainer.getChildCount();x++){
            ImageView starBtn = (ImageView)rateNowContainer.getChildAt(x);
            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
            if(x <= starPosition){
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.home){
            finish();
            return true;
        }else if(id == R.id.main_search_icon){
            return true;
        }else if(id == R.id.main_cart_icon){
            Intent cartIntent = new Intent(ProductDetailActivity.this, MainActivity.class);
            showCart=true;
            startActivity(cartIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
