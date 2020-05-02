package learncodeonline.in.mymall.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import learncodeonline.in.mymall.R;

public class CartAdapter extends RecyclerView.Adapter {

    private List<CartItemModel> cartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()) {
            case 0:
                return CartItemModel.CART_ITEM;
            case 1:
                return CartItemModel.TOTAL_AMOUNT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case CartItemModel.CART_ITEM:
                View cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
                return new cartItemViewholder(cartItemView);
            case CartItemModel.TOTAL_AMOUNT:
                View cartTotalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount_layout, parent, false);
                return new cartTotalAmountViewholder(cartTotalView);
            default:
                return null;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (cartItemModelList.get(position).getType()) {
            case CartItemModel.CART_ITEM:
                int resource = cartItemModelList.get(position).getProductImage();
                String title = cartItemModelList.get(position).getProductTitle();
                int freeCoupon = cartItemModelList.get(position).getFreeCoupon();
                String productPrice = cartItemModelList.get(position).getProductPrice();
                String cuttedPrice = cartItemModelList.get(position).getCuttedPrice();
                int offersApplied = cartItemModelList.get(position).getOffersApplied();
                ((cartItemViewholder)holder).setItemDetails(resource,title,freeCoupon,productPrice,cuttedPrice,offersApplied);
                break;
            case CartItemModel.TOTAL_AMOUNT:
                String totalItem = cartItemModelList.get(position).getTotalItem();
                String totalItemPrice = cartItemModelList.get(position).getTotalItemPrice();
                String totalPrice = cartItemModelList.get(position).getTotalPrice();
                String deliveryPrice = cartItemModelList.get(position).getDeliveryPrice();
                String savedAmount = cartItemModelList.get(position).getSavedAmount();
                ((cartTotalAmountViewholder)holder).setTotalAmount(totalItem,totalItemPrice,totalPrice,deliveryPrice,savedAmount);
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    class cartItemViewholder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private ImageView freeCouponIcon;
        private TextView productTitle;
        private TextView freeCoupons;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView productQuantity;
        private TextView offersApplied;
        private TextView couponsApplied;

        public cartItemViewholder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            freeCouponIcon = itemView.findViewById(R.id.free_coupon_icon);
            freeCoupons = itemView.findViewById(R.id.tv_free_coupon);
            productPrice = itemView.findViewById(R.id.product_price);
            cuttedPrice = itemView.findViewById(R.id.cutted_price);
            productQuantity = itemView.findViewById(R.id.product_quantity);
            offersApplied = itemView.findViewById(R.id.offers_applied);
            couponsApplied = itemView.findViewById(R.id.coupons_applied);
        }

        private void setItemDetails(int resource, String title, int freeCouponNum, String productPriceText, String cuttedPriceText, int offersAppliedNum) {
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if (freeCouponNum > 0) {
                freeCoupons.setVisibility(View.VISIBLE);
                freeCouponIcon.setVisibility(View.VISIBLE);
                if (freeCouponNum == 1)
                    freeCoupons.setText("free " + freeCouponNum + " Coupon");
                else
                    freeCoupons.setText("free " + freeCouponNum + " Coupons");
            } else {
                freeCoupons.setVisibility(View.INVISIBLE);
                freeCouponIcon.setVisibility(View.INVISIBLE);
            }
            productPrice.setText(productPriceText);
            cuttedPrice.setText(cuttedPriceText);
            if (offersAppliedNum > 0) {
                offersApplied.setVisibility(View.VISIBLE);
                offersApplied.setText(offersAppliedNum + " Offers applied");
            } else {
                offersApplied.setVisibility(View.INVISIBLE);
            }
        }
    }

    class cartTotalAmountViewholder extends RecyclerView.ViewHolder {

        private TextView totalItem;
        private TextView totalItemPrice;
        private TextView totalPrice;
        private TextView deliveryPrice;
        private TextView savedAmount;

        public cartTotalAmountViewholder(@NonNull View itemView) {
            super(itemView);
            totalItem = itemView.findViewById(R.id.total_item);
            totalItemPrice = itemView.findViewById(R.id.total_item_price);
            totalPrice = itemView.findViewById(R.id.total_price);
            deliveryPrice = itemView.findViewById(R.id.delivery_price);
            savedAmount = itemView.findViewById(R.id.saved_amount);
        }

        private void setTotalAmount(String totalItemText, String totalItemPriceText, String totalPriceText, String deliveryPriceText, String savedAmountText) {
            totalItem.setText(totalItemText);
            totalItemPrice.setText(totalItemPriceText);
            totalPrice.setText(totalPriceText);
            deliveryPrice.setText(deliveryPriceText);
            savedAmount.setText(savedAmountText);
        }
    }
}
