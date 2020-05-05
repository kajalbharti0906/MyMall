package learncodeonline.in.mymall.address;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import learncodeonline.in.mymall.R;

import static learncodeonline.in.mymall.address.DeliveryActivity.SELECT_ADDRESS;

public class MyAddressActivity extends AppCompatActivity {

    private Button deliverHereBtn;
    private RecyclerView myaddressesRecyclerView;
    private static AddressesAdapter addressesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Addresses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myaddressesRecyclerView = findViewById(R.id.addresses_recycler_view);
        deliverHereBtn = findViewById(R.id.deliver_here_btn);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myaddressesRecyclerView.setLayoutManager(layoutManager);

        List<AdressesModel> adressesModelList = new ArrayList<>();
        adressesModelList.add(new AdressesModel("KAJAL","mjsdhndchdbnahjdsbdhb","476366", true));
        adressesModelList.add(new AdressesModel("KAJAL","mjsdhndchdbnahjdsbdhb","476366", false));
        adressesModelList.add(new AdressesModel("KAJAL","mjsdhndchdbnahjdsbdhb","476366", false));
        adressesModelList.add(new AdressesModel("KAJAL","mjsdhndchdbnahjdsbdhb","476366", false));
        adressesModelList.add(new AdressesModel("KAJAL","mjsdhndchdbnahjdsbdhb","476366", false));
        adressesModelList.add(new AdressesModel("KAJAL","mjsdhndchdbnahjdsbdhb","476366", false));

        int mode = getIntent().getIntExtra("MODE",-1);
        if(mode==SELECT_ADDRESS){
            deliverHereBtn.setVisibility(View.VISIBLE);
        }else{
            deliverHereBtn.setVisibility(View.GONE);
        }
        addressesAdapter = new AddressesAdapter(adressesModelList,mode);
        ((SimpleItemAnimator)myaddressesRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        myaddressesRecyclerView.setAdapter(addressesAdapter);
        addressesAdapter.notifyDataSetChanged();
    }

    public static void refreshItem(int deselect, int select){
        addressesAdapter.notifyItemChanged(deselect);
        addressesAdapter.notifyItemChanged(select);
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
