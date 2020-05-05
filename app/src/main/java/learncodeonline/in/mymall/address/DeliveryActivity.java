package learncodeonline.in.mymall.address;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import learncodeonline.in.mymall.R;
import learncodeonline.in.mymall.cart.CartAdapter;
import learncodeonline.in.mymall.cart.CartItemModel;

public class DeliveryActivity extends AppCompatActivity {


    public static final int SELECT_ADDRESS = 0;

    private RecyclerView deliveryRecyclerView;
    private Button changeOrAddNewAddressbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Delivery");

        deliveryRecyclerView = findViewById(R.id.delivery_recycler_view);
        changeOrAddNewAddressbtn = findViewById(R.id.change_or_add_address_button);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        deliveryRecyclerView.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.mobile_phone,"Pixel 2","Rs.49999/-","Rs.59999/-",2,1,0,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.mobile_phone,"Pixel 0","Rs.49999/-","Rs.59999/-",2,1,1,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.mobile_phone,"Pixel 2","Rs.49999/-","Rs.59999/-",0,1,2,0));
        cartItemModelList.add(new CartItemModel(1,"Price (3items)","Rs.169999/-", "Rs.169999/-", "Free","Rs.5999/-"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        deliveryRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        changeOrAddNewAddressbtn.setVisibility(View.VISIBLE);
        changeOrAddNewAddressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myaddressesIntent = new Intent(DeliveryActivity.this, MyAddressActivity.class);
                myaddressesIntent.putExtra("MODE",SELECT_ADDRESS);
                startActivity(myaddressesIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
         if(id==android.R.id.home){
             finish();
             return true;
         }
        return super.onOptionsItemSelected(item);
    }
}
