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
import android.widget.TextView;

import learncodeonline.in.mymall.DBqueries;
import learncodeonline.in.mymall.R;
import learncodeonline.in.mymall.cart.CartAdapter;

public class DeliveryActivity extends AppCompatActivity {


    public static final int SELECT_ADDRESS = 0;

    private RecyclerView deliveryRecyclerView;
    private Button changeOrAddNewAddressbtn;
    private TextView totalAmount;
    private TextView fullname;
    private TextView fullAddress;
    private TextView pincode;

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
        totalAmount = findViewById(R.id.total_cart_amount);
        fullname = findViewById(R.id.fullname);
        fullAddress = findViewById(R.id.address);
        pincode = findViewById(R.id.pincode);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        deliveryRecyclerView.setLayoutManager(layoutManager);

        CartAdapter cartAdapter = new CartAdapter(DBqueries.cartItemModelList, totalAmount, false);
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
    protected void onStart() {
        super.onStart();
        fullname.setText(DBqueries.adressesModelList.get(DBqueries.selectedAddress).getFullName());
        fullAddress.setText(DBqueries.adressesModelList.get(DBqueries.selectedAddress).getAddress());
        pincode.setText(DBqueries.adressesModelList.get(DBqueries.selectedAddress).getPincode());
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
