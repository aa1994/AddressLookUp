package com.example.adityaagarwal.addresslookup;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by adityaagarwal on 05/04/18.
 */

public class LocationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> addressList = new ArrayList<>();
    //private CurrentAddressView.Listener clickListener = CurrentAddressView.Listener.NoOp;

    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        AddressView view = (AddressView) LayoutInflater.from(parent.getContext()).inflate(R.layout.address_view, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AddressViewModel viewModel = new AddressViewModel(addressList.get(position));
        AddressView view = (AddressView) holder.itemView;
        //view.setClickListener(clickListener);
        view.bindTo(viewModel);
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

//    public void setClickListener(CurrentAddressView.Listener clickListener) {
//        this.clickListener = clickListener;
//    }

    public void setAddressList(ArrayList<String> addressList) {
        this.addressList = addressList;
        notifyDataSetChanged();
    }
}
