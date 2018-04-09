package com.example.adityaagarwal.addresslookup;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityaagarwal on 05/04/18.
 */

public class FetchAddressService extends IntentService {

    protected ResultReceiver resultReceiver;
    private ArrayList<String> addressFragments = new ArrayList<>();

    public FetchAddressService() {
        super("FetchAddressService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Geocoder geocoder = new Geocoder(this);
        String errorMessage = "";

        Location location = intent.getParcelableExtra(Constants.LOCATION_DATA_EXTRA);
        resultReceiver = intent.getParcelableExtra(Constants.RECEIVER);

        List<Address> addressList = new ArrayList<>();

        try {
            addressList = geocoder.getFromLocation(location.getLatitude(),
                    location.getLongitude(),
                    10);
        } catch (Exception e) {
            errorMessage = "Sorry could not fetch your location";
            addressFragments.add(errorMessage);
        }

        if (addressList == null || addressList.size() == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = "Sorry we could not find you";
                addressFragments.add(errorMessage);
            }
            deliverAddress(Constants.LOCATION_ADDRESS_FAILURE, addressFragments);
        } else {

            // Fetch the address lines using getAddressLine,
            // join them, and send them to the thread.

            for (Address address : addressList) {
                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                    addressFragments.add(address.getAddressLine(i));
                }
            }
            deliverAddress(Constants.LOCATION_ADDRESS_SUCCESS,
                    addressFragments);
        }
    }

    private void deliverAddress(int locationAddressCode, ArrayList<String> message) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(Constants.RESULT_DATA_KEY, message);
        resultReceiver.send(locationAddressCode, bundle);
    }
}

