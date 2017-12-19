package com.example.elvina.quickshop.data;

import android.database.Cursor;


public class Fragrance {

    public int id;

    public String name;
    public String description;
    public String imageUrl;
    public Double price;
    public int userRating;


    public Fragrance(Cursor cursor) {
        this.name = cursor.getString(cursor.getColumnIndex(com.example.elvina.quickshop.data.FragranceContract.FragranceEntry.COLUMN_NAME));
        this.description = cursor.getString(cursor.getColumnIndex(com.example.elvina.quickshop.data.FragranceContract.FragranceEntry.COLUMN_DESCRIPTION));
        this.imageUrl = cursor.getString(cursor.getColumnIndex(com.example.elvina.quickshop.data.FragranceContract.FragranceEntry.COLUMN_IMAGE));
        this.price = cursor.getDouble(cursor.getColumnIndex(com.example.elvina.quickshop.data.FragranceContract.FragranceEntry.COLUMN_PRICE));
        this.userRating = cursor.getInt(cursor.getColumnIndex(com.example.elvina.quickshop.data.FragranceContract.FragranceEntry.COLUMN_USERRATING));
    }

}
