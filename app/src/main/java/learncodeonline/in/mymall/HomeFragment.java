package learncodeonline.in.mymall;


import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    /////////// Banner Slider
     private ViewPager bannerSliderViewPager;
     private List<SliderModel> sliderModelList;
     private int currentPage = 2;
     private Timer timer;
     final private long DELAY_TIME = 2000;
     final private long PERIOD_TIME = 2000;
    /////////// Banner Slider

    /////////// Strip Ad
    private ImageView stripAdImage;
    private ConstraintLayout stripAdContainer;

    /////////// Strip Ad

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
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

        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);
        sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.banner7,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.banner8,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.banner1,"#FFFFFF"));

        sliderModelList.add(new SliderModel(R.drawable.banner3,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.banner5,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.banner7,"#FFFFFF"));

        sliderModelList.add(new SliderModel(R.drawable.banner8,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.banner1,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.banner3,"#FFFFFF"));


        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);
        bannerSliderViewPager.setCurrentItem(currentPage);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
             currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
               if(state == ViewPager.SCROLL_STATE_IDLE){
                   pageLooper();
               }
            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSlideShow();
                if(event.getAction() == MotionEvent.ACTION_UP){
                    startBannerSlideShow();
                }
                return false;
            }
        });

        /////////// Banner Slider

        /////////// Strip Ad
        stripAdImage = view.findViewById(R.id.strip_aid_image);
        stripAdContainer = view.findViewById(R.id.strip_aid_container);

        stripAdImage.setImageResource(R.drawable.banner);
        stripAdContainer.setBackgroundColor(Color.parseColor("#FFFFFF"));
        /////////// Strip Ad

        return view;
    }

    /////////// Banner Slider

    private void pageLooper(){
       if(currentPage==sliderModelList.size()-2){
           currentPage=2;
           bannerSliderViewPager.setCurrentItem(currentPage,false);
       }
       if(currentPage==1){
           currentPage=sliderModelList.size()-3;
           bannerSliderViewPager.setCurrentItem(currentPage,false);
       }
    }

    private void startBannerSlideShow(){
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if(currentPage >= sliderModelList.size()){
                    currentPage = 1;
                }
               bannerSliderViewPager.setCurrentItem(currentPage++,true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME,PERIOD_TIME);
    }
    private void stopBannerSlideShow(){
        timer.cancel();
    }
    /////////// Banner Slider

}
