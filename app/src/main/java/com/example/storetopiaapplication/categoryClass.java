package com.example.storetopiaapplication;

public class categoryClass {


    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatGoals() {
        return catGoals;
    }

    public void setCatGoals(String catGoals) {
        this.catGoals = catGoals;
    }

    public categoryClass(String catName, String catGoals) {
        this.catName = catName;
        this.catGoals = catGoals;
    }

    public String catName;
    public String catGoals;



}
