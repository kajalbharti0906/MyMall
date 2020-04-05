package learncodeonline.in.mymall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HorizontalProductScrollAdapter extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder> {

    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public HorizontalProductScrollAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    @NonNull
    @Override
    public HorizontalProductScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_scroll_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductScrollAdapter.ViewHolder holder, int position) {
      int resource = horizontalProductScrollModelList.get(position).getProductImage();
      String name = horizontalProductScrollModelList.get(position).getProductName();
      String description = horizontalProductScrollModelList.get(position).getProductDescription();
      String price = horizontalProductScrollModelList.get(position).getProductPrice();

      holder.setProductImage(resource);
      holder.setProductName(name);
      holder.setProductDescription(description);
      holder.setProductPrice(price);
    }

    @Override
    public int getItemCount() {
        if(horizontalProductScrollModelList.size() > 8){
            return 8;
        }else {
            return horizontalProductScrollModelList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productName;
        private TextView productDescription;
        private TextView productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.hs_product_image);
            productName = itemView.findViewById(R.id.hs_product_name);
            productDescription = itemView.findViewById(R.id.hs_product_description);
            productPrice = itemView.findViewById(R.id.hs_product_price);
        }

        private void setProductImage(int resource){
            productImage.setImageResource(resource);
        }

        private void setProductName(String title){
            productName.setText(title);
        }

        private void setProductDescription(String description){
            productDescription.setText(description);
        }

        private void setProductPrice(String price){
            productPrice.setText(price);
        }
    }
}
