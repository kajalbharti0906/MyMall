package learncodeonline.in.mymall.wishlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import learncodeonline.in.mymall.R;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    private List<WishlistModel> wishlistModelList;

    public WishlistAdapter(List<WishlistModel> wishlistModelList) {
        this.wishlistModelList = wishlistModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int resource = wishlistModelList.get(position).getProductImage();
        String title = wishlistModelList.get(position).getProductTitle();
        int freeCouponNum = wishlistModelList.get(position).getFreecoupons();
        String averageRate = wishlistModelList.get(position).getRating();
        int totalRatingNum = wishlistModelList.get(position).getTotalRating();
        String price = wishlistModelList.get(position).getProductPrice();
        String cutprice = wishlistModelList.get(position).getCuttedPrice();
        String payMethod = wishlistModelList.get(position).getPaymentMethod();
        holder.setData(resource,title,freeCouponNum,averageRate,totalRatingNum,price,cutprice,payMethod);
    }

    @Override
    public int getItemCount() {
        return wishlistModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private TextView productTitle;
        private ImageView couponIcon;
        private TextView freeCoupon;
        private TextView rating;
        private TextView totalRating;
        private View priceCut;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView paymentMethod;
        private ImageButton deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             productImage = itemView.findViewById(R.id.product_image);
             productTitle = itemView.findViewById(R.id.product_title);
             couponIcon = itemView.findViewById(R.id.coupon_icon);
             freeCoupon = itemView.findViewById(R.id.free_coupon);
             rating = itemView.findViewById(R.id.tv_product_rating_miniview);
             totalRating = itemView.findViewById(R.id.total_ratings);
             priceCut = itemView.findViewById(R.id.cutted_divider);
             productPrice = itemView.findViewById(R.id.product_price);
             cuttedPrice = itemView.findViewById(R.id.cutted_price);
             paymentMethod = itemView.findViewById(R.id.payment_method);
             deleteBtn = itemView.findViewById(R.id.delete_button);
        }
        private void setData(int resource, String title, int freeCouponNum, String averageRate, int totalRatingNum, String price, String cutprice, String payMethod){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if (freeCouponNum > 0) {
                freeCoupon.setVisibility(View.VISIBLE);
                couponIcon.setVisibility(View.VISIBLE);
                if (freeCouponNum == 1)
                    freeCoupon.setText("free " + freeCouponNum + " Coupon");
                else
                    freeCoupon.setText("free " + freeCouponNum + " Coupons");
            } else {
                freeCoupon.setVisibility(View.INVISIBLE);
                couponIcon.setVisibility(View.INVISIBLE);
            }
         rating.setText(averageRate);
            totalRating.setText(totalRatingNum+"(ratings)");
            productPrice.setText(price);
            cuttedPrice.setText(cutprice);
            paymentMethod.setText(payMethod);
            
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Delete", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
