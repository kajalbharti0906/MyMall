package learncodeonline.in.mymall.wishlist;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import learncodeonline.in.mymall.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyWishlistFragment extends Fragment {


    public MyWishlistFragment() {
        // Required empty public constructor
    }

    private RecyclerView wishlistRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_wishlist, container, false);
        wishlistRecyclerView = view.findViewById(R.id.my_wishlist_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        wishlistRecyclerView.setLayoutManager(layoutManager);

        List<WishlistModel> wishlistModelList = new ArrayList<>();
        wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone,"Pixel 2",1,"5",145,"Rs.49999/-","Rs.59999/-","Cash on Delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone,"Pixel 2",1,"3",145,"Rs.49999/-","Rs.59999/-","Cash on Delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone,"Pixel 2",1,"4.5",145,"Rs.49999/-","Rs.59999/-","Cash on Delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone,"Pixel 2",1,"2",145,"Rs.49999/-","Rs.59999/-","Cash on Delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.mobile_phone,"Pixel 2",1,"3.5",145,"Rs.49999/-","Rs.59999/-","Cash on Delivery"));

        WishlistAdapter wishlistAdapter = new WishlistAdapter(wishlistModelList, true);
        wishlistRecyclerView.setAdapter(wishlistAdapter);
        wishlistAdapter.notifyDataSetChanged();
        return view;
    }

}
