package learncodeonline.in.mymall.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import learncodeonline.in.mymall.R;
import learncodeonline.in.mymall.home.GridProductLayoutAdapter;
import learncodeonline.in.mymall.home.HorizontalProductScrollModel;
import learncodeonline.in.mymall.wishlist.WishlistAdapter;
import learncodeonline.in.mymall.wishlist.WishlistModel;

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridView gridView;
    public static List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);
        gridView = findViewById(R.id.grid_view);

        int layout_code = getIntent().getIntExtra("layout_code",-1);

        if(layout_code==0) {
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);


            List<WishlistModel> wishlistModelList = new ArrayList<>();
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "5", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "3", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "4.5", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "2", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "3.5", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "5", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "3", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "4.5", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "2", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "3.5", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "5", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "3", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "4.5", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "2", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "3.5", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "5", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "3", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "4.5", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "2", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone, "Pixel 2", 1, "3.5", 145, "Rs.49999/-", "Rs.59999/-", "Cash on Delivery"));


            WishlistAdapter adapter = new WishlistAdapter(wishlistModelList, false);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else if(layout_code==1) {
            gridView.setVisibility(View.VISIBLE);

            GridProductLayoutAdapter gridProductLayoutAdapter = new GridProductLayoutAdapter(horizontalProductScrollModelList);
            gridView.setAdapter(gridProductLayoutAdapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}