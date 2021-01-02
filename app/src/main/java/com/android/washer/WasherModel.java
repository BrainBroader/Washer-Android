package com.android.washer;

public class WasherModel {
    private String id;
    private String model;
    private String brand;

    public WasherModel(String id, String model, String brand) {
        this.id = id;
        this.model = model;
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }
}
