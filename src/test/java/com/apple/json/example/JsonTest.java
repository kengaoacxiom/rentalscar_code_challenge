package com.apple.json.example;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.oracle.javafx.jmx.json.JSONWriter;
import org.junit.Ignore;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonTest {
    // test for task1 print blue tesla
    @Test
    public void test1() {
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("rentalcar.json");

        Reader reader = new InputStreamReader(inputStream);
        JsonObject jsonObject = parser.parse(reader).getAsJsonObject();
        Assert.assertNotNull(jsonObject);

        JsonArray cars = jsonObject.getAsJsonArray("Car");
        Assert.assertNotNull(cars);
        for (int i = 0; i < cars.size(); i++) {
            String make = cars.get(i).getAsJsonObject().get("make").getAsString();
            //System.out.println(make);
            String color = cars.get(i).getAsJsonObject().getAsJsonObject("metadata").get("Color").getAsString();
            String notes = cars.get(i).getAsJsonObject().getAsJsonObject("metadata").get("Notes").getAsString();

            //System.out.println(notes);
            if (color.equalsIgnoreCase("blue") && make.equalsIgnoreCase("tesla")) {
                System.out.println(cars.get(i));
                System.out.println(notes);
            }


        }
        Assert.assertEquals(4, cars.size());
    }
    //Test for task 2 lowest price car
    @Test
    public void test2() {
        float lowest=0;
        int index=0;

        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("rentalcar.json");

        Reader reader = new InputStreamReader(inputStream);
        JsonObject jsonObject = parser.parse(reader).getAsJsonObject();
        Assert.assertNotNull(jsonObject);

        JsonArray cars = jsonObject.getAsJsonArray("Car");
        Assert.assertNotNull(cars);

        lowest = cars.get(0).getAsJsonObject().getAsJsonObject("perdayrent").get("Price").getAsFloat();
        for(int i=1; i<cars.size(); i++){
            float price = cars.get(i).getAsJsonObject().getAsJsonObject("perdayrent").get("Price").getAsFloat();
            float discount= cars.get(i).getAsJsonObject().getAsJsonObject("perdayrent").get("Discount").getAsFloat();
            price=price-discount;
            if(price<lowest){
                 lowest=price;
                 index=i;
            }
        }
        System.out.println(cars.get(index));
    }

}