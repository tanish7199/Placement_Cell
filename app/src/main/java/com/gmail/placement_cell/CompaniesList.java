package com.gmail.placement_cell;

/**
 * Created by Tanish on 15-03-2018.
 */

public class CompaniesList {
    private int image;
    private String Name;

    public CompaniesList(int image, String name) {
        this.image = image;
        Name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
