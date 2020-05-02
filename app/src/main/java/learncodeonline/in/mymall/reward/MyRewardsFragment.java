package learncodeonline.in.mymall.reward;


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
public class MyRewardsFragment extends Fragment {


    public MyRewardsFragment() {
        // Required empty public constructor
    }

    private RecyclerView myrewardRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_rewards, container, false);
        myrewardRecyclerView = view.findViewById(R.id.my_rewards_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myrewardRecyclerView.setLayoutManager(layoutManager);

        List<RewardModel> rewardModelList = new ArrayList<>();
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));
        rewardModelList.add(new RewardModel("Rewards","till 29th August,2019","Get 20% OFF on any product between Rs.500/- to Rs.2500/-"));

        RewardAdapter rewardAdapter = new RewardAdapter(rewardModelList);
        myrewardRecyclerView.setAdapter(rewardAdapter);
        rewardAdapter.notifyDataSetChanged();

        return view;
    }

}
