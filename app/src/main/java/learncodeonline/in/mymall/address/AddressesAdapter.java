package learncodeonline.in.mymall.address;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import learncodeonline.in.mymall.R;

import static learncodeonline.in.mymall.address.DeliveryActivity.SELECT_ADDRESS;
import static learncodeonline.in.mymall.address.MyAccountFragment.MANAGE_ADDRESS;
import static learncodeonline.in.mymall.address.MyAddressActivity.refreshItem;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.Viewholder> {

    private List<AdressesModel> adressesModelList;
    private int MODE;
    private int preSelectedPosition;

    public AddressesAdapter(List<AdressesModel> adressesModelList, int MODE) {
        this.adressesModelList = adressesModelList;
        this.MODE = MODE;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addresses_item_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        String username = adressesModelList.get(position).getFullName();
        String userAddress = adressesModelList.get(position).getAddress();
        String userpincode = adressesModelList.get(position).getPincode();
        Boolean selected = adressesModelList.get(position).getSelected();
        holder.setdata(username, userAddress, userpincode, selected,position);
    }

    @Override
    public int getItemCount() {
        return adressesModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private TextView fullname;
        private TextView address;
        private TextView pincode;
        private ImageView icon;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            fullname = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            pincode = itemView.findViewById(R.id.pin_code);
            icon = itemView.findViewById(R.id.icon_view);
        }

        private void setdata(String username, String userAddress, String userpincode, Boolean selected, final int position){
               fullname.setText(username);
               address.setText(userAddress);
               pincode.setText(userpincode);

               if(MODE == SELECT_ADDRESS){
                    icon.setImageResource(R.drawable.check);
                    if(selected){
                        icon.setVisibility(View.VISIBLE);
                        preSelectedPosition = position;
                    }
                    else{
                        icon.setVisibility(View.GONE);
                    }
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(preSelectedPosition!=position){
                            adressesModelList.get(position).setSelected(true);
                            adressesModelList.get(preSelectedPosition).setSelected(false);
                            refreshItem(preSelectedPosition, position);
                            preSelectedPosition = position;
                        }
                        }
                    });
               }else if(MODE == MANAGE_ADDRESS){

               }
        }
    }
}
