package learncodeonline.in.mymall.reward;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import learncodeonline.in.mymall.R;
import learncodeonline.in.mymall.product.ProductDetailActivity;

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.ViewHolder> {

    private List<RewardModel> rewardModelList;
    private Boolean useMiniLayout = false;

    public RewardAdapter(List<RewardModel> rewardModelList, Boolean useMiniLayout) {
        this.rewardModelList = rewardModelList;
        this.useMiniLayout = useMiniLayout;
    }

    @NonNull
    @Override
    public RewardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(useMiniLayout){
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_reward_item_layout, parent, false);
        }
        else {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rewards_item_layout, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = rewardModelList.get(position).getTitle();
        String date = rewardModelList.get(position).getValidity();
        String body = rewardModelList.get(position).getCouponBody();
        holder.setData(title, date, body);
    }

    @Override
    public int getItemCount() {
        return rewardModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView couponTitle;
        private TextView couponValidity;
        private TextView couponBody;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            couponTitle = itemView.findViewById(R.id.coupon_title);
            couponValidity = itemView.findViewById(R.id.coupon_validity);
            couponBody = itemView.findViewById(R.id.coupon_body);
        }
        private void setData(final String title, final String date, final String body){
            couponTitle.setText(title);
            couponValidity.setText(date);
            couponBody.setText(body);

            if(useMiniLayout){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProductDetailActivity.couponTitle.setText(title);
                        ProductDetailActivity.couponExpiryDate.setText(date);
                        ProductDetailActivity.couponBody.setText(body);
                        ProductDetailActivity.showDialogRecyclerView();
                    }
                });
            }
        }
    }
}
