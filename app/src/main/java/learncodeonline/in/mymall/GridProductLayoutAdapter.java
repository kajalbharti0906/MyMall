package learncodeonline.in.mymall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {

    List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public GridProductLayoutAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null){
          view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,null);
            ImageView productImage = view.findViewById(R.id.hs_product_image);
            TextView productName = view.findViewById(R.id.hs_product_name);
            TextView productDescription = view.findViewById(R.id.hs_product_description);
            TextView productPrice = view.findViewById(R.id.hs_product_price);

            productImage.setImageResource(horizontalProductScrollModelList.get(position).getProductImage());
            productName.setText(horizontalProductScrollModelList.get(position).getProductName());
            productDescription.setText(horizontalProductScrollModelList.get(position).getProductDescription());
            productPrice.setText(horizontalProductScrollModelList.get(position).getProductPrice());
        }else{
             view = convertView;
        }
        return view;
    }
}
