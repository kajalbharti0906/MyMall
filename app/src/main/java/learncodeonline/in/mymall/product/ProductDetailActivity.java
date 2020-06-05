package learncodeonline.in.mymall.product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import learncodeonline.in.mymall.MainActivity;
import learncodeonline.in.mymall.address.DeliveryActivity;
import learncodeonline.in.mymall.R;
import learncodeonline.in.mymall.authentication.RegisterActivity;
import learncodeonline.in.mymall.authentication.SignInFragment;
import learncodeonline.in.mymall.authentication.SignUpFragment;
import learncodeonline.in.mymall.reward.RewardAdapter;
import learncodeonline.in.mymall.reward.RewardModel;

import static learncodeonline.in.mymall.DBqueries.firebaseUser;
import static learncodeonline.in.mymall.MainActivity.showCart;
import static learncodeonline.in.mymall.authentication.RegisterActivity.setSignUpFragment;

public class ProductDetailActivity extends AppCompatActivity {

    private ViewPager productImagesViewPager;
    private TextView productTitle;
    private TextView averageRatingMiniview;
    private TextView totalRatingMiniview;
    private TextView productPrice;
    private TextView cuttedPrice;
    private ImageView codIndicator;
    private TextView tvcodIndicator;
    private TabLayout viewpagerIndicator;

    private LinearLayout couponRedemptionLayout;
    private Button couponRedemBtn;

    private TextView rewardTitle;
    private TextView rewardBody;

    //////// product description
    private ConstraintLayout productDetailsOnlyContainer;
    private ConstraintLayout productDetailsTabContainer;
    private ViewPager productDetailsViewpager;
    private TabLayout productDetailsTabLayout;
    private TextView productOnlyDescriptionBody;
    private List<ProductSpecificationModel> productSpecificationModelList = new ArrayList<>();
    private String productDescription;
    private String productOtherDetails;
    //////// product description

    ////////rating layout
    private LinearLayout rateNowContainer;
    private TextView totalRatings;
    private LinearLayout ratingsNoContainer;
    private TextView totalRatingsFigure;
    private LinearLayout ratingsProgressBarContainer;
    private TextView averageRating;
    ////////rating layout

    private Button buyNowBtn;
    private LinearLayout addToCartBtn;
    private static boolean ALREADY_ADDED_TO_WISHLIST = false;
    private FloatingActionButton addToWishlistBtn;

    private FirebaseFirestore firebaseFirestore;
    ///////coupon redem
    public static TextView couponTitle;
    public static TextView couponExpiryDate;
    public static TextView couponBody;
    private static RecyclerView couponRecyclerView;
    private static LinearLayout selectedCoupon;
    //////coupon redem

    private Dialog signInDialog;

    List<String> productImages = new ArrayList<>();

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
        productTitle = findViewById(R.id.product_title);
        averageRatingMiniview = findViewById(R.id.tv_product_rating_miniview);
        totalRatingMiniview = findViewById(R.id.total_rating_miniview);
        productPrice = findViewById(R.id.product_price);
        cuttedPrice = findViewById(R.id.cutted_price);
        codIndicator = findViewById(R.id.cod_indicator_image_view);
        tvcodIndicator = findViewById(R.id.tv_cod_indicator);
        rewardTitle = findViewById(R.id.reward_title);
        rewardBody = findViewById(R.id.reward_body);
        productDetailsTabContainer = findViewById(R.id.product_details_tab_container);
        productDetailsOnlyContainer = findViewById(R.id.product_detail_container);
        productOnlyDescriptionBody = findViewById(R.id.product_details_body);
        totalRatings = findViewById(R.id.total_ratings);
        ratingsNoContainer = findViewById(R.id.ratings_number_container);
        totalRatingsFigure = findViewById(R.id.total_rating_fig);
        ratingsProgressBarContainer = findViewById(R.id.ratings_progress_bar_container);
        averageRating = findViewById(R.id.average_rating);
        addToCartBtn = findViewById(R.id.add_to_cart_btn);
        couponRedemptionLayout = findViewById(R.id.coupon_redemption_layout);


        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("PRODUCTS").document("ohe4xccqQkVghlEdhBbc")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
              if(task.isSuccessful()){
                  DocumentSnapshot documentSnapshot = task.getResult();
                  for(long x=1;x<(long)documentSnapshot.get("no_of_product_image")+1;x++){
                     productImages.add(documentSnapshot.get("product_image_"+x).toString());
                  }
                  ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
                  productImagesViewPager.setAdapter(productImagesAdapter);
                  productTitle.setText(documentSnapshot.get("product_title").toString());
                  averageRatingMiniview.setText(documentSnapshot.get("average_rating").toString());
                  totalRatingMiniview.setText("("+(long)documentSnapshot.get("total_ratings")+")");
                  productPrice.setText(documentSnapshot.get("product_price").toString());
                  cuttedPrice.setText(documentSnapshot.get("cutted_price").toString());
                  if((boolean)documentSnapshot.get("COD")){
                      codIndicator.setVisibility(View.VISIBLE);
                      tvcodIndicator.setVisibility(View.VISIBLE);
                  }else{
                      codIndicator.setVisibility(View.INVISIBLE);
                      tvcodIndicator.setVisibility(View.INVISIBLE);
                  }
                  rewardTitle.setText((long)documentSnapshot.get("free_coupons") + documentSnapshot.get("free_coupon_title").toString());
                  rewardBody.setText(documentSnapshot.get("free_coupon_body").toString());
                  if((boolean)documentSnapshot.get("use_tab_layout")){
                      productDetailsTabContainer.setVisibility(View.VISIBLE);
                      productDetailsOnlyContainer.setVisibility(View.GONE);
                      productDescription = documentSnapshot.get("product_description").toString();
                      productOtherDetails = documentSnapshot.get("product_other_details").toString();
                      for(long x=1;x<(long)documentSnapshot.get("total_spec_titles")+1;x++){
                          productSpecificationModelList.add(new ProductSpecificationModel(0,documentSnapshot.get("spec_title_"+x).toString()));
                          for(long y=1;y<(long)documentSnapshot.get("spec_title_"+x+"_total_fields")+1;y++){
                              productSpecificationModelList.add(new ProductSpecificationModel(1,documentSnapshot.get("spec_title_"+x+"_field_"+y+"_name").toString(),documentSnapshot.get("spec_title_"+x+"_field_"+y+"_value").toString()));
                          }
                      }
                  }else{
                      productDetailsTabContainer.setVisibility(View.GONE);
                      productDetailsOnlyContainer.setVisibility(View.VISIBLE);
                      productOnlyDescriptionBody.setText(documentSnapshot.get("product_description").toString());
                  }
                    totalRatings.setText((long)documentSnapshot.get("total_ratings")+"ratings");
                  for(int x=0;x<5;x++){
                      TextView rating = (TextView) ratingsNoContainer.getChildAt(x);
                      rating.setText(String.valueOf((long)documentSnapshot.get((5-x)+"_star")));
                      ProgressBar progressBar = (ProgressBar) ratingsProgressBarContainer.getChildAt(x);
                      int maxProgress = Integer.parseInt(String.valueOf((long)documentSnapshot.get("total_ratings")));
                      progressBar.setMax(maxProgress);
                      progressBar.setProgress(Integer.parseInt(String.valueOf((long)documentSnapshot.get((5-x)+"_star"))));
                  }
                  totalRatingsFigure.setText(String.valueOf((long)documentSnapshot.get("total_ratings")));
                  averageRating.setText(documentSnapshot.get("average_rating").toString());
                  productDetailsViewpager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(),productDetailsTabLayout.getTabCount(), productDescription, productOtherDetails, productSpecificationModelList));
              }
              else{
                  String error = task.getException().getMessage();
                  Toast.makeText(ProductDetailActivity.this, error, Toast.LENGTH_SHORT).show();
              }
            }
        });

         viewpagerIndicator.setupWithViewPager(productImagesViewPager,true);
         addToWishlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firebaseUser==null){
                    signInDialog.show();
                }
                else {
                    if (ALREADY_ADDED_TO_WISHLIST) {
                        ALREADY_ADDED_TO_WISHLIST = false;
                        addToWishlistBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                    } else {
                        ALREADY_ADDED_TO_WISHLIST = true;
                        addToWishlistBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorPrimary));
                    }
                }
            }
        });


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
                     if(firebaseUser==null){
                        signInDialog.show();
                     }
                     else {
                         setRating(starPosition);
                     }
                 }
             });
         }
        ////////rating layout

        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firebaseUser==null){
                    signInDialog.show();
                }
                else {
                    Intent deliveryIntent = new Intent(ProductDetailActivity.this, DeliveryActivity.class);
                    startActivity(deliveryIntent);
                }
            }
        });

         addToCartBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(firebaseUser==null){
                     signInDialog.show();
                 }
                 else {
                     /////to do....
                 }
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

        ///////// sign in dialog
        signInDialog = new Dialog(ProductDetailActivity.this);
        signInDialog.setContentView(R.layout.sign_in_dialog);
        signInDialog.setCancelable(true);
        signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        Button dialogSignInBtn = signInDialog.findViewById(R.id.sign_in_btn_dialog);
        Button dialogSignUpBtn = signInDialog.findViewById(R.id.sign_up_btn_dialog);
        final Intent registerIntent = new Intent(ProductDetailActivity.this, RegisterActivity.class);


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

        ///////// sign in dialog

        if(firebaseUser == null){
            couponRedemptionLayout.setVisibility(View.GONE);
        }
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
            if(firebaseUser == null){
                signInDialog.show();
            }
            else {
                Intent cartIntent = new Intent(ProductDetailActivity.this, MainActivity.class);
                showCart = true;
                startActivity(cartIntent);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

}
