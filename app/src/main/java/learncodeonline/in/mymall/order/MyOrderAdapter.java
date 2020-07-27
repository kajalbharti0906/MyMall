package learncodeonline.in.mymall.order;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import learncodeonline.in.mymall.DBqueries;
import learncodeonline.in.mymall.R;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.Viewholder> {

    private List<MyOrderItemModel> myOrderItemModelList;

    public MyOrderAdapter(List<MyOrderItemModel> myOrderItemModelList) {
        this.myOrderItemModelList = myOrderItemModelList;
    }

    @NonNull
    @Override
    public MyOrderAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.Viewholder holder, int position) {
        String productId = myOrderItemModelList.get(position).getProductId();
        String resource = myOrderItemModelList.get(position).getProductImage();
        String title = myOrderItemModelList.get(position).getProductTitle();
        String orderStatus = myOrderItemModelList.get(position).getOrderStatus();
        Date date;
        switch (orderStatus){
            case "Ordered":
                date = myOrderItemModelList.get(position).getOrderDate();
                break;
            case "Packed":
                date = myOrderItemModelList.get(position).getPackedDate();
                break;
            case "Shipped":
                date = myOrderItemModelList.get(position).getShippedDate();
                break;
            case "Delivered":
                date = myOrderItemModelList.get(position).getDeliveredDate();
                break;
            case "Cancelled":
                date = myOrderItemModelList.get(position).getCancelledDate();
                break;
            default:
                date = myOrderItemModelList.get(position).getCancelledDate();
        }
        int rating = myOrderItemModelList.get(position).getRating();
        holder.setdata(resource,title,orderStatus,date,rating,productId,position);
    }

    @Override
    public int getItemCount() {
        return myOrderItemModelList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private ImageView deliveryIndicator;
        private TextView productTitle;
        private TextView deliveryStatus;
        ////////rating layout
        private LinearLayout rateNowContainer;
        ////////rating layout

        public Viewholder(@NonNull final View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            deliveryIndicator = itemView.findViewById(R.id.order_indicator);
            productTitle = itemView.findViewById(R.id.product_title);
            deliveryStatus = itemView.findViewById(R.id.order_deliverd_date);
            rateNowContainer = itemView.findViewById(R.id.rate_now_container);

        }
        private void setdata(String resource, String title, String orderStatus, Date date, final int rating, final String productID, final int position) {
            Glide.with(itemView.getContext()).load(resource).into(productImage);
            productTitle.setText(title);

            if (orderStatus.equals("Cancelled")) {
                deliveryIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.colorPrimary)));
            }
            else{
                deliveryIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.successGreen)));
            }
            deliveryStatus.setText(orderStatus + String.valueOf(date));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent orderDetailsIntent = new Intent(itemView.getContext(), OrderDetailActivity.class);
                    orderDetailsIntent.putExtra("Position",position);
                    itemView.getContext().startActivity(orderDetailsIntent);
                }
            });

            ////////rating layout

            setRating(rating);
            for(int x=0;x<rateNowContainer.getChildCount();x++){
                final int starPosition = x;
                rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setRating(starPosition);
                        final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("PRODUCTS").document(productID);

                        FirebaseFirestore.getInstance().runTransaction(new Transaction.Function<Object>() {
                            @Nullable
                            @Override
                            public Object apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {

                                DocumentSnapshot documentSnapshot = transaction.get(documentReference);

                                if(rating!=0){
                                    Long increase = documentSnapshot.getLong(starPosition+1+"_star") + 1;
                                    Long decrease = documentSnapshot.getLong(rating+1+"_star") - 1;
                                    transaction.update(documentReference,starPosition+1+"_star",increase);
                                    transaction.update(documentReference,rating+1+"_star",decrease);
                                }else{
                                    Long increase = documentSnapshot.getLong(starPosition+1+"_star") + 1;
                                    transaction.update(documentReference,starPosition+1+"_star",increase);
                                }

                                return null;
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Object>() {
                            @Override
                            public void onSuccess(Object o) {

                                Map<String, Object> myRating = new HashMap<>();
                                if(DBqueries.myRatedIds.contains(productID)){
                                    myRating.put("rating_"+DBqueries.myRatedIds.indexOf(productID),(long)starPosition+1);
                                }
                                else{
                                    myRating.put("list_size",(long)DBqueries.myRatedIds.size()+1);
                                    myRating.put("product_ID_" + DBqueries.myRatedIds.size(), productID);
                                    myRating.put("rating_" + DBqueries.myRatedIds.size(), (long) starPosition + 1);

                                }

                                FirebaseFirestore.getInstance().collection("USERS").document(FirebaseAuth.getInstance().getUid()).collection("USER_DATA").document("MY_RATINGS")
                                        .update(myRating).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            DBqueries.myOrderItemModelList.get(position).setRating(starPosition);
                                            if(DBqueries.myRatedIds.contains(productID)){
                                                DBqueries.myRating.set(DBqueries.myRatedIds.indexOf(productID), Long.valueOf(starPosition+1));
                                            }else{
                                                DBqueries.myRatedIds.add(productID);
                                                DBqueries.myRatedIds.add(String.valueOf(Long.valueOf(starPosition+1)));
                                            }
                                        }
                                        else{
                                            String error = task.getException().getMessage();
                                            Toast.makeText(itemView.getContext(), error, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                            });

                            }
                        });
                    }
                });
            }
            ////////rating layout
        }
        private void setRating(int starPosition) {
            for(int x=0;x<rateNowContainer.getChildCount();x++){
                ImageView starBtn = (ImageView)rateNowContainer.getChildAt(x);
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
                if(x <= starPosition){
                    starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
                }
            }
        }
    }
}
