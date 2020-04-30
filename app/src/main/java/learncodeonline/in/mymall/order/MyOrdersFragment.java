package learncodeonline.in.mymall.order;


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
public class MyOrdersFragment extends Fragment {

    private RecyclerView myorderRecyclerView;

    public MyOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);
        myorderRecyclerView = view.findViewById(R.id.my_orders_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myorderRecyclerView.setLayoutManager(layoutManager);

        List<MyOrderItemModel> myOrderItemModelList = new ArrayList<>();
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.mobile_phone,2,"Pixel 2XL (BLACK)","Delivered on Mon,27 April 2020"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.mobile_phone,0,"Pixel 2XL (BLACK)","Delivered on Tue,21 April 2020"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.mobile_phone,4,"Pixel 2XL (BLACK)","Delivered on Tue,28 April 2020"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.mobile_phone,1,"Pixel 2XL (BLACK)","Delivered on Sun,26 April 2020"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.mobile_phone,3,"Pixel 2XL (BLACK)","Delivered on Mon,27 April 2020"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.mobile_phone,2,"Pixel 2XL (BLACK)","Delivered on Wed,29 April 2020"));

        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrderItemModelList);
        myorderRecyclerView.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();
        return view;
    }

}
