package com.example.joyce.driverquickstart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import io.hypertrack.lib.common.HyperTrack;
import io.hypertrack.lib.transmitter.model.HTTrip;
import io.hypertrack.lib.transmitter.model.HTTripParams;
import io.hypertrack.lib.transmitter.model.HTTripParamsBuilder;
import io.hypertrack.lib.transmitter.model.callback.HTTripStatusCallback;
import io.hypertrack.lib.transmitter.service.HTTransmitterService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HyperTrack.setPublishableApiKey("pk_3cc1e888c778eb55895a5b42b0e7ce55a6bb110b", getApplicationContext());
        HTTransmitterService.initHTTransmitter(getApplicationContext());

        ArrayList<String> taskIDs = new ArrayList<>();
        taskIDs.add("d7268153-384e-4e60-b185-ee1479ed6393");
        HTTripParamsBuilder htTripParamsBuilder = new HTTripParamsBuilder();
        HTTripParams htTripParams = htTripParamsBuilder.setDriverID("30b57915-d6a0-4b93-9b32-13d79cf340d6")
                .setTaskIDs(taskIDs)
                .createHTTripParams();

        HTTransmitterService transmitterService = HTTransmitterService.getInstance(this);
        transmitterService.startTrip(htTripParams, new HTTripStatusCallback() {
            @Override
            public void onError(Exception error) {
                Toast.makeText(MainActivity.this, "Trip start failed: " + error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(boolean isOffline, HTTrip trip) {
                Toast.makeText(MainActivity.this, "Trip start success: " + trip, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
