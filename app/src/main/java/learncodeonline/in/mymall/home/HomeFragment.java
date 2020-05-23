package learncodeonline.in.mymall.home;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import learncodeonline.in.mymall.DBqueries;
import learncodeonline.in.mymall.R;

import static learncodeonline.in.mymall.DBqueries.categoryModelList;
import static learncodeonline.in.mymall.DBqueries.firebaseFirestore;
import static learncodeonline.in.mymall.DBqueries.homePageModelList;
import static learncodeonline.in.mymall.DBqueries.loadCategories;
import static learncodeonline.in.mymall.DBqueries.loadFragmentData;



/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView homepageRecyclerView;
    private HomePageAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);



        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);

        if(categoryModelList.size()==0){
           loadCategories(categoryAdapter,getContext());
        }
        else{
            categoryAdapter.notifyDataSetChanged();
        }


        /////////// Horizontal Product
//        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
        /////////// Horizontal Product

        /////////////////////////////////
        homepageRecyclerView = view.findViewById(R.id.home_page_recycler_view);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homepageRecyclerView.setLayoutManager(testingLayoutManager);

        adapter = new HomePageAdapter(homePageModelList);
        homepageRecyclerView.setAdapter(adapter);


        if(homePageModelList.size()==0){
            loadFragmentData(adapter,getContext());
        }
        else{
            categoryAdapter.notifyDataSetChanged();
        }


        /////////////////////////////////
        return view;
    }

}
