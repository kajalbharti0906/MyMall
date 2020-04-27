package learncodeonline.in.mymall.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import learncodeonline.in.mymall.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView testing;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("perul","inside home fragmnebtr");
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        final List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel("link","Home"));
        categoryModelList.add(new CategoryModel("link","Electronics"));
        categoryModelList.add(new CategoryModel("link","Appliances"));
        categoryModelList.add(new CategoryModel("link","Furniture"));
        categoryModelList.add(new CategoryModel("link","Fashion"));
        categoryModelList.add(new CategoryModel("link","Toys"));
        categoryModelList.add(new CategoryModel("link","Sports"));
        categoryModelList.add(new CategoryModel("link","Wall Arts"));
        categoryModelList.add(new CategoryModel("link","Books"));
        categoryModelList.add(new CategoryModel("link","Shoes"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        /////////// Banner Slider
        List<SliderModel>sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.banner7,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.banner8,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.banner1,"#FFFFFF"));

        sliderModelList.add(new SliderModel(R.drawable.banner3,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.banner5,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.banner7,"#FFFFFF"));

        sliderModelList.add(new SliderModel(R.drawable.banner8,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.banner1,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.banner3,"#FFFFFF"));
        /////////// Banner Slider

        /////////// Horizontal Product
        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile_phone,"Redmi 5A","SD 625 Processor","Rs. 5999/-"));
        /////////// Horizontal Product

        /////////////////////////////////
        testing = view.findViewById(R.id.home_page_recycler_view);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(1,R.drawable.banner,"#FFFFFF"));
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(2,"Deals of the Day!",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2,"Deals of the Day!",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Deals of the Day!",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.banner,"#FFFFFF"));
        homePageModelList.add(new HomePageModel(3,"Deals of the Day!",horizontalProductScrollModelList));
        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        /////////////////////////////////
        return view;
    }

}
