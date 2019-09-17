package com.myapplication.data;

import android.content.Intent;

import com.myapplication.model.BoredAction;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.*;

public class BoredApiClientTest {

    BoredApiClient boredApiClient;

    @Before
    public void setUp() {
        boredApiClient = new BoredApiClient();

    }

    @Test
    public void boredTypeActionRequest() {
        //Prepare
        String type = "education";
        Call<BoredAction> call = boredApiClient.client.getBoredAction(
                null,
                type,
                null,
                null,
                null,
                null,
                null
        );

        try {
            //Interact
            Response<BoredAction> response = call.execute();
            BoredAction action = response.body();

            //Assert
            assertEquals(type, action.getType().toString().toLowerCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void boredParticipantsActionRequest() {
        //Prepare
        Integer participants = 2;

        Call<BoredAction> call = boredApiClient.client.getBoredAction(
                null,
                null,
                null,
                null,
                null,
                null,
                participants
        );

        try {
            //Interact
            Response<BoredAction> response = call.execute();
            BoredAction action = response.body();

            //Assert
            assertEquals(participants, action.getParticipants());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void boredPriceActionRequest() {
        //Prepare
        Integer minPrice = 0;
        Integer maxPrice = 0;

        Call<BoredAction> call = boredApiClient.client.getBoredAction(
                null,
                null,
                minPrice,
                maxPrice,
                null,
                null,
                null
        );

        try {
            //Interact
            Response<BoredAction> response = call.execute();
            BoredAction action = response.body();

            //Assert
            assertEquals(minPrice,maxPrice, action.getPrice());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void boredAccessibilityActionRequest() {
        //Prepare
        Integer minAccessibility = 1;
        Integer maxAccessibility = 1;

        Call<BoredAction> call = boredApiClient.client.getBoredAction(
                null,
                null,
               null,
               null,
                minAccessibility,
                maxAccessibility,
                null
        );

        try {
            //Interact
            Response<BoredAction> response = call.execute();
            BoredAction action = response.body();

            //Assert
            assertEquals(minAccessibility,maxAccessibility, action.getAccessibility());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}