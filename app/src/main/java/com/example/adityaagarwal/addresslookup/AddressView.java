package com.example.adityaagarwal.addresslookup;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by adityaagarwal on 05/04/18.
 */

public class AddressView extends LinearLayout {

    public interface Listener {

        void itemClicked(AddressView v, AddressViewModel vm);

        AddressView.Listener NoOp = (v, vm) -> {
        };
    }

    @BindView(R.id.address_text)
    TextView address;

    private AddressViewModel viewModel;
    private AddressView.Listener clickListener = Listener.NoOp;

    public AddressView(Context context) {
        super(context);
    }

    public AddressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setClickListener(AddressView.Listener clickListener) {
        this.clickListener = clickListener;
    }

    public void bindTo(AddressViewModel viewModel) {
        this.viewModel = viewModel;
        validateViewModel();
    }

    private void validateViewModel() {
        address.setText(viewModel.getAddress());
        address.setOnClickListener(view -> clickListener.itemClicked(this, viewModel));
    }
}

