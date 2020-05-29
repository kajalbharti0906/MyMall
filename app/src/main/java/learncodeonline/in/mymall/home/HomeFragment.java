package learncodeonline.in.mymall.home;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import learncodeonline.in.mymall.R;

import static learncodeonline.in.mymall.DBqueries.categoryModelList;
import static learncodeonline.in.mymall.DBqueries.lists;
import static learncodeonline.in.mymall.DBqueries.loadCategories;
import static learncodeonline.in.mymall.DBqueries.loadFragmentData;
import static learncodeonline.in.mymall.DBqueries.loadedCategoriesNames;


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
     private ImageView noInternetConnection;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        noInternetConnection = view.findViewById(R.id.no_internet_connection);

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()==true) {
            noInternetConnection.setVisibility(View.GONE);

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

            homepageRecyclerView = view.findViewById(R.id.home_page_recycler_view);
            LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
            testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            homepageRecyclerView.setLayoutManager(testingLayoutManager);


            if(lists.size()==0){
                loadedCategoriesNames.add("HOME");
                lists.add(new ArrayList<HomePageModel>());
                adapter = new HomePageAdapter(lists.get(0));
                loadFragmentData(adapter,getContext(),0,"Home");
            }
            else{
                adapter = new HomePageAdapter(lists.get(0));
                adapter.notifyDataSetChanged();
            }

            homepageRecyclerView.setAdapter(adapter);
        }
        else{
            Glide.with(this).load(R.drawable.forgot_password_image).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);
        }

        /////////////////////////////////
        return view;
    }

}
